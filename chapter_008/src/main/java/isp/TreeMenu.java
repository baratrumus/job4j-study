package isp;

import java.util.*;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 *
 * Реализовать структуру для поддержания меню.
 * Каждый элемент меню имеет имя. Все меню должно выводиться на экран.
 * Каждый пункт меню может быть как одиночным элементов, так и иметь дочерние под пункты.
 * Все меню должно выводиться на экран. В виде дерева.
 * Предусмотреть возможность определить действие, когда пользователь выбирает конкретный пункт меню. *
 * Например
 * Задача 1.
 * ---- Задача 1.1.
 * --------- Задача 1.1.1.
 * --------- Задача 1.1.2.
 * ----- Задача 1.2..
 */

public class TreeMenu implements ShowMenu {
    private List<TreeMap<Integer, MenuElement>> menu;

    public TreeMenu() {
        this.menu = new ArrayList<>();
    }

    @Override
    public void show() {

    }

    public void addElement(String name, Optional<MenuElement> parent) {
        if (parent.isEmpty()) {
            TreeSet<MenuElement> rootTree = new TreeSet<>();
            rootTree.add(new MenuElement(name));
            menu.add(rootTree);
        } else {

        }
    }


}
