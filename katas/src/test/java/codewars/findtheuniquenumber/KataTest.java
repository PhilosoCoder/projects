package codewars.findtheuniquenumber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KataTest {

    @Test
    void sampleTestCases() {
        double precision = 0.0000000000001;
        assertEquals(1.0, Kata.findUniq(new double[]{0, 1, 0}), precision);
        assertEquals(2.0, Kata.findUniq(new double[]{1, 1, 1, 2, 1, 1}), precision);
    }

}