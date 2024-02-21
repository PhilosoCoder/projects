package hu.geralt.services.beer.beer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import hu.geralt.model.beer.Beer;
import hu.geralt.model.beer.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    private final Map<UUID, Beer> beerMap;

    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(beerMap.values());
    }

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer otherBeer = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer anotherBeer = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer.getId(), beer);
        beerMap.put(otherBeer.getId(), otherBeer);
        beerMap.put(anotherBeer.getId(), anotherBeer);
    }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get beer Id - in service. Id: " + id.toString());
        return beerMap.get(id);
    }

    @Override
    public Beer saveBeer(Beer beer) {
        Beer savedBear = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .version(beer.getVersion())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBear.getId(), savedBear);
        return savedBear;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        existing.setBeerName(beer.getBeerName());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())){
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }
    }
}
