package webshop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService {

    private Set <Customer> customers = new HashSet<>();

    public void addCustomer(Customer customer) {
        if (customers.contains(customer)) {
            throw new IllegalArgumentException("Customer with e-mail address: " + customer.getEmail() + " is already registered.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be empty.");
        }
        customers.add(customer);
    }

    public List<Customer> listCustomersByCategoryGiven(CustomerCategory category) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getCategory().equals(category)) {
                result.add(customer);
            }
        }
        return result;
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("No customer with e-mail address: " + email);
    }

    public List <Customer> listCustomersSortedByEmail() {
        List<Customer> result = new ArrayList<>(customers);
        result.sort(new CustomerEmailComparator());
        return result;
    }

    public List <String> listCustomerNamesSorted() {
        List<String> result = new ArrayList<>();
        for (Customer customer : customers) {
            result.add(customer.getName());
        }
        result.sort(new CustomerNameComparator());
        return result;
    }

    public Set<Customer> getCustomers() {
        return new HashSet<>(customers);
    }



}
