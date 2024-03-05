package hu.geralt.mappers.beer;

import hu.geralt.entities.beer.Beer;
import hu.geralt.dtos.beer.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoBeer(BeerDto dto);

    BeerDto beerToBeerDto(Beer beer);

}
