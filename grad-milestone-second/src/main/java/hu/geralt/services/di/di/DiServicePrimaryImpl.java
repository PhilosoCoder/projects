package hu.geralt.services.di.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DiServicePrimaryImpl implements DiService {

    @Override
    public String sayHello() {
        return "Hello from primary service!";
    }

}
