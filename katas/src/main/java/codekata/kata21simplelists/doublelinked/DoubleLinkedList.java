package codekata.kata21simplelists.doublelinked;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList {

    private DoubleLinkedNode head;
    private int size;

    public DoubleLinkedList() {
        this.size = 0;
        this.head = null;
    }

    public void add(String data) {
        DoubleLinkedNode node = new DoubleLinkedNode(data);
        DoubleLinkedNode current = this.head;

        if (this.head == null) {
            this.head = node;
            this.head.setNext(null);
            this.size = 1;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(node);
            node.setPrev(current);
            node.setNext(null);
            this.size++;
        }
    }

    public void add(int index, String data) {
        DoubleLinkedNode node = new DoubleLinkedNode(data);
        DoubleLinkedNode current = this.head;
        if (this.head != null && index <= this.size) {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrev(node);
            }
            current.setNext(node);
            node.setPrev(current);
            this.size++;
        } else {
            System.out.println("Invalid index add(index, data) ->" + index);
        }
    }

    public String get(int index) {
        DoubleLinkedNode node = this.head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        }
        return node.getData();
    }

    public int get(String data) {
        DoubleLinkedNode node = this.head;
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
            DoubleLinkedNode current = this.head;
            DoubleLinkedNode prev = null;
            for (int i = 0; i < this.size; i++) {
                if (current.getData().equals(data)) {
                    prev.setNext(current.getNext());
                    if (current.getNext() != null) {
                        current.getNext().setPrev(prev);
                    }
                    this.size--;
                    break;
                }
                prev = current;
                current = current.getNext();
            }
        } else {
            System.out.println("Invalid data removeAt() ->" + data);
        }
    }


    public void removeAt(int index) {
        if (index >= 0 && index < this.size && this.head != null) {
            DoubleLinkedNode current = this.head;
            DoubleLinkedNode prev = null;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.getNext();
            }
            if (prev != null) {
                prev.setNext(current.getNext());
                if (current.getNext() != null) {
                    current.getNext().setPrev(prev);
                }
            } else {
                this.head = current.getNext();
                if (this.head != null) {
                    this.head.setPrev(null);
                }
            }
            this.size--;
        } else {
            System.out.println("Invalid index removeAt() ->" + index);
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
