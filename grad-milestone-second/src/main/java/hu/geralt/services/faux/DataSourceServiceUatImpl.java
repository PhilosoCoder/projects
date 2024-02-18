package hu.geralt.services.faux;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("uat")
public class DataSourceServiceUatImpl implements FauxService {

    @Override
    public String getDatasource() {
        return "User Acceptance Testing";
    }

}
