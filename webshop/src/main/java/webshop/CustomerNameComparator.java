package webshop;

import java.util.Comparator;

public class CustomerNameComparator implements Comparator <String> {

    @Override
    public int compare(String one, String other) {
        return one.compareTo(other);
    }
}
