package shoppingsimulation;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //one costumer enter a shop
        Customer costumer = new Customer("Jack", List.of("butter", "milk"), 20_000, 50_000, 0);
        Shop superMarket = new Shop();
        superMarket.addCustomer(costumer);

        //the costumer get a cart
        superMarket.addCartToCostumer(costumer);

        //if the items of shopping list are available, put them to the cart
        costumer.addProductsToCartFromShoppingList();

        //the costumer step to the cash register
        CashRegister cashRegister = new CashRegister();
        cashRegister.setCustomer(costumer);
        costumer.setShop(superMarket);

        //the cashier take the content of the cart
        Cashier cashier = new Cashier();
        cashier.setCustomer(costumer);
        cashier.setCashRegister(cashRegister);
        cashRegister.setCashier(cashier);
        cashRegister.setShop(superMarket);
        cashier.takeProductsFromCustomer(costumer.getCart().getProducts());

        //the cashier read the barcodes with the bar code reader,
        List<String> barCodes = cashier.readBarCodes();
        // and put them to the cash register that find the products and calculate the total amount
        int totalAmount = cashRegister.findProductsPriceByBarCode(barCodes);

        //the cash register print the total amount - parameter
        //System.out.println(totalAmount);

        //the cashier ask the costumer to pay with cash
        //the customer check its wallet to have enough money in there
        //the customer give the amount to the cashier
        //the cashier put the money to the cash register, and it prints a receipt
        String receipt = cashier.askTheCustomerToPay(PayType.CASH, totalAmount);

        //the cashier give the receipt to the costumer
        costumer.setReceipt(receipt);

        //end of shopping
        cashier.setCustomer(null);
        costumer.setShop(null);
        superMarket.getCustomers().remove(costumer);
    }
}
