package vaccination;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class VaccinationTest {
    @Test
    void testCreate() {
        Vaccination vaccination = new Vaccination(1L, LocalDate.of(2022, 10, 10), Status.PLANNED);
        assertNull(vaccination.getId());
        assertEquals(1L, vaccination.getCitizenId());
        assertEquals(LocalDate.of(2022, 10, 10), vaccination.getDate());
        assertNull(vaccination.getType());
    }
}