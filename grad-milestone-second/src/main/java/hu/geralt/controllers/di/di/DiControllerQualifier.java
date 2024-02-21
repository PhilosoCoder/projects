package hu.geralt.controllers.di.di;

import hu.geralt.services.di.di.DiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DiControllerQualifier {

    private final DiService diService;

    public String sayHello() {
        System.out.println("I'm in the qualifier controller");
        return diService.sayHello();
    }

}
