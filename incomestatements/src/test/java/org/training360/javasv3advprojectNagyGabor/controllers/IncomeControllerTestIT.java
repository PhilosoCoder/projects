package org.training360.javasv3advprojectNagyGabor.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.IncomeDto;
import org.training360.javasv3advprojectNagyGabor.dtos.UpdateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;
import org.training360.javasv3advprojectNagyGabor.services.IncomeService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from expenses", "delete from incomes", "delete from people"})
class IncomeControllerTestIT {

    @Autowired
    WebTestClient client;

    @Autowired
    IncomeService service;

    @Test
    void testCreateIncome() {
        IncomeDto result = client.post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "rent", 30000, IncomeType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(IncomeDto.class).returnResult().getResponseBody();

        assertEquals("rent", result.getTitle());
        assertEquals(30000, result.getAmount());
        assertEquals(IncomeType.RENT, result.getIncomeType());
        assertEquals(LocalDate.of(2011, 1, 1), result.getDate());
    }

    @Test
    void testCreateIncomeWithWrongTitle() {
        ProblemDetail problemDetail = client.post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "", 30000, IncomeType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectStatus().isEqualTo(406)
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();

        assertEquals(URI.create("validation/not-valid"), problemDetail.getType());
    }

    @Test
    void testFindIncomeByPart() {
        client.post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "rent", 30000, IncomeType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange();
        client.post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "loan", 40000, IncomeType.LOAN, LocalDate.of(2013, 1, 1)))
                .exchange();

        List<IncomeDto> result = client.get()
                .uri(u -> u.path("/api/incomes").queryParam("IncomeType", IncomeType.RENT)
                        .queryParam("limit", 30000).build())
                .exchange()
                .expectBodyList(IncomeDto.class).returnResult().getResponseBody();

        assertEquals(1, result.size());
    }

    @Test
    void testIncomeNotFound() {
        ProblemDetail detail = client.get()
                .uri("api/incomes/{id}", 1)
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();

        assertEquals(URI.create("incomes/income-not-found"), detail.getType());
    }

    @Test
    void testUpdateIncomeAmount() {
        IncomeDto saved = client.post()
                .uri("/api/incomes")
                .bodyValue(new CreateIncomeCommand(
                        "rent", 30000, IncomeType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectBody(IncomeDto.class).returnResult().getResponseBody();

        IncomeDto updated = client.put()
                .uri("/api/incomes/{id}", saved.getId())
                .bodyValue(new UpdateIncomeCommand(30000))
                .exchange()
                .expectBody(IncomeDto.class).returnResult().getResponseBody();

        assertEquals(updated.getId(), saved.getId());
        assertEquals(updated.getAmount(), saved.getAmount());
    }


}
