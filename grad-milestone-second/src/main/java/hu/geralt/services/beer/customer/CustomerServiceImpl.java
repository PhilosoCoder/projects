package hu.geralt.services.beer.customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import hu.geralt.dtos.beer.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDto> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDto customer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName("John")
                .version("v1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        CustomerDto otherCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName("Jack")
                .version("v1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        CustomerDto anotherCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName("Jane")
                .version("v1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customerMap.put(customer.getId(), customer);
        customerMap.put(otherCustomer.getId(), otherCustomer);
        customerMap.put(anotherCustomer.getId(), anotherCustomer);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDto> getCustomerById(UUID id) {
        log.debug("Get customer Id - in service. Id: " + id.toString());
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        CustomerDto savedCustomer = CustomerDto.builder()
                .customerName("Jack")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .id(UUID.randomUUID())
                .version(customer.getVersion())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCostumerById(UUID customerID, CustomerDto customer) {
        CustomerDto existing = customerMap.get(customerID);

        existing.setCustomerName(customer.getCustomerName());
        existing.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDto customer) {
        CustomerDto existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())){
            existing.setCustomerName(customer.getCustomerName());
        }
    }

}
