package hu.geralt.controllers.beer.beer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

import hu.geralt.bootstrap.BootstrapData;
import hu.geralt.dtos.beer.BeerDto;
import hu.geralt.entities.beer.Beer;
import hu.geralt.exceptions.NotFoundException;
import hu.geralt.mappers.beer.BeerMapper;
import hu.geralt.repositories.beer.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    BootstrapData bootstrapData;

    @BeforeEach
    void setup() {
        beerRepository.deleteAll();
        bootstrapData.run();
    }

    @Test
    void testListBeers() {
        List<BeerDto> dtos = beerController.listBeers();

        assertThat(dtos).hasSize(3);
    }


    @Test
    void testEmptyBeerList() {
        beerRepository.deleteAll();
        List<BeerDto> dtos = beerController.listBeers();

        assertThat(dtos).isEmpty();
    }

    @Test
    void testGetBeerById() {
        Beer beer = beerRepository.findAll().getFirst();
        BeerDto dto = beerController.getBeerById(beer.getId());
        assertThat(dto).isNotNull();
    }

    @Test
    void testBeerIdNotFound() {
        UUID randomUUID = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(randomUUID));
    }

    @Test
    void testSaveBeer() {
        BeerDto beerDto = BeerDto.builder()
                .beerName("New Beer").build();

        ResponseEntity<Void> responseEntity = beerController.createBeer(beerDto);

        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertNotNull(responseEntity.getHeaders().getLocation());

        String[] locationUuid = responseEntity.getHeaders()
                .getLocation().getPath().split("/");

        UUID savedUuid = UUID.fromString(locationUuid[4]);

        Beer beer = beerRepository.findById(savedUuid).orElseThrow(NotFoundException::new);
        assertThat(beer).isNotNull();
    }

    @Test
    void testUpdateBeer() {
        Beer beer = beerRepository.findAll().getFirst();
        BeerDto beerDto = beerMapper.beerToBeerDto(beer);
        beerDto.setId(null);
        beerDto.setVersion(null);
        final String beerName = "UPDATED";
        beerDto.setBeerName(beerName);

        ResponseEntity<Void> responseEntity = beerController.updateBeerById(beer.getId(), beerDto);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());

        Beer updatedBeer = beerRepository.findById(beer.getId()).orElseThrow(NotFoundException::new);
        assertEquals(updatedBeer.getBeerName(), beerName);
    }

    @Test
    void testUpdatedBeerNotFound() {
        UUID randomUuid = UUID.randomUUID();
        BeerDto beerDto = BeerDto.builder().build();

        assertThrows(NotFoundException.class, () -> {
            beerController.updateBeerById(randomUuid, beerDto);
        });
    }

    @Test
    void testDeleteBeer() {
        Beer beer = beerRepository.findAll().getFirst();

        ResponseEntity<Void> responseEntity = beerController.deleteBeerById(beer.getId());
        assertEquals(responseEntity.getStatusCode(), HttpStatusCode.valueOf(204));

        assertThat(beerRepository.findById(beer.getId()).isEmpty());
    }

    @Test
    void testDeleteBeerNotFound() {
        UUID randomUuid = UUID.randomUUID();

        assertThrows(NotFoundException.class, () -> {
            beerController.deleteBeerById(randomUuid);
        });
    }

}