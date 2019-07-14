package ru.job4j.list;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack
 */

public class SimpleStack<E> extends DynamicLinkedList {


    /**
     * должен возвращать значение и удалять его из коллекции.
     */
    public E poll() {
        E res = null;
        if (size == 1) {
            res = (E) this.first.data;
            this.first.data = null;
            size = 0;
        }
        if (size > 1) {
            Node<E> n = this.last;
            res = n.data;
            n.data = null;
            this.last = get(size - 2);
            this.last.next = null;
            this.size--;
        }
        return res;
    }


    /**
     * помещает значение в коллекцию.
     */
    public void push(E value) {
        add(value);
    }
}
