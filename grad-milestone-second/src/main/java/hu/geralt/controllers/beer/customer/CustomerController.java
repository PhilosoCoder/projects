package hu.geralt.controllers.beer.customer;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import hu.geralt.model.beer.Beer;
import hu.geralt.model.beer.Customer;
import hu.geralt.services.beer.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping
    @RequestMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get customer by id - in controller");
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return ResponseEntity.created(new URI("/api/v1/customer/" + savedCustomer.getId())).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCostumerById (
            @PathVariable UUID customerId,
            @RequestBody Customer customer
    ) {
        customerService.updateCostumerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("customerId") UUID customerId){
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<Void> patchCustomerById(@PathVariable("customerId") UUID customerId, Customer customer) {
        customerService.patchCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
