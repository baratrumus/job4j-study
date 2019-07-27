package ru.job4j.tree;

import java.util.*;


public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children;
    private final E value;
    public Node<E> left;
    public Node<E> right;
    public Node<E> parent;

    public Node(final E value) {
        this.value = value;
        children = new ArrayList<>();
    }

    /**
     * Добавляет потомка
     */
    public void add(Node<E> child) {
        //this.children.add(child);
    }

    /**
     * Геттер потомков ноды
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * equals для значения
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;    }

/*
    public int compareTo(E o) {
        return this.value.compareTo(o);
    }*/
}



