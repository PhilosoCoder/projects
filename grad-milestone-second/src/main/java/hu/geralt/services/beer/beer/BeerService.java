package hu.geralt.services.beer.beer;

import java.util.Optional;
import java.util.UUID;

import hu.geralt.dtos.beer.BeerDto;
import hu.geralt.entities.beer.BeerStyle;
import org.springframework.data.domain.Page;

public interface BeerService {

    Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveBeer(BeerDto beer);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer);

    boolean deleteBeerById(UUID beerId);

    Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beer);

}
