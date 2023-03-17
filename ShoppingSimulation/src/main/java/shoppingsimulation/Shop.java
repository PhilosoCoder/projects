package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private Cart cart = new Cart();

    private List<Customer> customers = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addCartToCostumer(Customer customer) {
        cart.setShop(this);
        customer.setHasCart(true);
        customer.setCart(cart);
        customer.setShop(this);

    }

    public boolean isProductsAvailable(List<String> shoppingList) {
        return true;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
