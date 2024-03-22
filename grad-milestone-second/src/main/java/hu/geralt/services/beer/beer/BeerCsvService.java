package hu.geralt.services.beer.beer;

import java.io.File;
import java.util.List;

import hu.geralt.entities.beer.BeerCsvRecord;
import org.springframework.stereotype.Service;

@Service
public interface BeerCsvService {

    List<BeerCsvRecord> convertCsv(File csvFile);

}
