package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DynamicLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    private int modCount = 0;

    public DynamicLinkedList() {
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> pointer = first;
            Node<E> ret;
            int ind = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return ind < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Лист закончился");
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Недопустимые изменения");
                }
                ind++;
                ret = pointer;
                pointer =  pointer.next;
                return ret.data;
            }

            @Override
            public void remove() {


            }
        };
    }

    /**
     * добавляет  элемент  в конец списка
     */
    public  void add(E value) {
        final Node<E> tmp = last;
        final Node<E> newNode = new Node<>(value);
        if (size == 0) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        last = newNode;
        modCount++;
        size++;
    }



    /**
     * Метод получения элемента по индексу.
     */
    public E get(int ind) {
        if (ind >= size) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        Node<E> result = this.first;
        for (int i = 0; i < ind; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }
}
