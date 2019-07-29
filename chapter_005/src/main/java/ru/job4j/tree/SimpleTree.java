package ru.job4j.tree;

import java.util.*;

//E extends Comparable<E> означает: тип E, который может сравниваться с другими объектами того же типа E.

public class SimpleTree<E extends Comparable<E>> implements STree<E>  {
    private Node<E> root = null;
    private boolean balanced = true;
    private int modCount = 0;

    public  SimpleTree(E rootValue) {
        this.root = new Node<E>(rootValue);
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     */
    public boolean add(E parent, E child) {
        boolean res = false;
        Optional<Node<E>>  parentNode = findBy(parent);
        if (!parentNode.isEmpty()) {
            parentNode.get().add(new Node<E>(child));
            res = true;
            modCount++;
        }
        return res;
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
            Node<E> nextEl = root;
            Queue<Node<E>> data = new LinkedList<>();
            int expectedModCount = modCount;
            boolean firstMove = true;

            private void fillData(Node<E> el) {
                data.offer(el);
                for (Node<E> child : el.leaves()) {
                    fillData(child);
                }
            }


            @Override
            public boolean hasNext() {
                boolean nodeExists = false;
                if (firstMove) {
                    fillData(nextEl);
                    firstMove = false;
                }

                if (!data.isEmpty()) {
                    nodeExists = true;
                }
                return nodeExists;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Данные закончились");
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Недопустимые изменения массива");
                }
                nextEl = data.poll();
                return nextEl.getValue();
            }
        };
    }

}


