package webshop;

import java.util.Comparator;

public class OrderTotalAmountComparator implements Comparator <Order> {

    @Override
    public int compare(Order one, Order other) {
        return Integer.compare(other.getTotalAmount(), one.getTotalAmount());
    }
}
