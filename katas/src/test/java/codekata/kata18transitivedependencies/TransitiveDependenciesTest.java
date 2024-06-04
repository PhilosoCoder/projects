package codekata.kata18transitivedependencies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class TransitiveDependenciesTest {

    @Test
    void testBasic() {
        TransitiveDependencies dep = new TransitiveDependencies();
        dep.addDirect("A", List.of("B", "C"));
        dep.addDirect("B", List.of("C", "E"));
        dep.addDirect("C", List.of("G"));
        dep.addDirect("D", List.of("A", "F"));
        dep.addDirect("E", List.of("F"));
        dep.addDirect("F", List.of("H"));

        assertEquals(List.of("B", "C", "E", "F", "G", "H"), dep.dependenciesFor("A"));
        assertEquals(List.of("C", "E", "F", "G", "H"), dep.dependenciesFor("B"));
        assertEquals(List.of("G"), dep.dependenciesFor("C"));
        assertEquals(List.of("A", "B", "C", "E", "F", "G", "H"), dep.dependenciesFor("D"));
        assertEquals(List.of("F", "H"), dep.dependenciesFor("E"));
        assertEquals(List.of("H"), dep.dependenciesFor("F"));
    }

}