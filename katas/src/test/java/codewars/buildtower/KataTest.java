package codewars.buildtower;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KataTest {

    @Test
    void basicTests() {
        assertEquals(String.join(",", "*"), String.join(",", Kata.towerBuilder(1)));
        assertEquals(String.join(",", " * ", "***"), String.join(",", Kata.towerBuilder(2)));
        assertEquals(String.join(",", "  *  ", " *** ", "*****"), String.join(",", Kata.towerBuilder(3)));
    }

}