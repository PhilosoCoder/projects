package shoppingsimulation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //one costumer enter a shop
        Customer customer = new Customer(List.of("butter", "milk"), 20_000);
        Shop superMarket = new Shop();
        superMarket.addCustomer(customer);

        //the costumer get a cart
        superMarket.addCartToCustomer(customer);

        //if the items of shopping list are available, put them to the cart
        boolean hasProducts = superMarket.isProductsAvailable(customer.getShoppingList());
        customer.addProductsToCartFromShoppingList(hasProducts);

        //the costumer step to the cash register
        CashRegister cashRegister = new CashRegister(100_000);
        superMarket.addCashRegister(cashRegister);

        //the cashier take the content of the cart
        Cashier cashier = new Cashier();
        superMarket.addCashier(cashier);
        cashier.setCustomer(customer);
        cashier.setCashRegister(cashRegister);
        cashier.takeProductsFromCustomer(customer.getCart());

        //the cashier read the barcodes with the bar code reader,
        String barCodes = cashier.readBarCodes();
        // and put them to the cash register that find the products and calculate the total amount
        int totalAmount = cashRegister.sumProductsPriceByBarCode(List.of(barCodes), new ArrayList<>());

        //the cash register print the total amount
        System.out.println("Total amount to pay " + totalAmount);

        //the cashier ask the costumer to pay with cash
        System.out.println(cashier.askCostumerToPay(PayType.CASH, totalAmount));
        //the customer check its wallet to have enough money in there
        customer.checkTheWalletHasEnoughCashToPay(totalAmount);
        //the customer give the amount to the cashier
        customer.pay(PayType.CASH, totalAmount);
        //the cashier put the money to the cash register, and it prints a receipt
        String receipt = cashier.putCashToCashRegister(totalAmount);

        //the cashier give the receipt to the costumer
        System.out.println(receipt);

        //end of shopping
        cashier.setCustomer(null);
        superMarket.getCustomers().remove(customer);
    }
}
