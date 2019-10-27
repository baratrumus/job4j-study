package isp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Menu node class
 *
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 *
 * number - номер на этом уровне меню - 1
 * FullNumber - номер, включающий в себя структуру дерева 1.2.2.  Заполняется при создании меню.
 */
public class MenuElement implements Comparable<MenuElement> {
    private String name;
    private int number;
    private String FullNumber;
    private Optional<MenuElement> parent;
    private List<MenuElement> childrens;

    public MenuElement(String name,  MenuElement parent) {
        this.name = name;
        this.parent = Optional.ofNullable(parent);
        this.childrens = new ArrayList<>();
    }

    public MenuElement(String name) {
        this.name = name;
        this.parent = Optional.empty();
        this.childrens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFullNumber() {
        return FullNumber;
    }

    public void setFullNumber(String fullNumber) {
        FullNumber = fullNumber;
    }

    public  Optional<MenuElement> getParent() {
        return parent;
    }

    public void setParent(MenuElement parent) {
        this.parent = Optional.of(parent);
    }

    public List<MenuElement> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<MenuElement> childrens) {
        this.childrens = childrens;
    }


    public int compareTo(MenuElement m){

        return this.FullNumber.compareTo(m.getFullNumber());
    }

}
