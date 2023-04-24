package org.training360.javasv3advprojectNagyGabor.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.training360.javasv3advprojectNagyGabor.dtos.*;
import org.training360.javasv3advprojectNagyGabor.model.ExpenseType;
import org.training360.javasv3advprojectNagyGabor.services.ExpenseService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from expenses", "delete from incomes", "delete from people"})
class ExpenseControllerTestIT {

    @Autowired
    WebTestClient client;

    @Autowired
    ExpenseService service;

    @Test
    void testCreateExpense() {
        ExpenseDto result = client.post()
                .uri("/api/expenses")
                .bodyValue(new CreateExpenseCommand(
                        "rent", 30000, ExpenseType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(ExpenseDto.class).returnResult().getResponseBody();

        assertEquals("rent", result.getTitle());
        assertEquals(30000, result.getAmount());
        assertEquals(ExpenseType.RENT, result.getExpenseType());
        assertEquals(LocalDate.of(2011, 1, 1), result.getDate());
    }

    @Test
    void testCreateExpenseWithWrongTitle() {
        ProblemDetail problemDetail = client.post()
                .uri("/api/expenses")
                .bodyValue(new CreateExpenseCommand(
                        "", 30000, ExpenseType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectStatus().isEqualTo(406)
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();

        assertEquals(URI.create("validation/not-valid"), problemDetail.getType());
    }

    @Test
    void testFindExpenseByPart() {
        client.post()
                .uri("/api/expenses")
                .bodyValue(new CreateExpenseCommand(
                        "rent", 30000, ExpenseType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange();
        client.post()
                .uri("/api/expenses")
                .bodyValue(new CreateExpenseCommand(
                        "loan", 40000, ExpenseType.LOAN, LocalDate.of(2013, 1, 1)))
                .exchange();

        List<ExpenseDto> result = client.get()
                .uri(u -> u.path("/api/expenses").queryParam("ExpensType", ExpenseType.RENT)
                        .queryParam("limit", 30000).build())
                .exchange()
                .expectBodyList(ExpenseDto.class).returnResult().getResponseBody();

        assertEquals(1, result.size());
    }

    @Test
    void testExpenseNotFound() {
        ProblemDetail detail = client.get()
                .uri("api/expenses/{id}", 1)
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();

        assertEquals(URI.create("expenses/expense-not-found"), detail.getType());
    }

    @Test
    void testUpdateExpenseAmount() {
        ExpenseDto saved = client.post()
                .uri("/api/expenses")
                .bodyValue(new CreateExpenseCommand(
                        "rent", 30000, ExpenseType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectBody(ExpenseDto.class).returnResult().getResponseBody();

        ExpenseDto updated = client.put()
                .uri("/api/expenses/{id}", saved.getId())
                .bodyValue(new UpdateExpenseCommand(30000))
                .exchange()
                .expectBody(ExpenseDto.class).returnResult().getResponseBody();

        assertEquals(updated.getId(), saved.getId());
        assertEquals(updated.getAmount(), saved.getAmount());
    }
}