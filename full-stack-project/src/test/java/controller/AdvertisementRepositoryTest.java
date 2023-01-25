package controller;

import model.Advertisement;
import model.User;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdvertisementRepositoryTest {

    AdvertisementRepository advertisementRepository;

    UserRepository userRepository;

    @BeforeEach
    void init() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/full-stack-project?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException se) {
            throw new IllegalStateException("Can not reach database.", se);
        }

        Flyway flyway = Flyway.configure().cleanDisabled(false).dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        advertisementRepository = new AdvertisementRepository(dataSource);
        userRepository = new UserRepository(dataSource);
    }

    @Test
    void testListUsers() {
        List<User> users = userRepository.listUsers();
        List<Advertisement> advertisementsById1 = advertisementRepository.listAdvertisementsByUser(users.get(0).getId());
        List<Advertisement> advertisementsById4 = advertisementRepository.listAdvertisementsByUser(users.get(3).getId());
        List<Advertisement> advertisementsById6 = advertisementRepository.listAdvertisementsByUser(users.get(5).getId());

        assertEquals(1, advertisementsById1.size());
        assertEquals(2, advertisementsById4.size());
        assertEquals(5, advertisementsById6.size());
        assertEquals("John Wick", users.get(1).getName());
        assertEquals(2L, users.get(1).getId());
        assertEquals(1L, users.get(0).getId());
    }
}