package org.training360.javasv3advprojectNagyGabor.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.training360.javasv3advprojectNagyGabor.dtos.*;
import org.training360.javasv3advprojectNagyGabor.exceptions.ExpenseNotFoundException;
import org.training360.javasv3advprojectNagyGabor.mappers.ExpenseMapper;
import org.training360.javasv3advprojectNagyGabor.model.Expense;
import org.training360.javasv3advprojectNagyGabor.model.ExpenseType;
import org.training360.javasv3advprojectNagyGabor.repositories.ExpenseRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    private ExpenseMapper expenseMapper;

    public ExpenseDto findExpenseById(long id) {
        Expense expense = getExpense(id);
        return expenseMapper.toDto(expense);
    }

    private Expense getExpense(long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @Transactional
    public ExpenseDto createExpense(CreateExpenseCommand createExpenseCommand) {
        Expense expense = new Expense(createExpenseCommand.getTitle(), createExpenseCommand.getAmount(),
                createExpenseCommand.getExpenseType(), createExpenseCommand.getDate());
        expenseRepository.save(expense);
        return expenseMapper.toDto(expense);
    }

    public ExpenseDto deleteExpense(long id) {
        Expense expense = getExpense(id);
        expenseRepository.delete(expense);
        return expenseMapper.toDto(expense);
    }

    public List<ExpenseDto> listExpenses(Optional<ExpenseType> type, Optional<Integer> limit) {
        List<Expense> expenses = expenseRepository.findExpensesByTypeAndMaxPrice(type, limit);
        return expenseMapper.toDtos(expenses);
    }

    @Transactional
    public ExpenseDto changeExpenseAmountById(long id, UpdateExpenseCommand updateExpenseCommand) {
        Expense expense = getExpense(id);
        expense.setAmount(updateExpenseCommand.getAmount());
        return expenseMapper.toDto(expense);
    }
}
