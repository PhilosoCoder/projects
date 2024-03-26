package hu.geralt.controllers.beer.beer;

import java.util.UUID;

import hu.geralt.entities.beer.BeerStyle;
import hu.geralt.exceptions.NotFoundException;
import hu.geralt.dtos.beer.BeerDto;
import hu.geralt.services.beer.beer.BeerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public Page<BeerDto> listBeers(
            @RequestParam(required = false) String beerName,
            @RequestParam(required = false) BeerStyle beerStyle,
            @RequestParam(required = false) Boolean showInventory,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {

        return beerService.listBeers(beerName, beerStyle, showInventory, pageNumber, pageSize);
    }

    @GetMapping("/{beerId}")
    public BeerDto getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get beer by id - in controller");
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Void> createBeer(@Valid @RequestBody BeerDto beer) {
        BeerDto savedBeer = beerService.saveBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeerById(
            @PathVariable("beerId") UUID beerId,
            @Validated @RequestBody BeerDto beer
    ) {
        if (beerService.updateBeerById(beerId, beer).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeerById(@PathVariable("beerId") UUID beerId) {
        if (!beerService.deleteBeerById(beerId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{beerId}")
    public ResponseEntity<Void> patchBeerById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beer) {
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
