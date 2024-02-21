package hu.geralt.services.di.di;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("impl")
public class DiServiceImpl implements DiService {

    @Override
    public String sayHello() {
        return "Hello from diServiceImpl!";
    }

}
