package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {

    private Long id;

    private int cash;

    private int balance;

    private Cashier cashier;

    private Customer customer;

    private Shop shop;

    public int findProductsPriceByBarCode(List<String> barCodes) {
        List<Product> products = findProductByBarCode(barCodes);
        int sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    private List<Product> findProductByBarCode(List<String> barCodes) {
        List<Product> actualProducts = shop.getProducts();
        List<Product> result = new ArrayList<>();
        for (String s : barCodes) {
            for (Product p : actualProducts) {
                if (s.equals(p.getBarCode())) {
                    result.add(p);
                }
            }
        }
        return result;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
