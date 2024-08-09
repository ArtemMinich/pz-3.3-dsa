package org.example;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Josephus");
        System.out.print("N: ");
        int numberOfElements = getCorrectNumber();
        System.out.print("M: ");
        int removalStep = getCorrectNumber();

        CircularLinkedList circularLinkedList = createCircularLinkedList(numberOfElements);

        System.out.print("List: ");
        circularLinkedList.print();
        System.out.println("\nDelete sequence: " + circularLinkedList.findLastElement(removalStep).toString());

    }

    private static int getCorrectNumber() {
        int number = 0;
        while (true){
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if(number>0){
                    break;
                }
                else{
                    System.out.println("Enter number over 0 !!!");
                }
            }
            else {
                System.out.println("Enter only number !!!");
                scanner.next();
            }
        }
        return number;
    }

    private static CircularLinkedList createCircularLinkedList(int numberOfElements) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        for (int i = 0; i < numberOfElements; i++) {
            circularLinkedList.addNode(i);
        }
        return circularLinkedList;
    }


}