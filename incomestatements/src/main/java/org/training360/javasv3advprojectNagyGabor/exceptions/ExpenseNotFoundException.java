package org.training360.javasv3advprojectNagyGabor.exceptions;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(long id) {
        super(String.format("expense not found with id: %d", id));
    }
}
