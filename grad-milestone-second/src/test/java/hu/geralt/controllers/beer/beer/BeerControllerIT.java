package hu.geralt.controllers.beer.beer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.geralt.TestEnvironment;
import hu.geralt.dtos.beer.BeerDto;
import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerStyle;
import hu.geralt.exceptions.NotFoundException;
import hu.geralt.mappers.beer.BeerMapper;
import hu.geralt.repositories.beer.BeerRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class BeerControllerIT extends TestEnvironment {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testListBeers() {
        Page<BeerDto> dtos = beerController.listBeers(null, null, false, 1, 2413);

        assertThat(dtos.getContent()).hasSize(1000);
    }

    // Gherkin test
    // Feature: List Beers
    //
    //  Scenario: List beers with default parameters
    //    Given a beer inventory
    //    When I request to list beers with default parameters
    //    Then I should receive a list of beers
    //
    //  Scenario: List beers with specific page size
    //    Given a beer inventory
    //    When I request to list beers with page number 1 and page size 2413
    //    Then the list of beers should contain 1000 beers

    @Test
    void testEmptyBeerList() {
        beerRepository.deleteAll();
        Page<BeerDto> dtos = beerController.listBeers(null, null, false, 25, 1);

        assertThat(dtos).isEmpty();
    }

    @Test
    void testListBeerByName() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .with(httpBasic("user", "password"))
                        .queryParam("beerName", "IPA")
                        .queryParam("pageSize", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(336)));
    }

    @Test
    void testListBeerByStyle() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .with(httpBasic("user", "password"))
                        .queryParam("beerStyle", BeerStyle.IPA.toString())
                        .queryParam("pageSize", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(548)));
    }

    @Test
    void testListBeerByStyleAndNameShowInventoryTrue() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .with(httpBasic("user", "password"))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.toString())
                        .queryParam("showInventory", "TRUE")
                        .queryParam("pageSize", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)))
                .andExpect(jsonPath("$.content[0].quantityOnHand", notNullValue()));
    }

    @Test
    void testListBeerByStyleAndNameShowInventoryFalse() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .with(httpBasic("user", "password"))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.toString())
                        .queryParam("showInventory", "FALSE")
                        .queryParam("pageSize", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)))
                .andExpect(jsonPath("$.content[0].quantityOnHand", nullValue()));
    }

    @Test
    void tesListBeersByStyleAndNameShowInventoryTruePage2() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .with(httpBasic("user", "password"))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name())
                        .queryParam("showInventory", "true")
                        .queryParam("pageNumber", "2")
                        .queryParam("pageSize", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(50)))
                .andExpect(jsonPath("$.content[0].quantityOnHand").value(IsNull.notNullValue()));
    }

    @Test
    void testListBeerByStyleAndName() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .with(httpBasic("user", "password"))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.toString())
                        .queryParam("pageSize", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)));
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
                .beerName("New Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("2342423")
                .price(new BigDecimal("435345345"))
                .build();

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

        assertThrows(NotFoundException.class,
                () -> beerController.updateBeerById(randomUuid, beerDto));
    }

    @Test
    void testDeleteBeer() {
        Beer beer = beerRepository.findAll().getFirst();

        ResponseEntity<Void> responseEntity = beerController.deleteBeerById(beer.getId());
        assertEquals(responseEntity.getStatusCode(), HttpStatusCode.valueOf(204));

        assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }

    @Test
    void testDeleteBeerNotFound() {
        UUID randomUuid = UUID.randomUUID();

        assertThrows(NotFoundException.class,
                () -> beerController.deleteBeerById(randomUuid));
    }

    @Test
    void testPatchBeerWithInvalidName() throws Exception {
        Beer beer = beerRepository.findAll().getFirst();

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name123456789123456789123456789123456789123456789");

        mockMvc.perform(patch("/api/v1/beer/" + beer.getId())
                        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testNoAuth() throws Exception {
        mockMvc.perform(get("/api/v1/beer")
                        .queryParam("beerStyle", BeerStyle.IPA.toString())
                        .queryParam("pageSize", "800"))
                .andExpect(status().isUnauthorized());
    }

}