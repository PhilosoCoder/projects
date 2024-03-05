package hu.geralt.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.Customer;
import hu.geralt.entities.beer.BeerStyle;
import hu.geralt.entities.book.Author;
import hu.geralt.entities.book.Book;
import hu.geralt.entities.book.Publisher;
import hu.geralt.repositories.book.AuthorRepository;
import hu.geralt.repositories.beer.BeerRepository;
import hu.geralt.repositories.book.BookRepository;
import hu.geralt.repositories.beer.CustomerRepository;
import hu.geralt.repositories.book.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
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
        loadCustomerData();

        printRepositoryCounts();
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

    private void printRepositoryCounts() {
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Beer Count: " + beerRepository.count());
        System.out.println("Customer Count: " + customerRepository.count());
    }

}
