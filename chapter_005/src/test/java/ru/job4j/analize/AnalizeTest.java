package ru.job4j.analize;


import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenGrowSizeAndReassignBucketsItsOk() {
        List<Analize.Users> previous = List.of(new Analize.Users(1, "Pavel"),
                new Analize.Users(2, "Vasiliy"),
                new Analize.Users(3, "Andery"),
                new Analize.Users(4, "Gosha"));

        List<Analize.Users> current = List.of(new Analize.Users(1, "Pavel"),
                new Analize.Users(2, "Petr"),
                new Analize.Users(3, "Anton"),
                new Analize.Users(5, "Lisa"),
                new Analize.Users(6, "Kara"),
                new Analize.Users(7, "Mara"));


        Analize analize = new Analize();
        Analize.Info result = analize.diff(previous, current);

        assertThat(result.changed, is(2));
        assertThat(result.added, is(3));
        assertThat(result.deleted, is(3));
    }


}
