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
import static org.assertj.core.api.Assertions.assertThat;

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
    void testListAdvertisementsByUser() {
        List<User> users = userRepository.listUsers();
        List<Advertisement> advertisementsById1 = advertisementRepository.listAdvertisementsByUser(users.get(0).getId());
        List<Advertisement> advertisementsById4 = advertisementRepository.listAdvertisementsByUser(users.get(3).getId());
        List<Advertisement> advertisementsById6 = advertisementRepository.listAdvertisementsByUser(users.get(5).getId());

        assertEquals(2L, users.get(1).getId());
        assertEquals(1L, users.get(0).getId());

        assertThat(advertisementsById1)
                .hasSize(1)
                .extracting(Advertisement::getTitle)
                .contains("How to reach success");

        assertThat(advertisementsById4)
                .hasSize(2)
                .extracting(Advertisement::getTitle)
                .containsExactly(
                        "Be like Terminator",
                        "From Austria to the USA");

        assertThat(advertisementsById6)
                .hasSize(5)
                .extracting(Advertisement::getTitle)
                .containsExactly(
                        "Way of The Dragon",
                        "Fast as lightning",
                        "Be like water my friend",
                        "Against Chuck Norris",
                        "Iron fist");
    }
}