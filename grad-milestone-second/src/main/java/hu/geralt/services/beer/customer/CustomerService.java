package hu.geralt.services.beer.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import hu.geralt.dtos.beer.CustomerDto;

public interface CustomerService {

    List<CustomerDto> listCustomers();

    Optional<CustomerDto> getCustomerById(UUID id);

    CustomerDto saveCustomer(CustomerDto customer);

    Optional<CustomerDto> updateCostumerById(UUID customerID, CustomerDto customer);

    boolean deleteCustomerById(UUID customerId);

    Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customer);

}
