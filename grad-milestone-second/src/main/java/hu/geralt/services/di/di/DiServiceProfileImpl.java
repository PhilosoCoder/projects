package hu.geralt.services.di.di;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("serviceProfile")
@Profile({"di", "default"})
public class DiServiceProfileImpl implements DiService {

    @Override
    public String sayHello() {
        return "Hello from profile!";
    }

}
