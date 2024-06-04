package codewars.detectpangram;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PangramCheckerTest {

    @Test
    void test1() {
        String pangram1 = "The quick brown fox jumps over the lazy dog.";
        PangramChecker pc = new PangramChecker();
        assertTrue(pc.check(pangram1));
    }
    @Test
    void test2() {
        String pangram2 = "You shall not pass!";
        PangramChecker pc = new PangramChecker();
        assertFalse(pc.check(pangram2));
    }

}