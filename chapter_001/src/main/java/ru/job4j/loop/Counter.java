package ru.job4j.loop;

public class Counter {
    public int add(int start, int finish) {
        int sum = (start%2 == 0) ?  start : start + 1;
        start = sum;
        while (start < finish) {
            start = start + 2;
            sum += start;
        }
        return sum;
    }
}