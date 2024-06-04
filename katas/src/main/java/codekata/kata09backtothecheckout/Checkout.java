package codekata.kata09backtothecheckout;

import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private final Map<Character, PricingRule> pricingRules;
    private final Map<Character, Integer> itemCounts;

    public Checkout(Map<Character, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
        this.itemCounts = new HashMap<>();
    }

    public void scan(char item) {
        itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
    }

    public int getTotal() {
        int totalPrice = 0;

        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char item = entry.getKey();
            int count = entry.getValue();

            PricingRule rule = pricingRules.getOrDefault(item, new PricingRule(0, 0, 1));

            totalPrice += rule.calculatePrice(count);
        }

        return totalPrice;
    }

}
