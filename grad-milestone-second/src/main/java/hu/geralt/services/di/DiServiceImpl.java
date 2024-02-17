package hu.geralt.services.di;

import org.springframework.stereotype.Service;

@Service("diServiceImpl")
public class DiServiceImpl implements DiService {

    @Override
    public String sayHello() {
        return "Hello from diServiceImpl!";
    }

}
