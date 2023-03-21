package shoppingsimulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    Shop shop;

    Cashier cashier;

    CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        cashier = new Cashier();
        shop.addCashier(cashier);

        cashRegister = new CashRegister(100);
        cashier.setCashRegister(cashRegister);
    }

    @Test
    void testPutCashToCashRegister() {
        String result = cashier.putCashToCashRegister(100);

        assertEquals(200, cashRegister.getCash());
        assertEquals("Receipt: 100", result);
    }

    @Test
    void testAskTheConsumerToPay() {
        String result = cashier.askCustomerToPay(200);

        assertEquals("Please pay 200", result);
    }
}
