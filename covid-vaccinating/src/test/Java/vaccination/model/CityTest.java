package vaccination.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void testCreate() {
        City city = new City(3L, "Senkifölde", "Óváros", "0000");

        assertEquals(3L, city.getId());
        assertEquals("Senkifölde", city.getName());
        assertEquals("Óváros", city.getDistrict());
        assertEquals("0000", city.getZipCode());
    }
}