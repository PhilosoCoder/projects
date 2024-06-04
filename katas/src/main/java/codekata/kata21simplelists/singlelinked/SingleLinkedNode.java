package codekata.kata21simplelists.singlelinked;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleLinkedNode {
    private String data;
    private SingleLinkedNode next;

    public SingleLinkedNode(String data) {
        this.data = data;
        this.next = null;
    }
}