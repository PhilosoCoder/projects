package hu.geralt.services.beer.beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import hu.geralt.dtos.beer.BeerDto;
import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerStyle;
import hu.geralt.mappers.beer.BeerMapper;
import hu.geralt.repositories.beer.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJpa implements BeerService {

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public List<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory) {
        List<Beer> beerList;

        if (StringUtils.hasText(beerName) && beerStyle == null) {
            beerList = listBeersByName(beerName);
        } else if (!StringUtils.hasText(beerName) && beerStyle != null) {
            beerList = listBeersByBeerStyle(beerStyle);
        } else if (StringUtils.hasText(beerName) && beerStyle != null) {
            beerList = listBeersByBeerNameAndStyle(beerName, beerStyle);
        } else {
            beerList = beerRepository.findAll();
        }

        if (showInventory != null && !showInventory) {
            beerList.forEach(beer -> beer.setQuantityOnHand(null));
        }

        return beerList
                .stream()
                .map(beerMapper::beerToBeerDto)
                .toList();
    }

    private List<Beer> listBeersByBeerNameAndStyle(String beerName, BeerStyle beerStyle) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%", beerStyle);
    }

    private List<Beer> listBeersByName(String beerName) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase("%" + beerName + "%");
    }

    private List<Beer> listBeersByBeerStyle(BeerStyle beerStyle) {
        return beerRepository.findAllByBeerStyle(beerStyle);
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.ofNullable(
                beerMapper.beerToBeerDto(beerRepository.findById(id).orElse(null)));
    }

    @Override
    @Transactional
    public BeerDto saveBeer(BeerDto beer) {
        Beer savedBeer = beerRepository.saveAndFlush(beerMapper.beerDtoToBeer(beer));
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer) {
        AtomicReference<Optional<BeerDto>> atomicReference = new AtomicReference<>();
        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public boolean deleteBeerById(UUID beerId) {
        if (beerRepository.existsById(beerId)) {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beer) {
        AtomicReference<Optional<BeerDto>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            if (StringUtils.hasText(beer.getBeerName())) {
                foundBeer.setBeerName(beer.getBeerName());
            }
            if (beer.getBeerStyle() != null) {
                foundBeer.setBeerStyle(beer.getBeerStyle());
            }
            if (StringUtils.hasText(beer.getUpc())) {
                foundBeer.setUpc(beer.getUpc());
            }
            if (beer.getPrice() != null) {
                foundBeer.setPrice(beer.getPrice());
            }
            if (beer.getQuantityOnHand() != null) {
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

}
