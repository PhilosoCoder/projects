package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {

    private Long id;

    private int cash;

    public CashRegister(int cash) {
        this.cash = cash;
    }

    public CashRegister(Long id, int cash) {
        this.id = id;
        this.cash = cash;
    }

    public int sumProductsPriceByBarCode(List<String> barCodes, List<Product> products) {
        List<Product> result = findProductByBarCode(barCodes, products);
        int sum = 0;
        for (Product p : result) {
            sum += p.getPrice();
        }
        return sum;
    }

    private List<Product> findProductByBarCode(List<String> barCodes, List<Product> products) {
        List<Product> result = new ArrayList<>();
        for (String s : barCodes) {
            for (Product p : products) {
                if (s.equals(p.getBarCode())) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
