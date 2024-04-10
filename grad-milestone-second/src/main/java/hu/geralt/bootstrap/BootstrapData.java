package hu.geralt.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerCsvRecord;
import hu.geralt.entities.beer.BeerStyle;
import hu.geralt.entities.beer.Customer;
import hu.geralt.entities.book.Author;
import hu.geralt.entities.book.Book;
import hu.geralt.entities.book.Publisher;
import hu.geralt.repositories.beer.BeerRepository;
import hu.geralt.repositories.beer.CustomerRepository;
import hu.geralt.repositories.book.AuthorRepository;
import hu.geralt.repositories.book.BookRepository;
import hu.geralt.repositories.book.PublisherRepository;
import hu.geralt.services.beer.beer.BeerCsvService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@Component
@RequiredArgsConstructor
@Getter
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    private final BeerRepository beerRepository;

    private final BeerCsvService beerCsvService;

    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public void run(String... args) throws FileNotFoundException {
        Author martinEricsson = new Author();
        martinEricsson.setFirstName("Martin");
        martinEricsson.setLastName("martinEricsson");

        Author kennethHite = new Author();
        kennethHite.setFirstName("Kenneth");
        kennethHite.setLastName("Hite");

        Author karimMuammar = new Author();
        karimMuammar.setFirstName("Karim");
        karimMuammar.setLastName("Muammar");

        Book vampireTheMasquerade = new Book();
        vampireTheMasquerade.setTitle("Vampire: The Masquerade");
        vampireTheMasquerade.setIsbn("978-1-912200-93-1");

        Publisher whiteWolfPublishing = new Publisher();
        whiteWolfPublishing.setPublisherName("White Wolf Publishing");
        whiteWolfPublishing.setAddress("2075 West Park Place Blvd. Suite G");
        whiteWolfPublishing.setCity("Stone Mountain");
        whiteWolfPublishing.setState("GA");
        whiteWolfPublishing.setZip("30087");

        Author justinAchilli = new Author();
        justinAchilli.setFirstName("Justin");
        justinAchilli.setLastName("Achilli");

        Book sabbatTheBlackHand = new Book();
        sabbatTheBlackHand.setTitle("Sabbat: The Black Hand");
        sabbatTheBlackHand.setIsbn("978-1-735993-88-1");

        Publisher renegadeGameStudios = new Publisher();
        renegadeGameStudios.setPublisherName("Renegade Game Studios");
        renegadeGameStudios.setAddress("27155 Silver Berry Way");
        renegadeGameStudios.setCity("Valley Center");
        renegadeGameStudios.setState("California");
        renegadeGameStudios.setZip("92082");

        Author martinEricssonSaved = authorRepository.findByLastName(
                martinEricsson.getLastName()).orElseGet(() -> authorRepository.save(martinEricsson));
        Author kennethHiteSaved = authorRepository.findByLastName(
                kennethHite.getLastName()).orElseGet(() -> authorRepository.save(kennethHite));
        Author karimMuammarSaved = authorRepository.findByLastName(
                karimMuammar.getLastName()).orElseGet(() -> authorRepository.save(karimMuammar));
        Author justinAchilliSaved = authorRepository.findByLastName(
                justinAchilli.getLastName()).orElseGet(() -> authorRepository.save(justinAchilli));

        Book vampireTheMasqueradeSaved = bookRepository.findByIsbn(
                vampireTheMasquerade.getIsbn()).orElseGet(() -> bookRepository.save(vampireTheMasquerade));
        Book sabbatTheBlackHandSaved = bookRepository.findByIsbn(
                sabbatTheBlackHand.getIsbn()).orElseGet(() -> bookRepository.save(sabbatTheBlackHand));

        Publisher whiteWolfPublishingSaved = publisherRepository.findBypublisherName(
                whiteWolfPublishing.getPublisherName()).orElseGet(() -> publisherRepository.save(whiteWolfPublishing));
        Publisher renegadeGameStudiosSaved = publisherRepository.findBypublisherName(
                renegadeGameStudios.getPublisherName()).orElseGet(() -> publisherRepository.save(renegadeGameStudios));

        martinEricssonSaved.getBooks().add(vampireTheMasqueradeSaved);
        kennethHiteSaved.getBooks().add(vampireTheMasqueradeSaved);
        karimMuammarSaved.getBooks().add(vampireTheMasqueradeSaved);
        justinAchilliSaved.getBooks().add(sabbatTheBlackHandSaved);
        karimMuammarSaved.getBooks().add(sabbatTheBlackHandSaved);

        vampireTheMasqueradeSaved.getAuthors().add(martinEricssonSaved);
        vampireTheMasqueradeSaved.getAuthors().add(kennethHiteSaved);
        vampireTheMasqueradeSaved.getAuthors().add(karimMuammarSaved);
        sabbatTheBlackHandSaved.getAuthors().add(justinAchilliSaved);
        sabbatTheBlackHandSaved.getAuthors().add(karimMuammarSaved);

        whiteWolfPublishingSaved.getBooks().add(vampireTheMasqueradeSaved);
        renegadeGameStudiosSaved.getBooks().add(sabbatTheBlackHandSaved);

        loadBeerData();
        loadCsvData();
        loadCustomerData();
    }

    private void loadCsvData() throws FileNotFoundException {
        if (beerRepository.count() < 10) {
            File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
            List<BeerCsvRecord> records = beerCsvService.convertCsv(file);

            records.forEach(beerCSVRecord -> {
                BeerStyle beerStyle = switch (beerCSVRecord.getStyle()) {
                    case "American Pale Lager" -> BeerStyle.LAGER;
                    case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" ->
                            BeerStyle.ALE;
                    case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyle.IPA;
                    case "American Porter" -> BeerStyle.PORTER;
                    case "Oatmeal Stout", "American Stout" -> BeerStyle.STOUT;
                    case "Saison / Farmhouse Ale" -> BeerStyle.SAISON;
                    case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyle.WHEAT;
                    case "English Pale Ale" -> BeerStyle.PALE_ALE;
                    default -> BeerStyle.PILSNER;
                };

                beerRepository.save(Beer.builder()
                        .beerName(StringUtils.abbreviate(beerCSVRecord.getBeer(), 50))
                        .beerStyle(beerStyle)
                        .price(BigDecimal.TEN)
                        .upc(beerCSVRecord.getRow().toString())
                        .quantityOnHand(beerCSVRecord.getCountX())
                        .build());
            });
        }
    }

    private void loadBeerData() {
        if (beerRepository.count() == 0) {
            Beer beer = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Beer otherBeer = Beer.builder()
                    .beerName("Crank")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356222")
                    .price(new BigDecimal("11.99"))
                    .quantityOnHand(392)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Beer anotherBeer = Beer.builder()
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.IPA)
                    .upc("12356")
                    .price(new BigDecimal("13.99"))
                    .quantityOnHand(144)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            beerRepository.saveAll(Arrays.asList(beer, otherBeer, anotherBeer));
        }
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            Customer customer = Customer.builder()
                    .customerName("John")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Customer otherCustomer = Customer.builder()
                    .customerName("Jack")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Customer anotherCustomer = Customer.builder()
                    .customerName("Jane")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer, otherCustomer, anotherCustomer));
        }
    }

}
