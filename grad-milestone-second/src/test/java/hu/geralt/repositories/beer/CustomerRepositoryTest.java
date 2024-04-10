package hu.geralt.repositories.beer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hu.geralt.TestEnvironment;
import hu.geralt.entities.beer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

@SpringBootTest
class CustomerRepositoryTest extends TestEnvironment {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {
        Customer customer = customerRepository.save(Customer.builder()
                .customerName("New Name").build());

        assertThat(customer.getId()).isNotNull();
    }

    @Test
    void testSaveCustomerWithTooLongName() {
        assertThrows(TransactionSystemException.class, this::saveCustomerWithLongName);
    }

    private void saveCustomerWithLongName() {
        customerRepository.save(Customer.builder()
                .customerName("New Name123456789123456789123456789123456789123456789")
                .build());
    }

}