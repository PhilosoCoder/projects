package org.training360.javasv3advprojectNagyGabor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ProblemDetail handleIllegalArgumentExceptionPeople(PersonNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create("people/person-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(IncomeNotFoundException.class)
    public ProblemDetail handleIllegalArgumentExceptionIncomes(IncomeNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create("incomes/income-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ProblemDetail handleIllegalArgumentExceptionExpenses(ExpenseNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create("expenses/expense-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptionIncomes(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        problemDetail.setType(URI.create("validation/not-valid"));
        return problemDetail;
    }
}
