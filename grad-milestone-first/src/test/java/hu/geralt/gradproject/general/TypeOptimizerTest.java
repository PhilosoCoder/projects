package hu.geralt.gradproject.general;

import static hu.geralt.gradproject.general.TypeOptimizer.convertToPrimitive;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TypeOptimizerTest {

    @Test
    void testConvertToPrimitive() {
        assertEquals('h', (char) convertToPrimitive("h"));
        assertEquals("hello", convertToPrimitive("hello"));
        assertEquals((byte) 2, (byte) convertToPrimitive("2"));
        assertEquals((short) 130, (short) convertToPrimitive("130"));
        assertEquals(33333333, (int) convertToPrimitive("33333333"));
        assertEquals(4444444444444444L, (long) convertToPrimitive("4444444444444444"));
        assertEquals(12.3f, (float) convertToPrimitive("12.3"));
        assertEquals(444444444444444444444444444444444444444444444444.44444444,
                convertToPrimitive("444444444444444444444444444444444444444444444444.44444444"));
        assertEquals(true, convertToPrimitive("true"));
    }

}