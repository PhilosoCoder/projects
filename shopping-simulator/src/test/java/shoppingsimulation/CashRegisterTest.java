package shoppingsimulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    Shop shop;

    Cashier cashier;

    CashRegister cashRegister;

    Customer customer;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        shop.addProduct(new Product("olive", "12345", 20));
        shop.addProduct(new Product("carrot", "54321", 10));

        customer = new Customer(List.of("olive", "carrot"), 300);
        shop.addCartToCustomer(customer);

        cashier = new Cashier();
        cashier.setCustomer(customer);
        shop.addCashier(cashier);

        cashRegister = new CashRegister(100);
        cashier.setCashRegister(cashRegister);
    }

    @Test
    void testSumProductsPriceByBarCode() {
        int result = cashRegister.sumProductsPriceByBarCode(List.of("12345", "54321"), shop.getProducts());

        assertEquals(30, result);
    }
}
