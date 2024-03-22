package hu.geralt.services.beer.beer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import hu.geralt.entities.beer.BeerCsvRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCsv() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCsvRecord> records = beerCsvService.convertCsv(file);

        System.out.println(records.size());

        assertThat(records).isNotEmpty();
    }

}