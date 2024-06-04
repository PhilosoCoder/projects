package codekata.kata09backtothecheckout;

public record PricingRule(int price, int specialPrice, int specialQuantity) {

    public int calculatePrice(int quantity) {
        int specialOffers = quantity / specialQuantity;
        int regularItems = quantity % specialQuantity;

        return (specialOffers * specialPrice) + (regularItems * price);
    }

}
