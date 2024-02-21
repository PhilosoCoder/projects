package hu.geralt.controllers.di;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.geralt.controllers.di.di.DiControllerProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev", "di"})
class DiApplicationProfileTest {

    @Autowired
    DiControllerProfile diControllerProfile;

    @Test
    void testProfile() {
        assertEquals("Hello from profile!", diControllerProfile.sayHello());
    }

}
