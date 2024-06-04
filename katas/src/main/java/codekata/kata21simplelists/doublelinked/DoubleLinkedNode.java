package codekata.kata21simplelists.doublelinked;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleLinkedNode {

    private String data;
    private DoubleLinkedNode next;
    private DoubleLinkedNode prev;

    public DoubleLinkedNode(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}
