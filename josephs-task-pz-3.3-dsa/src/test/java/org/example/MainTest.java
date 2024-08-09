package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void corresponds_To_The_Example_From_The_Task() {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        int numberOfElements = 7;
        int removalStep = 2;
        for (int i = 0; i < numberOfElements; i++) {
            circularLinkedList.addNode(i);
        }
        assertEquals("[1, 3, 5, 0, 4, 2, 6]", circularLinkedList
                .findLastElement(removalStep)
                .toString());
    }
}