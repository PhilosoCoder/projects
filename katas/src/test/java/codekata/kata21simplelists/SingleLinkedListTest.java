package codekata.kata21simplelists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import codekata.kata21simplelists.singlelinked.SingleLinkedList;
import org.junit.jupiter.api.Test;

class SingleLinkedListTest {

    SingleLinkedList singleLinkedList = new SingleLinkedList();

    @Test
    void testSingleLinkedListAddAndGet() {
        assertEquals(-1, singleLinkedList.get("fred"));

        singleLinkedList.add("fred");
        assertEquals("fred", singleLinkedList.get(0));

        assertEquals(-1, singleLinkedList.get("wilma"));
        singleLinkedList.add("wilma");

        assertEquals("fred", singleLinkedList.get(0));
        assertEquals("wilma", singleLinkedList.get(1));

        assertEquals(List.of("fred", "wilma"), singleLinkedList.getElementsInList());
    }

    @Test
    void testSingleLinkedListRemoveAndList() {
        singleLinkedList.add("fred");
        singleLinkedList.add("wilma");
        singleLinkedList.add("betty");
        singleLinkedList.add("barney");
        assertEquals(List.of("fred", "wilma", "betty", "barney"), singleLinkedList.getElementsInList());

        singleLinkedList.removeAt(singleLinkedList.get("wilma"));
        assertEquals(List.of("fred", "betty", "barney"), singleLinkedList.getElementsInList());

        singleLinkedList.removeAt(singleLinkedList.get("barney"));
        assertEquals(List.of("fred", "betty"), singleLinkedList.getElementsInList());

        singleLinkedList.removeAt(singleLinkedList.get("fred"));
        assertEquals(List.of("betty"), singleLinkedList.getElementsInList());

        singleLinkedList.removeAt(singleLinkedList.get("betty"));
        assertEquals(List.of(), singleLinkedList.getElementsInList());
    }

}
