package org.training360.javasv3advprojectNagyGabor.exceptions;

public class IncomeNotFoundException extends RuntimeException {
    public IncomeNotFoundException(long id) {
        super(String.format("income not found with id: %d", id));
    }
}
