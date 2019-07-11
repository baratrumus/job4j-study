package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

/**
 * необходимо сделать универсальную обертку над массивом.
*/
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size;
    private int index = 0;

    public SimpleArray(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int ind;
            @Override
            public boolean hasNext() {
                return ind < size;
            }

            @Override
            public T next() {
               if (!hasNext()) {
                   throw new NoSuchElementException("Массив закончился");
               }
               return (T) array[ind++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Нельзя удалить из этого массива");
            }
        };
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     */
    public  void add(T model) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Мест в массиве больше нет");
        }
        array[index++] = model;
    }

    /**
     *заменяет указанным элементом (model) элемент, находящийся по индексу index;
     */
    public void set(int ind, T model) {
        if (ind >= size) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        array[ind] = model;
    }

    /**
     * удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо
     * сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
     */
    public void remove(int ind) {
        if (ind >= size) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        arraycopy(array, ind + 1, array, ind, size - ind - 1);
        array[size - 1] = null;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     */
    public T get(int ind) {
        if (ind >= size) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        return (T) this.array[ind];
    }

}
