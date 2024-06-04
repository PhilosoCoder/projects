package codekata.kata21simplelists.singlelinked;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedList {

    private SingleLinkedNode head;
    private int size;

    public SingleLinkedList() {
        this.size = 0;
        this.head = null;
    }

    public void add(String data) {
        SingleLinkedNode node = new SingleLinkedNode(data);
        SingleLinkedNode current = this.head;

        if (this.head == null) {
            this.head = node;
            this.head.setNext(null);
            this.size = 1;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(node);
            node.setNext(null);
            this.size++;
        }
    }

    public void add(int index, String data) {
        SingleLinkedNode node = new SingleLinkedNode(data);
        SingleLinkedNode current = this.head;
        if (this.head != null && index <= this.size) {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
            this.size++;
        } else {
            System.out.println("Invalid index add(index, data) ->" + index);
        }
    }

    public String get(int index) {
        SingleLinkedNode node = this.head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        }
        return node.getData();
    }

    public int get(String data) {
        SingleLinkedNode node = this.head;
        if (head != null) {
            for (int i = 0; i < this.size; i++) {
                if (node.getData().equals(data)) {
                    return i;
                }
                node = node.getNext();
            }
        }
        return -1;
    }

    public void remove(String data) {
        if (this.head != null) {
            SingleLinkedNode current = this.head;
            SingleLinkedNode prev = null;
            while (current != null) {
                if (current.getData().equals(data)) {
                    if (prev == null) {
                        this.head = current.getNext();
                    } else {
                        prev.setNext(current.getNext());
                    }
                    this.size--;
                    break;
                }
                prev = current;
                current = current.getNext();
            }
        } else {
            System.out.println("Invalid data remove() -> " + data);
        }
    }

    public void removeAt(int index) {
        if (index >= 0 && index < this.size && this.head != null) {
            SingleLinkedNode current = this.head;
            SingleLinkedNode prev = null;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.getNext();
            }
            if (prev == null) {
                this.head = current.getNext();
            } else {
                prev.setNext(current.getNext());
            }
            this.size--;
        } else {
            System.out.println("Invalid index removeAt() -> " + index);
        }
    }

    public List<String> getElementsInList() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            result.add(this.get(i));
        }
        return result;
    }

}
