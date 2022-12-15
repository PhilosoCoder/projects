package webshop;

import java.util.Comparator;

public class CustomerEmailComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer one, Customer other) {
        return one.getEmail().compareTo(other.getEmail());
    }
}
