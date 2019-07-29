package ru.job4j.tree;

import java.util.*;


public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children;
    private final E value;

    public Node(final E value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public E getValue() {
        return this.value;
    }

    /**
     * Добавляет потомка
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Геттер потомков ноды
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;    }
}



