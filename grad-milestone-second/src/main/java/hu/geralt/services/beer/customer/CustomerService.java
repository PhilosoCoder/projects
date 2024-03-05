package hu.geralt.services.beer.customer;

import java.util.List;
import java.util.UUID;

import hu.geralt.model.beer.CustomerDto;

public interface CustomerService {

    List<CustomerDto> listCustomers();

    CustomerDto getCustomerById(UUID id);

    CustomerDto saveCustomer(CustomerDto customer);

    void updateCostumerById(UUID customerID, CustomerDto customer);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, CustomerDto customer);

}
