package hu.geralt.repositories.beer;

import java.util.List;
import java.util.UUID;

import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository <Beer, UUID> {

    List<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName);

    List<Beer> findAllByBeerStyle(BeerStyle beerStyle);

    List<Beer> findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle);

}
