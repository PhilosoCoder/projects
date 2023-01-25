package vaccination;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityRepositoryTest {

    CityRepository cr;

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

        cr = new CityRepository(dataSource);
    }

    @Test
    void testListCitiesByZipCode() {
        List<City> cities = cr.listCitiesByZipCode("7720");

        assertEquals(5, cities.size());
        assertEquals(2161L, cities.get(1).getId());
        assertEquals("Martonfa", cities.get(3).getName());
    }
}