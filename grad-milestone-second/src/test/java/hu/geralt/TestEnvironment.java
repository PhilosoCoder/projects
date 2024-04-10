package hu.geralt;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import hu.geralt.bootstrap.BootstrapData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public abstract class TestEnvironment {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private BootstrapData bootstrapData;

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", TestPostgres::jdbcUrl);
        registry.add("spring.datasource.username", TestPostgres::username);
        registry.add("spring.datasource.password", TestPostgres::password);
    }

    @BeforeEach
    void setup() throws FileNotFoundException {
        bootstrapData.run();
    }

    @AfterEach
    void teardown() throws SQLException {
        TestPostgres.executeSqlScript("/db-cleanup.sql");
    }

}
