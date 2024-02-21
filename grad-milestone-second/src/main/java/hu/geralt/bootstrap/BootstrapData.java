package hu.geralt.bootstrap;

import hu.geralt.model.book.Author;
import hu.geralt.model.book.Book;
import hu.geralt.model.book.Publisher;
import hu.geralt.repositories.AuthorRepository;
import hu.geralt.repositories.BookRepository;
import hu.geralt.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

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
        whiteWolfPublishing.setPublisherName("White Wolf");
        whiteWolfPublishing.setAddress("2075 West Park Place Blvd. Suite G");
        whiteWolfPublishing.setCity("Stone Mountain");
        whiteWolfPublishing.setState("GA");
        whiteWolfPublishing.setZip("30087");

        Author martinEricssonSaved = authorRepository.save(martinEricsson);
        Author kennethHiteSaved = authorRepository.save(kennethHite);
        Author karimMuammarSaved = authorRepository.save(karimMuammar);
        Book vampireTheMasqueradeSaved = bookRepository.save(vampireTheMasquerade);
        Publisher whiteWolfPublishingSaved = publisherRepository.save(whiteWolfPublishing);

        Author justinAchilli = new Author();
        martinEricsson.setFirstName("Justin");
        martinEricsson.setLastName("Achilli");

        Book sabbatTheBlackHand = new Book();
        sabbatTheBlackHand.setTitle("Sabbat: The Black Hand");
        sabbatTheBlackHand.setIsbn("978-1-735993-88-1");

        Publisher renegadeGameStudios = new Publisher();
        whiteWolfPublishing.setPublisherName("Renegade Game Studios");
        whiteWolfPublishing.setAddress("27155 Silver Berry Way");
        whiteWolfPublishing.setCity("Valley Center");
        whiteWolfPublishing.setState("California");
        whiteWolfPublishing.setZip("92082");

        Author justinAchilliSaved = authorRepository.save(justinAchilli);
        Book sabbatTheBlackHandSaved = bookRepository.save(sabbatTheBlackHand);
        Publisher renegadeGameStudiosSaved = publisherRepository.save(renegadeGameStudios);

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

        authorRepository.save(martinEricssonSaved);
        authorRepository.save(kennethHiteSaved);
        authorRepository.save(karimMuammarSaved);
        authorRepository.save(justinAchilliSaved);

        publisherRepository.save(whiteWolfPublishingSaved);
        publisherRepository.save(renegadeGameStudios);

        System.out.println("In bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }

}
