package ru.job4j.streamtask;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamTask {
    /**
     * Задан массив чисел.
     * 1. Нужно его отфильтровать, оставить только четные числа.
     * 2. Каждое число возвести в квадрат.
     * 3. И все элементы просуммировать.
     */

    public Integer streamTask(Integer[] arr) {
        Integer result;
        Stream<Integer> st = Arrays.stream(arr);
                Optional<Integer> res = st.filter(s -> s % 2 == 0)
                  .map(s -> s * s)
                  .reduce(Integer::sum);
        result = res.orElse(0);
        return result;
    }
}
