package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.lang.System.arraycopy;

/**
 * Создать динамический список на базе массива.
 * Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.
 * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора
 * коллекция подверглась структурному изменению, итератор должен кидать ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно
 * модифицирует коллекцию должна инкрементировать этот счетчик. В свою очередь итератор запоминает
 * значение этого счетчика на момент своего создания (expectedModCount), а затем на каждой итерации
 * сравнивает сохраненное значение, с текущим значением поля modCount,
 * если они отличаются, то генерируется исключение.
 */

public class DynamicArray<T> implements Iterable<T> {
    private Object[] container;
    private int size;
    private int index = 0;
    private int modCount = 0;

    public DynamicArray(int size) {
        this.size = size;
        this.container = new Object[size];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int ind = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return ind < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Массив закончился");
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Недопустимые изменения массива");
                }
                return (T) container[ind++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Нельзя удалить из этого массива");
            }
        };
    }

    /**
     * добавляет указанный элемент в первую свободную ячейку;
     */
    public  void add(T value) {
        if (index >= size) {
            increaseSize();
        }
        container[index++] = value;
        modCount++;
    }


    private void increaseSize() {
        Object[] newContainer = new Object[size * 2];
        arraycopy(container, 0, newContainer, 0, size);
        size *= 2;
        container = new Object[size];
        container = newContainer;
    }


    /**
     * возвращает элемент, расположенный по указанному индексу;
     */
    public T get(int ind) {
        if (ind >= size) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        return (T) this.container[ind];
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

}
