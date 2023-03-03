package controller;

import model.User;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class UserRepositoryTest {

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

        userRepository = new UserRepository(dataSource);
    }

    @Test
    void testListUsers() {
        List<User> users = userRepository.listUsers();

        assertEquals(2L, users.get(1).getId());
        assertEquals(1L, users.get(0).getId());

        assertThat(users)
                .hasSize(6)
                .extracting(User::getName)
                .containsExactly(
                        "John Doe",
                        "John Wick",
                        "Jane Doe",
                        "Arnold Schwarzenegger",
                        "Bruce Wayne",
                        "Bruce Lee");
    }
}