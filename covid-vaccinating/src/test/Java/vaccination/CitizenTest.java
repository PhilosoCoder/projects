package vaccination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CitizenTest {

    Vaccination vaccinationPrepared = new Vaccination(1L, LocalDate.of(2022, 10, 10), Status.PREPARED);
    Vaccination vaccinationPlanned = new Vaccination(1L, null, Status.PLANNED);
    Vaccination vaccinationCancelled = new Vaccination(1L, 1L, LocalDate.of(2022, 10, 10), null, Status.CANCELLED, "Betegség");
    Vaccination vaccinationAdministrated = new Vaccination(1L, 1L, LocalDate.of(2022, 10, 10), VaccineType.SINOPHARM, Status.ADMINISTRATED, null);

    @Test
    void testCreate() {
        Citizen citizen = new Citizen(1L, "Gipsz Jakab", new City(160L, "Budapest", "XXIII. kerület", "1239"), 32, "gipszes@freemail.hu", "578390903");

        assertEquals(1L, citizen.getId());
        assertEquals("Gipsz Jakab", citizen.getName());
        assertEquals("XXIII. kerület", citizen.getCity().getDistrict());
        assertEquals("XXIII. kerület", citizen.getCity().getDistrict());
        assertEquals("578390903", citizen.getSsn());
        assertEquals(0, citizen.getVaccinations().size());
    }

    @Test
    void testCanBeVaccinated() {
        Citizen citizenPlanned = new Citizen(1L, "Gipsz Jakab", new City(160L, "Budapest", "XXIII. kerület", "1239"), 32, "gipszes@freemail.hu", "578390903", List.of(vaccinationPlanned));
        Citizen citizenPrepared = new Citizen(1L, "Gipsz Jakab", new City(160L, "Budapest", "XXIII. kerület", "1239"), 32, "gipszes@freemail.hu", "578390903", List.of(vaccinationPrepared));

        assertTrue(citizenPlanned.canBeVaccinated(LocalDate.of(2022, 10, 10)));
        assertTrue(citizenPrepared.canBeVaccinated(LocalDate.of(2022, 10, 10)));
    }

    @Test
    void testCanNotBeVaccinated() {
        Citizen citizenCancelled = new Citizen(1L, "Gipsz Jakab", new City(160L, "Budapest", "XXIII. kerület", "1239"), 32, "gipszes@freemail.hu", "578390903", List.of(vaccinationCancelled));
        Citizen citizenAdministrated = new Citizen(1L, "Gipsz Jakab", new City(160L, "Budapest", "XXIII. kerület", "1239"), 32, "gipszes@freemail.hu", "578390903", List.of(vaccinationAdministrated));
        Citizen citizenPrepared = new Citizen(1L, "Gipsz Jakab", new City(160L, "Budapest", "XXIII. kerület", "1239"), 32, "gipszes@freemail.hu", "578390903", List.of(vaccinationPrepared));

        assertFalse(citizenPrepared.canBeVaccinated(LocalDate.of(2022, 9, 15)));
        assertFalse(citizenPrepared.canBeVaccinated(LocalDate.of(2022, 12, 4)));
        assertFalse(citizenAdministrated.canBeVaccinated(LocalDate.of(2022, 10, 10)));
        assertFalse(citizenCancelled.canBeVaccinated(LocalDate.of(2022, 10, 10)));
    }

}