package hu.geralt;

import hu.geralt.controllers.di.DiControllerProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    DiControllerProfile diController;

    @Test
    void testAutowiredOfDiController() {
        System.out.println(diController.sayHello());
    }

    @Test
    void testGetControllerFromCtx() {
        DiControllerProfile otherDiController = applicationContext.getBean(DiControllerProfile.class);
        System.out.println("hello");
    }

    @Test
    void contextLoads() {
    }

}
