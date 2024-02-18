package hu.geralt.controllers.di;

import hu.geralt.services.di.DiService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class DiControllerProfile {

    private final DiService diService;

    public DiControllerProfile(@Qualifier("serviceProfile") DiService diService) {
        this.diService = diService;
    }

    public String sayHello() {
        System.out.println("I'm in the profile controller");
        return diService.sayHello();
    }
}
