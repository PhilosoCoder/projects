package codewars.breakcamelcase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void tests() {
        assertEquals("camel Casing", Solution.camelCase("camelCasing"));
        assertEquals("camel Casing Test", Solution.camelCase("camelCasingTest"));
        assertEquals("camelcasingtest", Solution.camelCase("camelcasingtest"));
    }

}