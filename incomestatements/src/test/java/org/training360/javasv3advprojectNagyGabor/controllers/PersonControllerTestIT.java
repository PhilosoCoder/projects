package org.training360.javasv3advprojectNagyGabor.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.CreatePersonCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.IncomeDto;
import org.training360.javasv3advprojectNagyGabor.dtos.PersonDto;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;
import org.training360.javasv3advprojectNagyGabor.model.Person;
import org.training360.javasv3advprojectNagyGabor.services.PersonService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from expenses", "delete from incomes", "delete from people"})
class PersonControllerTestIT {

    @Autowired
    WebTestClient client;

    @Autowired
    PersonService service;

    PersonDto person;

    @BeforeEach
    void init() {
        person = client.post()
                .uri("api/people")
                .bodyValue(new CreatePersonCommand("Jonathan"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PersonDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreatePerson() {
        PersonDto result = client.post()
                .uri("/api/people")
                .bodyValue(new CreatePersonCommand(
                        "Jack"))
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(PersonDto.class).returnResult().getResponseBody();

        assertNotNull(result.getId());
        assertEquals(1, result.getId());
    }

    @Test
    void testCreateIncomeToPerson() {
        IncomeDto income = client.post()
                .uri(u -> u.path("api/people/{id}/incomes").build(person.getId()))
                .bodyValue(new CreateIncomeCommand(
                        "rent", 30000, IncomeType.RENT, LocalDate.of(2011, 1, 1)))
                .exchange()
                .expectBody(IncomeDto.class)
                .returnResult().getResponseBody();

        assert income != null;
        assertNotNull(income.getId());
        assertEquals("rent", income.getTitle());
        assertEquals(30000, income.getAmount());
        assertEquals(IncomeType.RENT, income.getIncomeType());
        assertEquals(LocalDate.of(2011, 1, 1), income.getDate());
    }
}