package hu.geralt.controllers.beer.customer;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import hu.geralt.dtos.beer.CustomerDto;
import hu.geralt.exceptions.NotFoundException;
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
    public List<CustomerDto> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping
    @RequestMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get customer by id - in controller");
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customer) {
        CustomerDto savedCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCostumerById (
            @PathVariable UUID customerId,
            @RequestBody CustomerDto customer
    ) {
        if (customerService.updateCostumerById(customerId, customer).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("customerId") UUID customerId){
        if(!customerService.deleteCustomerById(customerId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<Void> patchCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customer) {
        customerService.patchCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
