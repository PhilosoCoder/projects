package shoppingsimulation;

import java.util.List;

public class Customer {

    private Long id;

    private List<String> shoppingList;

    private Cart cart;

    private int cash;

    public Customer(List<String> shoppingList, int cash) {
        this.shoppingList = shoppingList;
        this.cash = cash;
    }

    public Customer(Long id, List<String> shoppingList, int cash) {
        this.id = id;
        this.shoppingList = shoppingList;
        this.cash = cash;
    }

    public void addProductsToCartFromShoppingList(boolean hasProducts) {
        if (hasProducts) {
            for (String s : shoppingList) {
                cart.addProduct(s);
            }
        }
    }

    public boolean checkTheWalletHasEnoughCashToPay(int amount) {
        return true;
    }

    public void pay(PayType type, int amount) {
        if (checkTheWalletHasEnoughCashToPay(amount) && type == PayType.CASH) {
            cash -= amount;
        }
    }

    public List<String> getShoppingList() {
        return shoppingList;
    }

    public Cart getCart() {
        return cart;
    }

    public int getCash() {
        return cash;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
