package hu.geralt.services.beer.beer;

import java.io.File;
import java.util.List;

import hu.geralt.entities.beer.BeerCsvRecord;

public interface BeerCsvService {

    List<BeerCsvRecord> convertCsv(File csvFile);

}
