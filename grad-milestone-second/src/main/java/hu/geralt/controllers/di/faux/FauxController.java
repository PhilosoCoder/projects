package hu.geralt.controllers.di.faux;

import hu.geralt.services.di.faux.FauxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FauxController {

    private final FauxService fauxService;

    public String getDatasource() {
        return fauxService.getDatasource();
    }
}
