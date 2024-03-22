package hu.geralt.services.beer.beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import hu.geralt.dtos.beer.BeerDto;
import hu.geralt.entities.beer.BeerStyle;

public interface BeerService {

    List<BeerDto> listBeers(String beerName, BeerStyle beerStyle);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveBeer(BeerDto beer);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer);

    boolean deleteBeerById(UUID beerId);

    Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beer);

}
