package vaccination.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import vaccination.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    Service service;

    @BeforeEach
    void init() {
        service = new Service();
    }

    @TempDir
    File temp;

    @Test
    void testGetListOfCitiesByZipCode() {
        List<City> cities = service.getListOfCitiesByZipCode("7720");

        assertEquals(5, cities.size());
        assertEquals("Lovászhetény", cities.get(2).getName());
    }

    @Test
    void testRegistration() {
        service.registration("Jóska Pista", new City(160L, "Budapest", "XXIII. kerület", "1239"), 44, "joskapista@gmail.com", "025102880");
        Citizen citizen = service.getCitizenRepository().findCitizenBySsn("025102880", service.getVaccinationRepository());
        List<Vaccination> vaccinations = service.getVaccinationRepository().listVaccinationsByCitizen(1L);

        assertEquals("Jóska Pista", citizen.getName());
        assertEquals(1, vaccinations.size());
        assertEquals(1L, vaccinations.get(0).getCitizenId());
        assertEquals(Status.PLANNED, vaccinations.get(0).getStatus());
        assertNull(vaccinations.get(0).getType());
        assertNull(vaccinations.get(0).getDate());
    }

    @Test
    void testMassRegistration() {
        service.massRegistration(Path.of("src/test/resources/citizensTest.csv"));
        List<Citizen> citizens = service.getCitizenRepository().listCitizensByCity(new City(160L, "Budapest", "XXIII. kerület", "1239"), service.getVaccinationRepository());

        assertEquals(13, citizens.size());
        assertEquals("Csapó Miklós", citizens.get(12).getName());
        assertEquals(18L, citizens.get(12).getId());
        assertEquals(Status.PLANNED, citizens.get(12).getLastVaccination().getStatus());
        assertEquals(18L, citizens.get(12).getLastVaccination().getId());
    }

    @Test
    void testMassRegistrationWithWrongPath() {
        assertThrows(IllegalArgumentException.class,
                () -> service.massRegistration(Path.of("src/test/noSuchFile.csv")),
                "Unreadable file or wrong path: src/test/noSuchFile.csv");
    }

    @Test
    void testGenerateVaccinationOrder() throws IOException {
        Path path = temp.toPath().resolve("generatedList.csv");
        service.massRegistration(Path.of("src/test/resources/citizensTest.csv"));
        service.generateVaccinationOrder(path, new City(1L, "Budapest", "I. kerület", "1011"), LocalDate.of(2022, 10, 10));
        Citizen citizen = service.getCitizenRepository().findCitizenBySsn("380034909", service.getVaccinationRepository());

        assertEquals(List.of(
                "Időpont(2022-10-10);Név;Irányítószám;Életkor;E-mail cím;TAJ",
                "08:00;Wass Gábor;1011;87;wssgbr@yahoo.com;263404546",
                "08:30;Nagy Péter;1011;76;peter.nagy12@gmail.com;675590499",
                "09:00;Horváth Júlia;1011;55;jolly9@facebook.com;380034909",
                "09:30;Gipsz Jakab;1011;30;joskapista@gmail.com;345664622",
                "10:00;Kovács Dániel;1011;13;danismith94@freemail.hu;048936075"), Files.readAllLines(path));
        assertEquals(Status.PREPARED, citizen.getLastVaccination().getStatus());
        assertEquals(LocalDate.of(2022, 10, 10), citizen.getLastVaccination().getDate());
        assertNull(citizen.getLastVaccination().getType());
    }

    @Test
    void testVaccination() {
        Path path = temp.toPath().resolve("generatedList.csv");
        service.massRegistration(Path.of("src/test/resources/citizensTest.csv"));
        service.generateVaccinationOrder(path, new City(1L, "Budapest", "I. kerület", "1011"), LocalDate.of(2022, 10, 10));

        Citizen citizen = service.getCitizenRepository().findCitizenBySsn("380034909", service.getVaccinationRepository());
        service.vaccination(citizen, LocalDate.of(2022, 10, 10), VaccineType.SINOPHARM);
        List<Vaccination> vaccinations = service.getVaccinationRepository().listVaccinationsByCitizen(5L);

        assertEquals(2, vaccinations.size());
        assertEquals(19L, vaccinations.get(1).getId());
        assertEquals(Status.ADMINISTRATED, vaccinations.get(0).getStatus());
        assertEquals(Status.PREPARED, vaccinations.get(1).getStatus());
        assertEquals(LocalDate.of(2022, 10, 26), vaccinations.get(1).getDate());
    }

    @Test
    void testCancellation() {
        service.registration("Jóska Pista", new City(160L, "Budapest", "XXIII. kerület", "1239"), 44, "joskapista@gmail.com", "025102880");
        Citizen citizen = service.getCitizenRepository().findCitizenBySsn("025102880", service.getVaccinationRepository());

        service.cancellation(citizen, "Betegség");
        Vaccination vaccination = service.getVaccinationRepository().listVaccinationsByCitizen(1L).get(0);

        assertEquals(1L, vaccination.getId());
        assertEquals(Status.CANCELLED, vaccination.getStatus());
        assertEquals("Betegség", vaccination.getNote());
    }

    @Test
    void testReport() {
        Path path = temp.toPath().resolve("generatedList.csv");
        service.massRegistration(Path.of("src/test/resources/citizensTest.csv"));
        service.generateVaccinationOrder(path, new City(160L, "Budapest", "XXIII. kerület", "1239"), LocalDate.of(2022, 10, 10));

        Citizen citizen = service.getCitizenRepository().findCitizenBySsn("736318859", service.getVaccinationRepository());
        Citizen citizen2 = service.getCitizenRepository().findCitizenBySsn("138572950", service.getVaccinationRepository());
        Citizen citizen3 = service.getCitizenRepository().findCitizenBySsn("933457085", service.getVaccinationRepository());

        service.vaccination(citizen, LocalDate.of(2022, 10, 10), VaccineType.SINOPHARM);
        service.vaccination(citizen2, LocalDate.of(2022, 10, 10), VaccineType.SINOPHARM);
        service.vaccination(citizen3, LocalDate.of(2022, 10, 10), VaccineType.SINOPHARM);
        service.vaccination(citizen2, LocalDate.of(2022, 10, 26), VaccineType.SINOPHARM);

        citizen3 = service.getCitizenRepository().findCitizenBySsn("933457085", service.getVaccinationRepository());
        service.cancellation(citizen3, "Betegség");

        Map<Integer, Long> report = service.report(new City(160L, "Budapest", "XXIII. kerület", "1239"));

        assertEquals(13L, report.values().stream().mapToLong(l -> l).sum());
        assertEquals(10L, report.get(1));
        assertEquals(2L, report.get(2));
        assertEquals(1L, report.get(3));
    }
}