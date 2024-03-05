package hu.geralt.repositories.beer;

import static org.assertj.core.api.Assertions.assertThat;

import hu.geralt.entities.beer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {
        Customer customer = customerRepository.save(Customer.builder()
                .customerName("New Name").build());

        assertThat(customer.getId()).isNotNull();
    }

}