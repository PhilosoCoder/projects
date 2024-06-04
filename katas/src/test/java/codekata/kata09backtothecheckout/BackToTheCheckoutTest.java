package codekata.kata09backtothecheckout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class BackToTheCheckoutTest {

    @Test
    void testGetTotals() {
        Map<Character, PricingRule> pricingRules = createPricingRules();

        assertEquals(0, getPrice(pricingRules, ""));
        assertEquals(50, getPrice(pricingRules, "A"));
        assertEquals(80, getPrice(pricingRules, "AB"));
        assertEquals(115, getPrice(pricingRules, "CDBA"));
        assertEquals(100, getPrice(pricingRules, "AA"));
        assertEquals(130, getPrice(pricingRules, "AAA"));
        assertEquals(180, getPrice(pricingRules, "AAAA"));
        assertEquals(230, getPrice(pricingRules, "AAAAA"));
        assertEquals(260, getPrice(pricingRules, "AAAAAA"));
        assertEquals(160, getPrice(pricingRules, "AAAB"));
        assertEquals(175, getPrice(pricingRules, "AAABB"));
        assertEquals(190, getPrice(pricingRules, "AAABBD"));
        assertEquals(190, getPrice(pricingRules, "DABABA"));
    }

    @Test
    void testIncremental() {
        Checkout co = new Checkout(createPricingRules());

        assertEquals(0, co.getTotal());
        co.scan('A');
        assertEquals(50, co.getTotal());
        co.scan('B');
        assertEquals(80, co.getTotal());
        co.scan('A');
        assertEquals(130, co.getTotal());
        co.scan('A');
        assertEquals(160, co.getTotal());
        co.scan('B');
        assertEquals(175, co.getTotal());
    }

    private int getPrice(Map<Character, PricingRule> pricingRules, String goods) {
        Checkout co = new Checkout(pricingRules);
        goods.chars().forEach(item -> co.scan((char) item));
        return co.getTotal();
    }

    private Map<Character, PricingRule> createPricingRules() {
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new PricingRule(50, 130, 3));
        pricingRules.put('B', new PricingRule(30, 45, 2));
        pricingRules.put('C', new PricingRule(20, 20, 1));
        pricingRules.put('D', new PricingRule(15, 15, 1));
        return pricingRules;
    }

}