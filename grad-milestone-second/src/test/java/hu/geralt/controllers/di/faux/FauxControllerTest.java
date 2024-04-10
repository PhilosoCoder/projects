package hu.geralt.controllers.di.faux;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.geralt.TestEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev", "di"})
class FauxControllerTest extends TestEnvironment {

    @Autowired
    FauxController fauxController;

    @Test
    void testGetDatasource() {
        assertEquals("Development", fauxController.getDatasource());
    }

}