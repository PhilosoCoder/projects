package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Long id;

    private Shop shop;

    private List<Product> products = new ArrayList<>();

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
    }

    public void addProduct(String productName) {
        Product product = getProductByName(productName);
        products.add(product);
    }

    public Product getProductByName(String productName) {
        Product result = null;
        for (Product p : shop.getProducts()) {
            if (p.getName().equals(productName)) {
                result = p;
            }
        }
        return result;
    }

    public Product getProductByBarCode(String barCode) {
        Product result = null;
        for (Product p : shop.getProducts()) {
            if (p.getBarCode().equals(barCode)) {
                result = p;
            }
        }
        return result;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Product> getProducts() {
        return products;
    }
}


