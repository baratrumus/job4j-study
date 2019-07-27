package ru.job4j.tree;

import java.util.*;

//E extends Comparable<E> означает: тип E, который может сравниваться с другими объектами того же типа E.

public class SimpleTree<E extends Comparable<E>> implements STree<E>  {
    private Node<E> root = null;
    private boolean balanced = true;


    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     */
    public boolean add(E parent, E child) {
        return true;
    };


    /**
     * Поиск по значению. алгоритм поиска в ширину.
     * Мы берем очередь и добавляем первый элемент дерева - это корень.
     * Дальше, если корень не наш элемент мы добавляем все элементы корня.
     * И так для каждого элемента.
     */
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

}


