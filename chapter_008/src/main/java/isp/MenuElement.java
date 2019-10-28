package isp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Menu node class
 *
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 *
 * number - номер на этом уровне меню - 1
 * FullNumber - номер, включающий в себя структуру дерева 1.2.2.  Заполняется при создании меню.
 * inputKey - строка, при вводе которой пользователем выполняется action этого элемента
 * action - класс, реализующий интерфейс Action
 */
public class MenuElement implements Comparable<MenuElement> {
    private String name;
    private String fullNumber;
    private Optional<String> parentNumber;
    private TreeMap<String, MenuElement> childrens;
    private String inputKey;
    private Action action;

    public MenuElement(String name,  String parentNumber,  String inputKey, Action action) {
        this.name = name;
        this.parentNumber = Optional.ofNullable(parentNumber);
        this.childrens = new TreeMap<String, MenuElement>();
        this.inputKey = inputKey;
        this.action = action;
    }

    public MenuElement(String name, String inputKey, Action action) {
        this.name = name;
        this.parentNumber = Optional.empty();
        this.childrens = new TreeMap<String, MenuElement>();
        this.inputKey = inputKey;
        this.action = action;
    }

    public void execute() {
        this.action.doAction();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public  Optional<String> getParent() {
        return parentNumber;
    }

    public void setParent(String parent) {
        this.parentNumber = Optional.of(parent);
    }

    public TreeMap<String, MenuElement> getChildrens() {
        return this.childrens;
    }

    public void setChildrens(TreeMap<String, MenuElement> childrens) {
        this.childrens = childrens;
    }

    public String getInputKey() {
        return this.inputKey;
    }

    public int compareTo(MenuElement m) {
        return this.fullNumber.compareTo(m.getFullNumber());
    }

}
