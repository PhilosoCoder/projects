package hu.geralt.services.beer.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import hu.geralt.dtos.beer.CustomerDto;
import hu.geralt.mappers.beer.CustomerMapper;
import hu.geralt.repositories.beer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerServiceJpa implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> listCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }

    @Override
    public Optional<CustomerDto> getCustomerById(UUID id) {
        return Optional.ofNullable(
                customerMapper.customerToCustomerDto(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        return null;
    }

    @Override
    public void updateCostumerById(UUID customerID, CustomerDto customer) {

    }

    @Override
    public void deleteCustomerById(UUID customerId) {

    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDto customer) {

    }
}
