package org.example;

import lombok.Data;

import java.util.LinkedList;

@Data
public class CircularLinkedList {

    private Node head;

    private Node tail;

    private int size;

    public boolean containsNode(int searchValue) {
        Node currentNode = head;

        if (head == null) {
            return false;
        } else {
            do {
                if (currentNode.value == searchValue) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
            return false;
        }
    }

    public void addNode(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
        }

        tail = newNode;
        tail.nextNode = head;
        size++;
    }

    public void deleteNode(int valueToDelete) {
        Node currentNode = head;
        if (head == null) {
            return;
        }
        do {
            Node nextNode = currentNode.nextNode;
            if (nextNode.value == valueToDelete) {
                if (tail == head) {
                    head = null;
                    tail = null;
                } else {
                    currentNode.nextNode = nextNode.nextNode;
                    if (head == nextNode) {
                        head = head.nextNode;
                    }
                    if (tail == nextNode) {
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
        size--;
    }

    public LinkedList<Integer> findLastElement(int removalStep){
        LinkedList<Integer> result = new LinkedList<>();
        Node currentNode = head;
        do{
            for (int i = 1; i < removalStep; i++) {
                currentNode = currentNode.nextNode;
            }
            Node deletingNode = currentNode;
            currentNode = currentNode.nextNode;
            deleteNode(deletingNode.value);
            result.add(deletingNode.value);
        }while (size >= 1);
        return result;
    }

    public void print() {
        Node currentNode = head;
        if (head != null) {
            do {
                System.out.print(currentNode.value + " ");
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
        }
    }

    static class Node {
        int value;
        Node nextNode;

        public Node(int value) {
            this.value = value;
        }
    }
}
