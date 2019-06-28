package ru.job4j.filterpupils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * В школе пришло требование разделить 9А класс на три класса.
 * У каждого ученика есть общий балл по всем предметам.  int score;
 * score - может быть в диапазоне от 0 до 100.
 * Учеников нужно поделить на следующие группы.
 * 10А диапазон балла [70: 100],
 * 10Б диапазон балла [50: 70);
 * 10B диапазон балла (0: 50);
 */

public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students
                .stream()
                .filter(predict)
                .collect(Collectors.toList());
    }

    /**
     * Преобразовать список учеников в Map
     * ключа фамилия ученика
     * значение объект ученика
     */
    public Map<String, Student> studentMap(List<Student> students) {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toMap(Student::getName, s -> s));
    }


    /**
     * Метод должен вернуть список студентов у которых балл аттестата больше bound.
     *
     * Во входящем списки могут быть null элементы.
     * Порядок действий.
     *  - Отсортировать список.
     *  - Используя метод flatMap убрать null
     *  - Используя метод takeWhile получить нужный поток.
     *  - Сохранить поток в List.
     * @param students
     * @param bound
     * @return
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students
                .stream()
                .flatMap(o -> Optional.ofNullable(o).stream())
                .sorted((o1, o2) -> o2.getScore() - o1.getScore())
                .takeWhile(o -> o.getScore() > bound)
                .collect(Collectors.toList());
    }
}
