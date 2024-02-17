package hu.geralt.controllers.di;

import hu.geralt.services.di.DiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class DiControllerQualifier {

    @Qualifier("diServiceImpl")
    @Autowired
    private DiService diService;

    public String sayHello() {
        System.out.println("I'm in the qualifier controller");
        return diService.sayHello();
    }

}
