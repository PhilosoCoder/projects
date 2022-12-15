package webshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer implements Comparable<Customer> {

    private String name;

    private String email;

    private CustomerCategory category;

    private List <Customer> customers = new ArrayList<>();

    public Customer(String name, String email) {
        validate(name, email);
        this.name = name;
        this.email = email;
        this.category = CustomerCategory.SINGLE;
    }

    public void setCustomerToVip() {
        this.category = CustomerCategory.VIP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public int compareTo(Customer other) {
        return this.email.compareTo(other.email);
    }

    private void validate(String name, String email) {
        if (name == null || name.isBlank() || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Name or e-mail address cannot be empty!");
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CustomerCategory getCategory() {
        return category;
    }
}
