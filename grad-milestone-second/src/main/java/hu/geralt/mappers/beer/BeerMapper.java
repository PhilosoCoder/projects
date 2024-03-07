package hu.geralt.mappers.beer;

import hu.geralt.entities.beer.Beer;
import hu.geralt.dtos.beer.BeerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto dto);

    BeerDto  beerToBeerDto(Beer beer);

}
