package ru.job4j.collections.prioritylinkedlist;


public class LinkList {
 /*

    private Node first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(int x) {
        Node newNode = new Node(x);
        Node previous = null;
        Node current = first;
        while (current != null && x < current.iData) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            newNode.next = first;
            first = newNode;
        } else {
            previous.next = newNode;
            newNode.next = current;
        }
    }

    public Node remove() {
        Node previous = null;
        Node current = first;
        Node temp = current;
        while (current.next != null) {
            previous = current; current = current.next;
        }
        previous.next = null;
        return temp;
    }

    public void display() {
        Node current = first;
        while (current != null) {
            current.displayNode(); current = current.next;
        }
        System.out.println(" "); }

        */
}
