package hu.geralt.services.beer.beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import hu.geralt.dtos.beer.BeerDto;

public interface BeerService {

    List<BeerDto> listBeers();

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveBeer(BeerDto beer);

    void updateBeerById(UUID beerId, BeerDto beer);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDto beer);

}
