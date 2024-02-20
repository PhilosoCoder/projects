package hu.geralt.services.beer;

import java.util.List;
import java.util.UUID;

import hu.geralt.model.Beer;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

}
