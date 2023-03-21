package shoppingsimulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer;

    Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        customer = new Customer(List.of("butter", "jam", "broccoli"), 200);

        shop.addCustomer(customer);
        shop.addCartToCustomer(customer);

        customer.addProductsToCartFromShoppingList(true);
    }

    @Test
    void addProductsToCartFromShoppingList() {
        assertEquals(3, customer.getCart().getProducts().size());
    }

    @Test
    void pay() {
        customer.pay(PayType.CASH, 100);
        assertEquals(100, customer.getCash());
    }
}
