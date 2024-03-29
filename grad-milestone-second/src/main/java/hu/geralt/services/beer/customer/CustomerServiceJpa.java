package hu.geralt.services.beer.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import hu.geralt.dtos.beer.CustomerDto;
import hu.geralt.entities.beer.Customer;
import hu.geralt.mappers.beer.CustomerMapper;
import hu.geralt.repositories.beer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        Customer savedCustomer = customerRepository.save(customerMapper.customerDtoToCustomer(customer));
        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public Optional<CustomerDto> updateCostumerById(UUID customerID, CustomerDto customer) {
        AtomicReference<Optional<CustomerDto>> atomicReference = new AtomicReference<>();
        customerRepository.findById(customerID).ifPresentOrElse(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public boolean deleteCustomerById(UUID customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customer) {
        AtomicReference<Optional<CustomerDto>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
            if (StringUtils.hasText(customer.getCustomerName())){
                foundCustomer.setCustomerName(customer.getCustomerName());
            }
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

}
