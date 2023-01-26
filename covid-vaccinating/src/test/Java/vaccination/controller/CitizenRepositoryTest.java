package vaccination.controller;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import vaccination.model.Citizen;
import vaccination.model.City;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CitizenRepositoryTest {

    CitizenRepository cr;
    VaccinationRepository vr;

    City city;

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

        cr = new CitizenRepository(dataSource);
        vr = new VaccinationRepository(dataSource);
        city = new City(160L, "Budapest", "XXIII. kerület", "1239");
    }

    @Test
    void testSaveNewCitizen() {
        Citizen citizen = new Citizen(null, "Jóska Pista", city, 44, "joskapista@gmail.com", "025102880");
        assertEquals(1L, cr.saveNewCitizen(citizen));
    }

    @Test
    void testListCitizenByCity() {
        cr.saveNewCitizen(new Citizen(null, "Jóska Pista", city, 44, "joskapista@gmail.com", "025102880"));
        cr.saveNewCitizen(new Citizen(null, "Nagy Anna Mária", city, 39, "nagy.annamari64@gmail.com", "151391532"));

        List<Citizen> citizens = cr.listCitizensByCity(city, vr);

        assertEquals(2, citizens.size());
        assertEquals(39, citizens.get(1).getAge());
        assertEquals(2L, citizens.get(1).getId());
        assertEquals("025102880", citizens.get(0).getSsn());
    }

    @Test
    void testFindCitizenBySsn() {
        cr.saveNewCitizen(new Citizen(null, "Jóska Pista", city, 44, "joskapista@gmail.com", "025102880"));

        Citizen citizen = cr.findCitizenBySsn("025102880", vr);

        assertEquals("Jóska Pista", citizen.getName());
        assertEquals(44, citizen.getAge());
    }
}