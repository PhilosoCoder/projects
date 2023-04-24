package org.training360.javasv3advprojectNagyGabor.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.IncomeDto;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;
import org.training360.javasv3advprojectNagyGabor.services.IncomeService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IncomeControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    IncomeService service;
    @BeforeEach
    void init() {
        webTestClient
                .post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "rent", 30000, IncomeType.RENT, LocalDate.of(2011, 2, 3)))
                .exchange();

        webTestClient
                .post()
                .uri("api/incomes")
                .bodyValue(new CreateIncomeCommand(
                "loan", 50000, IncomeType.LOAN, LocalDate.of(2011, 4, 5)))
                .exchange();

        webTestClient
                .post()
                .uri("api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "lottery win", 20000000, IncomeType.PRIZE, LocalDate.of(2021,2,3)))
                .exchange();
    }

    @Test
    void testCreateIncome() {
        when(service.createIncome(any()))
                .thenReturn(new IncomeDto(
                        1L, "rent", 30000, IncomeType.RENT, LocalDate.of(2022,4,7)));
        webTestClient
                .post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand("rent", 35000, IncomeType.RENT, LocalDate.of(2023,2,3)))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(IncomeDto.class)
                .value(incomeDto -> assertThat(incomeDto.getTitle().equals("rent")));
    }

    @Test
    void testFindIncomeById() {
        when(service.findIncomeById(1))
                .thenReturn(new IncomeDto(
                        1L, "rent", 30000, IncomeType.RENT, LocalDate.of(2022,4,7)));

        webTestClient
                .get()
                .uri("api/incomes/{id}", 1)
                .exchange()
                .expectBody(IncomeDto.class)
                .value(i -> assertEquals("rent", i.getTitle()));
    }

    @Test
    void testListAllIncomes() {
        IncomeDto rent = new IncomeDto(1L, "rent", 30000, IncomeType.RENT, LocalDate.of(2022,4,7));

        when(service.listIncomes(any(), any()))
                .thenReturn(List.of(rent,
                                    new IncomeDto(2L, "rent", 35000, IncomeType.RENT, LocalDate.of(2023,2,3))));

        webTestClient
                .get()
                .uri(i -> i.path("/api/incomes")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(IncomeDto.class)
                .hasSize(2);
    }
}