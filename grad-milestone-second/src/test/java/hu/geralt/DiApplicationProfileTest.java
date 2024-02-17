package hu.geralt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.geralt.controllers.di.DiControllerProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DiApplicationProfileTest {

    @Autowired
    DiControllerProfile diControllerProfile;

    @Test
    void testProfile() {
        assertEquals("Hello from profile!", diControllerProfile.sayHello());
    }

}
