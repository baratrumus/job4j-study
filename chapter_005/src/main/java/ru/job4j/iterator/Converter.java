package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Метод convert должен принимать объект итератор итератор и возвращать Итератор чисел.
 * Iterator<Iterator<Integer> - ((4 2 0 4 6 4 9), (0 9 8 7 5), (1 3 5 6 7 0 9 8 4))
 * Метод должен возвращать
 *Iterator<Integer> - (4 2 0 4 6 4 9 0 9 8 7 5 1 3 5 6 7 0 9 8 4)
 */

public class Converter {

    /**
     *  it  наружный итератор
     *  innerIterator внутренний
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> innerIterator = null;

            @Override
            public boolean hasNext() {
                //пока внешний имеет next либо внутренний находится внутри подмассива
                while (it.hasNext() || innerIterator != null) {
                    //если подмассив закончился переходим к след
                    if (innerIterator == null) {
                        innerIterator = it.next();
                    }
                    if (innerIterator.hasNext()) {
                        return true;
                    } else {
                        innerIterator = null;
                    }
                }
                return false;
            }

            @Override
            public Integer next() {

                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                return innerIterator.next();
            }
        };
    }
}
