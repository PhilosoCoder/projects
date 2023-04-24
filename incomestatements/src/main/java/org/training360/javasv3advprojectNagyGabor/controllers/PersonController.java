package org.training360.javasv3advprojectNagyGabor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateExpenseCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.CreatePersonCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.PersonDto;
import org.training360.javasv3advprojectNagyGabor.services.PersonService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/people")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a person")
    @ApiResponse(responseCode = "201", description = "Person has been created")
    @ApiResponse(responseCode = "400", description = "Bad request, person cannot be created")
    public PersonDto createPerson(@Valid @RequestBody CreatePersonCommand createPersonCommand) {
        return personService.createPerson(createPersonCommand);
    }

    @PostMapping("/{id}/incomes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an income to a person")
    @ApiResponse(responseCode = "201", description = "Income has been created")
    @ApiResponse(responseCode = "400", description = "Bad request, income cannot be created")
    public PersonDto addIncomeToPerson(@Valid @PathVariable("id") long id, @Valid @RequestBody CreateIncomeCommand createIncomeCommand) {
        return personService.addIncomeToPerson(id, createIncomeCommand);
    }

    @PostMapping("/{id}/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an expense to a person")
    @ApiResponse(responseCode = "201", description = "Expense has been created")
    @ApiResponse(responseCode = "400", description = "Bad request, expense cannot be created")
    public PersonDto addExpenseToPerson(@Valid @PathVariable("id") long id, @Valid @RequestBody CreateExpenseCommand createExpenseCommand) {
        return personService.addExpenseToPerson(id, createExpenseCommand);
    }
}
