package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Shop shop;

    private List<Product> products = new ArrayList<>();

    public void addProduct(String productName) {
        Product product = getProductByName(productName);
        products.add(product);
    }

    private Product getProductByName(String productName) {
        Product result = null;
        for (Product p : shop.getProducts()) {
            if (p.getName().equals(productName)) {
                result = p;
            }
        }
        return result;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
