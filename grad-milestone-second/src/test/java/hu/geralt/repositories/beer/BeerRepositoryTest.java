package hu.geralt.repositories.beer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import hu.geralt.bootstrap.BootstrapData;
import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerStyle;
import hu.geralt.repositories.book.AuthorRepository;
import hu.geralt.repositories.book.BookRepository;
import hu.geralt.repositories.book.PublisherRepository;
import hu.geralt.services.beer.beer.BeerCsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

@SpringBootTest
class BeerRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerCsvService beerCsvService;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setup() throws FileNotFoundException {
        bootstrapData = new BootstrapData(
                authorRepository, bookRepository, publisherRepository,
                beerRepository, beerCsvService, customerRepository
        );
        beerRepository.deleteAll();
        bootstrapData.run();
    }

    @Test
    void testGetBeerByListName() {
        List<Beer> beerList = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");

        assertThat(beerList).hasSize(336);
    }

    @Test
    void testGetBeerByListStyle() {
        List<Beer> beerList = beerRepository.findAllByBeerStyle(BeerStyle.IPA);

        assertThat(beerList).hasSize(548);
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("347284325")
                .price(new BigDecimal("11.93"))
                .build());

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerWithTooLongName() {
        assertThrows(TransactionSystemException.class, this::saveBeerWithTooLongName);
    }

    private void saveBeerWithTooLongName() {
        beerRepository.save(Beer.builder()
                .beerName("My beer442223435342624364365243526432645436436562646572365423545342")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("347284325")
                .price(new BigDecimal("11.93"))
                .build());
    }

}