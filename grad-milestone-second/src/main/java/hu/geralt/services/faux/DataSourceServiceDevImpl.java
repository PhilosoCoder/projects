package hu.geralt.services.faux;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"})
public class DataSourceServiceDevImpl implements FauxService {

    @Override
    public String getDatasource() {
        return "Development";
    }

}
