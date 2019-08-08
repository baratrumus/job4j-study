package ru.job4j.analize;

import java.util.*;

/**
 * Задан список скриптов с указанием их зависимостей.
 * 1 - [2, 3], 2 - [4], 3 - [4, 5], 4 - [], 5 - []
 * Необходим написать метод, который возвращает список всех скриптов,
 * которые нужно вызвать для загрузки входящего скрипта.
 * Например, чтобы выполнить скрипт 1. нужно выполнить скрипт (2, 3),
 * которые в свою очередь зависят от 4 и 5 скрипта.
 */
public class Scripts {
    private Map<Integer, List<Integer>> dependencies;
    private Set<Integer> depForValue;

    Scripts(Map<Integer, List<Integer>> dependency) {
        this.dependencies = dependency;
        depForValue = new HashSet<>();
    }

    List load(Integer scriptId) {
        depForValue.clear();
        List<Integer> list = new ArrayList<>();
        getLoadsSet(scriptId);
        list.addAll(depForValue);
        return list;
    }

    /**
     * Проводим поиск зависимостей через сет чтобы избежать дубликатов
     */
    void getLoadsSet(Integer scriptId) {
        Queue<Integer> data = new LinkedList<>();
        Integer scriptValue;
        data.offer(scriptId);
        while (!data.isEmpty()) {
            scriptValue = data.poll();
            depForValue.add(scriptValue);
            for (Integer dependValues : dependencies.get(scriptValue)) {
                data.offer(dependValues);
            }
        }
        depForValue.remove(scriptId);
    }

}

