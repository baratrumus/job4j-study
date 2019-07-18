package ru.job4j.list;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack
 */

public class SimpleStack<E> {

    //получаем связанный список через композицию
    private DynamicLinkedList<E> dLL = new DynamicLinkedList<>();

    /**
     * должен возвращать значение и удалять его из коллекции.
     */
    public E poll() {
        E res = dLL.removeLast();

        return res;
    }


    /**
     * помещает значение в коллекцию.
     */
    public void push(E value) {
        dLL.add(value);
    }

    public int getSize() {
        return dLL.size;
    }
}
