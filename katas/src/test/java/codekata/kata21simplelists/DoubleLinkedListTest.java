package codekata.kata21simplelists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import codekata.kata21simplelists.doublelinked.DoubleLinkedList;
import org.junit.jupiter.api.Test;

class DoubleLinkedListTest {

    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

    @Test
    void testDoubleLinkedListAddAndGet() {
        assertEquals(-1, doubleLinkedList.get("fred"));

        doubleLinkedList.add("fred");
        assertEquals("fred", doubleLinkedList.get(0));

        assertEquals(-1, doubleLinkedList.get("wilma"));
        doubleLinkedList.add("wilma");

        assertEquals("fred", doubleLinkedList.get(0));
        assertEquals("wilma", doubleLinkedList.get(1));

        assertEquals(List.of("fred", "wilma"), doubleLinkedList.getElementsInList());
    }

    @Test
    void testDoubleLinkedListRemoveAndList() {
        doubleLinkedList.add("fred");
        doubleLinkedList.add("wilma");
        doubleLinkedList.add("betty");
        doubleLinkedList.add("barney");
        assertEquals(List.of("fred", "wilma", "betty", "barney"), doubleLinkedList.getElementsInList());

        doubleLinkedList.removeAt(doubleLinkedList.get("wilma"));
        assertEquals(List.of("fred", "betty", "barney"), doubleLinkedList.getElementsInList());

        doubleLinkedList.removeAt(doubleLinkedList.get("barney"));
        assertEquals(List.of("fred", "betty"), doubleLinkedList.getElementsInList());

        doubleLinkedList.removeAt(doubleLinkedList.get("fred"));
        assertEquals(List.of("betty"), doubleLinkedList.getElementsInList());

        doubleLinkedList.removeAt(doubleLinkedList.get("betty"));
        assertEquals(List.of(), doubleLinkedList.getElementsInList());
    }

}
