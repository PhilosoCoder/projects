package hu.geralt.controllers.di.di;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.geralt.TestEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev", "di"})
class DiApplicationProfileTest extends TestEnvironment {

    @Autowired
    DiControllerProfile diControllerProfile;

    @Test
    void testProfile() {
        assertEquals("Hello from profile!", diControllerProfile.sayHello());
    }

}
