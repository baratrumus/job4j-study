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
    private int maxSize = 0;
    private int indexSize = 0;
    private int modCount = 0;

    public DynamicArray(int maxSize) {
        this.maxSize = maxSize;
        this.container = new Object[maxSize];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int ind = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return ind < indexSize;
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
        if (indexSize >= maxSize) {
            increaseSize();
        }
        container[indexSize++] = value;
        modCount++;
    }


    private void increaseSize() {
        Object[] newContainer = new Object[maxSize * 2];
        arraycopy(container, 0, newContainer, 0, maxSize);
        maxSize *= 2;
        container = new Object[maxSize];
        container = newContainer;
    }


    /**
     * возвращает элемент, расположенный по указанному индексу;
     */
    public T get(int ind) {
        if (ind >= indexSize) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        return (T) this.container[ind];
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.indexSize;
    }

}
