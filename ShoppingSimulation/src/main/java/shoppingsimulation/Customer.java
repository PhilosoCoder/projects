package shoppingsimulation;

import java.util.List;

public class Customer {

    private Long id;

    private String name;

    private List<String> shoppingList;

    private Cart cart;

    private Shop shop;

    private int cash;

    private int balance;

    private int coupon;

    private boolean hasCart;

    private String receipt;

    public Customer(String name, List<String> shoppingList, int cash, int balance, int coupon) {
        this.name = name;
        this.shoppingList = shoppingList;
        this.cash = cash;
        this.balance = balance;
        this.coupon = coupon;
    }

    public Customer(Long id, String name, List<String> shoppingList, int cash, int balance, int coupon) {
        this.id = id;
        this.name = name;
        this.shoppingList = shoppingList;
        this.cash = cash;
        this.balance = balance;
        this.coupon = coupon;
    }

    public void addProductsToCartFromShoppingList() {
        if (shop.isProductsAvailable(shoppingList)) {
            for (String s : shoppingList) {
                cart.addProduct(s);
            }
        }
    }

    public boolean checkTheAmountOfWallet(int amount) {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getShoppingList() {
        return shoppingList;
    }

    public int getCash() {
        return cash;
    }

    public int getBalance() {
        return balance;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setHasCart(boolean hasCart) {
        this.hasCart = hasCart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
