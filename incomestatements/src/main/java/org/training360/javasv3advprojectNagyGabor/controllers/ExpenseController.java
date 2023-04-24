package org.training360.javasv3advprojectNagyGabor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.javasv3advprojectNagyGabor.dtos.*;
import org.training360.javasv3advprojectNagyGabor.model.ExpenseType;
import org.training360.javasv3advprojectNagyGabor.services.ExpenseService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
@Tag(name = "management of expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    @GetMapping()
    @Operation(summary = "List of expenses, with optional filters(type, max price")
    public List<ExpenseDto> getExpenses(@RequestParam Optional<ExpenseType> type, @RequestParam Optional<Integer> limit) {
        return expenseService.listExpenses(type, limit);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an expense by id")
    public ExpenseDto findExpenseById(@PathVariable("id") long id) {
        return expenseService.findExpenseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an expense")
    @ApiResponse(responseCode = "201", description = "Expense has been created")
    @ApiResponse(responseCode = "400", description = "Bad request, expense cannot be created")
    public ExpenseDto createExpense(@Valid @RequestBody CreateExpenseCommand createShopCommand) {
        return expenseService.createExpense(createShopCommand);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Change the amount of expense, cannot be negative")
    public ExpenseDto changePrice(@PathVariable("id") long id, @Valid @RequestBody UpdateExpenseCommand updateExpenseCommand) {
        return expenseService.changeExpenseAmountById(id, updateExpenseCommand);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an expense with incomes")
    public ExpenseDto deleteExpense(@PathVariable("id") long id) {
        return expenseService.deleteExpense(id);
    }
}
