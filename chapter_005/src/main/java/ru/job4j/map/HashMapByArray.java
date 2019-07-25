package ru.job4j.map;

import java.util.*;

public class HashMapByArray<K, V> implements Iterable<V> {
    private Entry[] container;
    private int maxSize;
    private final static double LOADFACTOR = 0.75;
    private int realSize = 0;
    private int modCount = 0;

    public HashMapByArray(int maxSize) {
        this.maxSize = maxSize;
        this.container = new Entry[maxSize];
    }

    public HashMapByArray() {
        this.maxSize = 16;
        this.container = new Entry[16];
    }

    private boolean capacityOk() {
        boolean res = true;
        double t = maxSize * LOADFACTOR;
        if (realSize > maxSize * LOADFACTOR) {
            res = false;
        }
        return res;
    }

    /**
     * добавляет пару.
     * Методы разрешения коллизий реализовывать не надо.
     * Если место пустое - записываем новую пару.
     * Если хеш указывает на ту же позицию и ключи равны, то возвращать false.
     */
    public boolean insert(K key, V value) {
        if (!capacityOk()) {
            growSizeAndReassignBuckets();
        }
        boolean res = false;
        int position = hash(key);
        Entry<K, V> oldEntry = container[position];
        if (oldEntry == null) {
            container[position] = new Entry<>(key, value);
            realSize++;
            res = true;
        }
        modCount++;
        return res;
    }

    public boolean delete(K key) {
        boolean res = false;
        int position = hash(key);
        if (container[position] != null) {
            container[position] = null;
            res = true;
        }
        return res;
    }


    /**
     * Перезаполнение массива после расширения размера
     */
    private void growSizeAndReassignBuckets() {
        Entry<K, V>[] oldContainer = container;
        maxSize *= 2;
        container = new Entry[maxSize * 2];
        for (Entry<K, V> entry : oldContainer) {
            if (entry != null) {
                container[hash(entry.key)] = entry;
            }
        }
    }

    /**
     * возвращает значение по ключу
     */
    public V get(K key) {
        V value = null;
        Entry<K, V> entry = container[hash(key)];
        if (entry != null) {
            value = entry.value;
        }
        return value;
    }

    /**
     * Получает позицию в массиве, номер корзины как поразрядное И хеша и (количество_бакетов - 1)
     */
    final int hash(Object key) {
        return (key == null) ? 0 : (key.hashCode() & (maxSize - 1));
    }



    public int getSize() {
        return this.maxSize;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int ind = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return ind < realSize;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Массив закончился");
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Недопустимые изменения массива");
                }
                return (V) container[ind++].value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Нельзя удалить из этого массива");
            }
        };
    }



    /**
     * Класс пары ключ - значение
     */
     protected class Entry<K, V> {
        final K key;
        V value;
       // Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<K, V> entry = (Entry<K, V>) o;
            return Objects.equals(key, entry.key)
                    && Objects.equals(value, entry.value);
        }

        @Override
            public int hashCode() {
                return Objects.hash(key, value);
        }

    }
}