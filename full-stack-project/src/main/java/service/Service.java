package service;

import controller.AdvertisementRepository;
import controller.UserRepository;
import jakarta.servlet.http.HttpServlet;
import model.Advertisement;
import model.User;
import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service extends HttpServlet {

    private UserRepository userRepository;

    private AdvertisementRepository advertisementRepository;

    public Service() {
        MariaDbDataSource dataSource = createDataSource();

        configureFlyway(dataSource);

        this.userRepository = new UserRepository(dataSource);
        this.advertisementRepository = new AdvertisementRepository(dataSource);
    }

    public Service(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        MariaDbDataSource dataSource = createDataSource();

        configureFlyway(dataSource);

        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    public Map<String, List<Advertisement>> listAdvertisementsByUsers() {
        return listUsers()
                .stream()
                .collect(
                        Collectors.toMap(
                                User::getName,
                                user -> advertisementRepository
                                        .listAdvertisementsByUser(
                                                user.getId())));
    }

    private MariaDbDataSource createDataSource() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/full-stack-project?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException se) {
            throw new IllegalStateException("Can not reach database.", se);
        }
        return dataSource;
    }

    private void configureFlyway(MariaDbDataSource dataSource) {
        Flyway flyway = Flyway.configure().cleanDisabled(false).dataSource(dataSource).load();

        flyway.clean();
        flyway.migrate();
    }
}
