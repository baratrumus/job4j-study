package ru.job4j.list;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Написать алгоритм определяющий, что список содержит замыкания.
 * Обратите внимание, что список может быть замкнут и в середине.
 * К примеру, 3-й узел будет ссылаться на 2-й узел. Определение зацикленности
 * необходимо реализовать путем прохода по узлам, без использования коллекций.
 */
public class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
            this.data = data;
        }

    boolean hasNext() {
         return next != null;
    }

    /**
     * алгоритм зайца и черепахи
     */
    boolean hasCycle(Node<E> first) {
        boolean res = false;
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return res;
    }

}
