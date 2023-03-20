package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<Cart> carts = new ArrayList<>();

    private List<Customer> customers = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Cashier> cashiers = new ArrayList<>();

    private List<CashRegister> cashRegisters = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addCartToCustomer(Customer customer) {
        Cart cart = new Cart();
        cart.setShop(this);
        customer.setCart(cart);
        carts.add(cart);
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addCashRegister(CashRegister cashRegister) {
        cashRegisters.add(cashRegister);
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
