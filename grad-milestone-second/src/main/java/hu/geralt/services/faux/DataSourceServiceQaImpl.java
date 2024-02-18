package hu.geralt.services.faux;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("qa")
public class DataSourceServiceQaImpl implements FauxService {

    @Override
    public String getDatasource() {
        return "Quality Assurance";
    }

}
