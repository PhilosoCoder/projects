package hu.geralt.services.beer.customer;

import java.util.List;
import java.util.UUID;

import hu.geralt.model.beer.Customer;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);

    Customer saveCustomer(Customer customer);

    void updateCostumerById(UUID customerID, Customer customer);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);

}
