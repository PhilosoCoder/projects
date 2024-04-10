package hu.geralt.repositories.beer;

import hu.geralt.TestEnvironment;
import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.BeerOrder;
import hu.geralt.entities.beer.BeerOrderShipment;
import hu.geralt.entities.beer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerOrderRepositoryTest extends TestEnvironment {

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;

    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().getFirst();
        testBeer = beerRepository.findAll().getFirst();
    }

    @Test
    void testBeerOrders() {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test order")
                .customer(testCustomer)
                .beerOrderShipment(
                        BeerOrderShipment.builder()
                        .trackingNumber("1235r").build())
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);

        System.out.println(savedBeerOrder.getBeerOrderShipment().getTrackingNumber());
    }

}