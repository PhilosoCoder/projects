package codewars.tribonaccisequence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class XbonacciTest {

    private static Xbonacci variabonacci;

    @BeforeAll
    public static void setUp() throws Exception {
        variabonacci = new Xbonacci();
    }

    @AfterAll
    public static void tearDown() throws Exception {
        variabonacci = null;
    }

    @Test
    void sampleTests() {
        double precision = 1e-10;
        assertArrayEquals(new double []{1,1,1,3,5,9,17,31,57,105}, variabonacci.tribonacci(new double []{1,1,1},10), precision);
        assertArrayEquals(new double []{0,0,1,1,2,4,7,13,24,44}, variabonacci.tribonacci(new double []{0,0,1},10), precision);
        assertArrayEquals(new double []{0,1,1,2,4,7,13,24,44,81}, variabonacci.tribonacci(new double []{0,1,1},10), precision);
    }

}