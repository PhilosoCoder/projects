package org.training360.javasv3advprojectNagyGabor.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(long id) {
        super(String.format("person not found with id: %d", id));
    }
}
