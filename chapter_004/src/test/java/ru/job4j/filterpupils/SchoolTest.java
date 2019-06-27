package ru.job4j.filterpupils;


import org.junit.Test;
import ru.job4j.filterpupils.*;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {

    List<Student> students = List.of(
            new Student("Student #1", 10),
            new Student("Student #2", 50),
            new Student("Student #3", 60),
            new Student("Student #4", 57),
            new Student("Student #5", 80),
            new Student("Student #6", 90)
    );

    @Test
    public void whenFrom0to50() {
        School sch = new School();
        List<Student> result = sch.collect(students, s -> s.getScore() >= 0 && s.getScore() <= 50);
        List<Student> expected = List.of(
                new Student("Student #1", 10),
                new Student("Student #2", 50));
        assertThat(result, is(expected));
    }

    @Test
    public void whenFrom50to70() {
        School sch = new School();
        List<Student> result = sch.collect(students, s -> s.getScore() > 50 && s.getScore() <= 70);
        List<Student> expected = List.of(
                new Student("Student #3", 60),
                new Student("Student #4", 57));
        assertThat(result, is(expected));
    }


    @Test
    public void whenFrom70to100() {
        School sch = new School();
        List<Student> result = sch.collect(students, s -> s.getScore() > 70 && s.getScore() <= 100);
        List<Student> expected = List.of(
                new Student("Student #5", 80),
                new Student("Student #6", 90));
        assertThat(result, is(expected));
    }

    @Test
    public void whenFromListToMap() {
        School sch = new School();
        Map<String, Student> result = sch.studentMap(students);
        Map<String, Student> expected = Map.of(
                "Student #1", new Student("Student #1", 10),
                "Student #2", new Student("Student #2", 50),
                "Student #3", new Student("Student #3", 60),
                "Student #4", new Student("Student #4", 57),
                "Student #5", new Student("Student #5", 80),
                "Student #6", new Student("Student #6", 90));
        result.forEach((k, v) -> System.out.println(k + ": " + v));
        assertThat(result, is(expected));
    }

}
