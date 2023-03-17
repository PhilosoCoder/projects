package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Cashier {

    private Long id;

    private String name;

    private CashRegister cashRegister;

    private Customer customer;

    private List<Product> actualProductsOfCustomer;

    public List<Product> takeProductsFromCustomer(List<Product> products) {
        actualProductsOfCustomer = products;
        customer.setCart(null);
        customer.setHasCart(false);
        return actualProductsOfCustomer;
    }

    public List<String> readBarCodes() {
        List<String> barCodes = new ArrayList<>();
//        for (Product p : actualProductsOfCustomer) {
//            barCodes.add(p.getBarCode());
//        }
        return barCodes;
    }

    public String askTheCustomerToPay(PayType type, int amount) {
        if (type.equals(PayType.CASH)) {
            if (customer.checkTheAmountOfWallet(amount)) {
                customer.setBalance(customer.getBalance() - amount);
                cashRegister.setCash(cashRegister.getCash() + amount);
            }
        }
        return "";
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCashRegister(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }
}
