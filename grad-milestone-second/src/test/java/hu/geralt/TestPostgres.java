package hu.geralt;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestPostgres {

    private static final String IMAGE = "postgres:latest";

    private static final String DATABASE_NAME = "grad-milestone-second";

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            DockerImageName.parse(IMAGE).asCompatibleSubstituteFor("postgres"))
            .withDatabaseName(DATABASE_NAME);

    private static final DataSource dataSource;

    private static final JdbcClient jdbcClient;

    static {
        container.start();
        dataSource = createDataSource();
        jdbcClient = JdbcClient.create(dataSource);
    }

    public static String jdbcUrl() {
        return container.getJdbcUrl();
    }

    public static String username() {
        return container.getUsername();
    }

    public static String password() {
        return container.getPassword();
    }

    private static DataSource createDataSource() {
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl());
        dataSource.setUsername(username());
        dataSource.setPassword(password());

        return dataSource;
    }

    public static void executeSqlScript(String filePath) throws SQLException {
        var connection = dataSource.getConnection();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource(filePath));
        connection.close();
    }

}
