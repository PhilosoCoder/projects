package codewars.directionsreduction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DirReductionTest {

    @Test
    void testSimpleDirReduc() {
        assertArrayEquals(
                new String[]{"WEST"},
                DirReduction.dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"})
        );

        assertArrayEquals(
                new String[]{},
                DirReduction.dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH"})
        );
    }

}