package hu.geralt.controllers.beer.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.geralt.bootstrap.BootstrapData;
import hu.geralt.dtos.beer.CustomerDto;
import hu.geralt.entities.beer.Customer;
import hu.geralt.exceptions.NotFoundException;
import hu.geralt.mappers.beer.CustomerMapper;
import hu.geralt.repositories.beer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    BootstrapData bootstrapData;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
        bootstrapData.run();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testListCustomers() {
        List<CustomerDto> customerDtos = customerController.listCustomers();

        assertEquals(3, customerDtos.size());
    }

    @Test
    @Transactional
    @Rollback
    void testEmptyCustomerList() {
        customerRepository.deleteAll();

        assertEquals(0, customerController.listCustomers().size());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDto customerDto = customerController.getCustomerById(customer.getId());

        assertThat(customerDto).isNotNull();
    }

    @Test
    void testCustomerIdNotFound() {
        UUID randomUuid = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(randomUuid));
    }

    @Test
    @Rollback
    @Transactional
    void testSaveCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("New Customer").build();

        ResponseEntity<Void> responseEntity = customerController.createCustomer(customerDto);

        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertNotNull(responseEntity.getHeaders().getLocation());

        String[] locationUuid = responseEntity.getHeaders()
                .getLocation().getPath().split("/");

        UUID savedUuid = UUID.fromString(locationUuid[4]);

        Customer customer = customerRepository.findById(savedUuid).orElseThrow(NotFoundException::new);
        assertThat(customer).isNotNull();
    }

    @Test
    @Rollback
    @Transactional
    void testUpdateCustomer() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
        customerDto.setId(null);
        customerDto.setVersion(null);
        final String customerName = "UPDATED";
        customerDto.setCustomerName(customerName);

        ResponseEntity<Void> responseEntity = customerController.updateCostumerById(customer.getId(), customerDto);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());

        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElseThrow(NotFoundException::new);
        assertEquals(updatedCustomer.getCustomerName(), customerName);
    }

    @Test
    void testUpdatedCustomerNotFound() {
        UUID randomUuid = UUID.randomUUID();
        CustomerDto customerDto = CustomerDto.builder().build();

        assertThrows(NotFoundException.class,
                () -> customerController.updateCostumerById(randomUuid, customerDto));
    }

    @Test
    @Rollback
    @Transactional
    void testDeleteCustomer() {
        Customer customer = customerRepository.findAll().getFirst();

        ResponseEntity<Void> responseEntity = customerController.deleteCustomerById(customer.getId());
        assertEquals(responseEntity.getStatusCode(), HttpStatusCode.valueOf(204));

        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

    @Test
    void testDeleteCustomerNotFound() {
        UUID randomUuid = UUID.randomUUID();

        assertThrows(NotFoundException.class,
                () -> customerController.deleteCustomerById(randomUuid));
    }

    @Test
    void testPatchCustomerWithInvalidName() throws Exception {
        Customer customer = customerRepository.findAll().getFirst();

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("customerName", "New Name123456789123456789123456789123456789123456789");

        mockMvc.perform(patch("/api/v1/customer/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isBadRequest());
    }

}