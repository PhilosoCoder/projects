package hu.geralt.repositories.beer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import hu.geralt.TestEnvironment;
import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.TransactionSystemException;

@SpringBootTest
class BeerRepositoryTest extends TestEnvironment {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerByListName() {
        Page<Beer> beerPage = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);

        assertThat(beerPage.getContent()).hasSize(336);
    }

    @Test
    void testGetBeerByListStyle() {
        Page<Beer> beerPage = beerRepository.findAllByBeerStyle(BeerStyle.IPA, null);

        assertThat(beerPage.getContent()).hasSize(548);
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