package shoppingsimulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    Shop shop;

    Customer customer;

    Cashier cashier;

    CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        customer = new Customer(List.of("olive", "carrot"), 300);
        shop.addCartToCustomer(customer);

        cashier = new Cashier();
        cashier.setCustomer(customer);
        shop.addCashier(cashier);
        shop.addProduct(new Product("olive", "12345", 20));
        shop.addProduct(new Product("carrot", "54321", 10));

        cashRegister = new CashRegister(100);
        cashier.setCashRegister(cashRegister);
    }

    @Test
    void testAddProduct() {
        assertThat(shop.getProducts())
                .hasSize(2)
                .extracting(Product::getBarCode)
                .containsExactly("12345", "54321");

        assertThat(shop.getProducts())
                .hasSize(2)
                .extracting(Product::getName)
                .containsExactly("olive", "carrot");
    }

    @Test
    void testGetProductByBarCode() {
        Product productsByBarCode = customer.getCart().getProductByBarCode("54321");

        assertEquals(shop.getProducts().get(1), productsByBarCode);
    }

    @Test
    void testGetProductByName() {
        Product productByName = customer.getCart().getProductByName("olive");

        assertEquals(shop.getProducts().get(0), productByName);
    }
}
