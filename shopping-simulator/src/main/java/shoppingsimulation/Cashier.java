package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Cashier {

    private Long id;

    private CashRegister cashRegister;

    private Customer customer;

    public Cashier() {
    }

    public Cashier(Long id) {
        this.id = id;
    }

    public void takeProductsFromCustomer(Cart cart) {
        customer.setCart(null);
    }

    public List<String> readBarCodes() {
        return new ArrayList<>(List.of("Content of cart"));
    }

    public String putCashToCashRegister(int amount) {
        cashRegister.setCash(cashRegister.getCash() + amount);
        return "Receipt: " + amount;
    }

    public String askCustomerToPay(int totalAmount) {
        return "Please pay " + totalAmount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCashRegister(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }
}

