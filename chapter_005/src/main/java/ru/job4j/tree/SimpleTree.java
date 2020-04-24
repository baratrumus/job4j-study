package ru.job4j.tree;

import java.util.*;

//E extends Comparable<E> означает: тип E, который может сравниваться с другими объектами того же типа E.

public class SimpleTree<E extends Comparable<E>> implements STree<E>  {
    private Node<E> root;
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
        if (!parentNode.isEmpty() && isUnique(child)) {
            parentNode.get().add(new Node<E>(child));
            res = true;
            modCount++;
        }
        return res;
    }

    /**
     * проверка что ребёнок уникален для всего дерева
     */
    private boolean isUnique(E el) {
        boolean unique = true;
        Iterator it  = this.iterator();
        E elem;
        while (it.hasNext()) {
            elem = (E) it.next();
            if (elem.equals(el)) {
                unique = false;
                break;
            }
        }
        return unique;
    }


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


    /**
      * Метод должен проверять количество дочерних элементов в дереве. Если их <= 2 - то дерево бинарное.
     */
    public boolean isBinary() {
        boolean binary = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                binary = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return binary;
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
                return (E) nextEl.getValue();
            }
        };
    }

}


