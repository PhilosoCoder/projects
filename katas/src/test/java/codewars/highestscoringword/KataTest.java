package codewars.highestscoringword;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KataTest {

    @Test
    void sampleTests() {
        assertEquals("taxi", Kata.high("man i need a taxi up to ubud"));
        assertEquals("volcano", Kata.high("what time are we climbing up to the volcano"));
        assertEquals("semynak", Kata.high("take me to semynak"));
    }

    @Test
    void edgeCaseTests() {
        assertEquals("aa", Kata.high("aa b"));
        assertEquals("b", Kata.high("b aa"));
        assertEquals("bb", Kata.high("bb d"));
        assertEquals("d", Kata.high("d bb"));
        assertEquals("aaa", Kata.high("aaa b"));
    }

}