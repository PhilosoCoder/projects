package vaccination;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationRepositoryTest {

    CitizenRepository cr;
    VaccinationRepository vr;

    @BeforeEach
    void init() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/vaccination?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException se) {
            throw new IllegalStateException("Can not reach database.", se);
        }

        Flyway flyway = Flyway.configure().cleanDisabled(false).locations("vaccination/db/migration").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        vr = new VaccinationRepository(dataSource);
        cr = new CitizenRepository(dataSource);

        cr.saveNewCitizen(new Citizen(null, "Jóska Pista", new City(160L, "Budapest", "XXIII. kerület", "1239"), 44, "joskapista@gmail.com", "025102880"));
    }

    @Test
    void testSaveVaccination() {
        vr.saveVaccination(new Vaccination(1L, LocalDate.of(2022, 10, 10), Status.PREPARED));
        Citizen citizen = cr.findCitizenBySsn("025102880", vr);

        assertEquals(1, citizen.getVaccinations().size());
        assertEquals(Status.PREPARED, citizen.getVaccinations().get(0).getStatus());
        assertNull(citizen.getVaccinations().get(0).getType());
    }

    @Test
    void testListVaccinationsByCitizen() {
        vr.saveVaccination(new Vaccination(1L, LocalDate.of(2022, 10, 10), Status.PREPARED));
        List<Vaccination> vaccinations = vr.listVaccinationsByCitizen(1L);

        assertEquals(1, vaccinations.size());
        assertEquals(1L, vaccinations.get(0).getCitizenId());
        assertEquals(1L, vaccinations.get(0).getId());
        assertEquals(Status.PREPARED, vaccinations.get(0).getStatus());
    }

    @Test
    void testUpdateVaccination() {
        vr.saveVaccination(new Vaccination(1L, LocalDate.of(2022, 10, 10), Status.PREPARED));
        vr.updateVaccination(new Vaccination(1L, 1L, LocalDate.of(2022, 10, 10), VaccineType.MODERNA, Status.ADMINISTRATED, null));
        vr.saveVaccination(new Vaccination(1L, LocalDate.of(2022, 10, 26), Status.PREPARED));

        List<Vaccination> vaccinations = vr.listVaccinationsByCitizen(1L);

        assertEquals(Status.ADMINISTRATED, vaccinations.get(0).getStatus());
        assertEquals(VaccineType.MODERNA, vaccinations.get(0).getType());
    }
}