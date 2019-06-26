package filterpupils;


import org.junit.Test;
import ru.job4j.filterpupils.*;

import java.util.List;
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
        List<Student> result = sch.collect(students, s -> s.score >= 0 && s.score <= 50);
        List<Student> expected = List.of(
                new Student("Student #1", 10),
                new Student("Student #2", 50));
        assertThat(result, is(expected));
    }

    @Test
    public void whenFrom50to70() {
        School sch = new School();
        List<Student> result = sch.collect(students, s -> s.score > 50 && s.score <= 70);
        List<Student> expected = List.of(
                new Student("Student #3", 60),
                new Student("Student #4", 57));
        assertThat(result, is(expected));
    }


    @Test
    public void whenFrom70to100() {
        School sch = new School();
        List<Student> result = sch.collect(students, s -> s.score > 70 && s.score <= 100);
        List<Student> expected = List.of(
                new Student("Student #5", 80),
                new Student("Student #6", 90));
        assertThat(result, is(expected));
    }

}
