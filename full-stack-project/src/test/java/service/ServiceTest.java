package service;

import controller.AdvertisementRepository;
import controller.UserRepository;
import model.Advertisement;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mariadb.jdbc.MariaDbDataSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    Service service;

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

        UserRepository userRepository = new UserRepository(dataSource);
        AdvertisementRepository advertisementRepository = new AdvertisementRepository(dataSource);
        service = new Service(userRepository, advertisementRepository);
    }

    @Test
    void testListUsersIntegral() {
        Map<String, List<Advertisement>> result = service.listAdvertisementsByUsers();

        assertEquals(6, result.size());
        assertEquals(1, result.get("John Wick").size());
        assertEquals(2, result.get("Arnold Schwarzenegger").size());
        assertEquals(3, result.get("Bruce Wayne").size());
    }

    @Test
    void testListUsersMock() {
        service.listUsers();

        verify(userRepository).listUsers();
    }
}