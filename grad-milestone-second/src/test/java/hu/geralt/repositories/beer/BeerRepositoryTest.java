package hu.geralt.repositories.beer;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

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

}