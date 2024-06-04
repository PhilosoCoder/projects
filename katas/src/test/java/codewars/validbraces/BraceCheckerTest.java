package codewars.validbraces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BraceCheckerTest {

    @Test
    void testValid() {
        assertTrue(BraceChecker.isValid("()"));
    }

    @Test
    void testInvalid() {
        assertFalse(BraceChecker.isValid("[(])"));
    }

}