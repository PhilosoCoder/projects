package org.training360.javasv3advprojectNagyGabor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.javasv3advprojectNagyGabor.dtos.*;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;
import org.training360.javasv3advprojectNagyGabor.services.IncomeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incomes")
@AllArgsConstructor
@Tag(name = "management of incomes")
public class IncomeController {

    private IncomeService incomeService;

    @GetMapping()
    @Operation(summary = "List of incomes, with optional filters(type, max price")
    public List<IncomeDto> getIncomes(@RequestParam Optional<IncomeType> type, @RequestParam Optional<Integer> limit) {
        return incomeService.listIncomes(type, limit);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find income by id")
    public IncomeDto findIncomeById(@PathVariable("id") long id) {
        return incomeService.findIncomeById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change the amount of income, cannot be negative")
    public IncomeDto changePrice(@PathVariable("id") long id, @Valid @RequestBody UpdateIncomeCommand updateincomeCommand) {
        return incomeService.changeIncomeAmountById(id, updateincomeCommand);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an income")
    @ApiResponse(responseCode = "201", description = "Income has been created")
    @ApiResponse(responseCode = "400", description = "Bad request, income cannot be created")
    public IncomeDto createIncome(@Valid @RequestBody CreateIncomeCommand createIncomeCommand) {
        return incomeService.createIncome(createIncomeCommand);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an income")
    public IncomeDto deleteIncome(@PathVariable("id") long id) {
        return incomeService.deleteIncome(id);
    }
}
