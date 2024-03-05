package hu.geralt.bootstrap;

import static org.assertj.core.api.Assertions.assertThat;

import hu.geralt.repositories.book.AuthorRepository;
import hu.geralt.repositories.beer.BeerRepository;
import hu.geralt.repositories.book.BookRepository;
import hu.geralt.repositories.beer.CustomerRepository;
import hu.geralt.repositories.book.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootstrapDataTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setup() {
        bootstrapData = new BootstrapData(
                authorRepository, bookRepository, publisherRepository, beerRepository, customerRepository);
    }

    @Test
    void run() {
        bootstrapData.run((String) null);

        assertThat(authorRepository.count()).isEqualTo(4);
        assertThat(bookRepository.count()).isEqualTo(2);
        assertThat(publisherRepository.count()).isEqualTo(2);
        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}