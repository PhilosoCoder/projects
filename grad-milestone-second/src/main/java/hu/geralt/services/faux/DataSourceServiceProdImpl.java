package hu.geralt.services.faux;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class DataSourceServiceProdImpl implements FauxService {

    @Override
    public String getDatasource() {
        return "Production";
    }

}
