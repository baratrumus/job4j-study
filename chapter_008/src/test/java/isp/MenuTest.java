package isp;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MenuTest {
    StartMenu sm = new StartMenu();


    @Test
    public void whenAddElementToRootItsAdded() {
        String elNum = sm.getMenu().addElement("element 1", Optional.empty(),
                "1", sm.new AddElement());
        assertThat(elNum, is("1"));
    }

    @Test
    public void whenAddElementToSubMenuItsAdded() {
        String elNum = sm.getMenu().addElement("element 1", Optional.empty(),
                "1", sm.new AddElement());
        elNum = sm.getMenu().addElement("element 1.1", Optional.of(elNum),
                "1", sm.new AddElement());
        assertThat(elNum, is("1.1"));
    }

    @Test
    public void whenAddElementToWrongParentItsNull() {
        String elNum = sm.getMenu().addElement("element 1", Optional.of("1.1.1.1"),
                "1", sm.new AddElement());
        assertNull(elNum);
    }

    @Test
    public void whenFindElementByInputKeyItsFound() {
        String elNum = sm.getMenu().addElement("element 1", Optional.empty(),
                "ttt", sm.new AddElement());
        elNum = sm.getMenu().addElement("element 1.1", Optional.of(elNum),
                "1", sm.new AddElement());
        String key = sm.getMenu().findElementBy("ttt", "inputKey").get().getInputKey();
        assertThat(key, is("ttt"));
    }

    @Test
    public void whenFindElementByFullNumberItsFound() {
        String elNum = sm.getMenu().addElement("element 1", Optional.empty(),
                "1", sm.new AddElement());
        elNum = sm.getMenu().addElement("element 1.1", Optional.of(elNum),
                "2", sm.new AddElement());
        String name = sm.getMenu().findElementBy(elNum, "fullNumber").get().getName();
        assertThat(name, is("element 1.1"));
    }


}
