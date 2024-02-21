package hu.geralt.services.beer.beer;

import java.util.List;
import java.util.UUID;

import hu.geralt.model.beer.Beer;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}
