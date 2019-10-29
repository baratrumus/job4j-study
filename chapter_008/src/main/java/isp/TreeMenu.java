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

public class TreeMenu implements ShowMenu, Menu {
    private TreeMap<String, MenuElement> rootMenu;

    public TreeMenu() {
        this.rootMenu = new TreeMap<>();
    }


    /**
     * Output of menu
      */
    @Override
    public void show() {
        Queue<MenuElement> data = new LinkedList<>();
        for (Map.Entry<String, MenuElement>  rootElem : rootMenu.entrySet()) {
            data.offer(rootElem.getValue());
            while (!data.isEmpty()) {
                MenuElement el = data.poll();
                printElement(el);
                for (Map.Entry<String, MenuElement>  child : el.getChildrens().entrySet()) {
                    data.offer(child.getValue());
                }
            }
        }
    }

    private void printElement(MenuElement el) {
        final String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        String number =  el.getFullNumber();
        String[] srtArr  = number.split(".");

        //indent for proper structure
        sb.append(new String(new char[srtArr.length]).replace("\0", "\t"));
        sb.append(number);
        sb.append(el.getName());
        sb.append(ln);
        System.out.println(sb);
    }


    /**
     * @param name Name of element
     * @param parentNumber Number in menu, like 2.5.1  This number is key, when value of parent is MenuElement
     * @param inputKey key for input in console by user
     *  return FullNumber of elem, that is key in his tree.
     *      Return need check for null.
     */
    @Override
    public String addElement(String name, Optional<String> parentNumber, String inputKey, Action action) {
        String fullNumber;
        MenuElement element;
        if (parentNumber.isEmpty()) { //add to menu root
            String key = Integer.toString(rootMenu.size() + 1);

            element = new MenuElement(name, inputKey, action);
            element.setFullNumber(key);
            this.rootMenu.put(key, element);
            fullNumber = key;
        } else {    //to submenu
            TreeMap<String, MenuElement> subMenu;
            Optional<MenuElement> parentElem = findElemByFullNumber(parentNumber.get());
            if (!parentElem.isEmpty()) { //if parent elem has found
                subMenu = parentElem.get().getChildrens();
                String key = parentNumber.get() + "." + Integer.toString(subMenu.size() + 1);

                element = new MenuElement(name, parentNumber.get(), inputKey, action);
                element.setFullNumber(key);
                subMenu.put(key, element);
                fullNumber = key;
            } else {
                fullNumber = null;
            }
        }
        return fullNumber;
    }


    private Optional<MenuElement> findElemByFullNumber(String fullNumber) {
        Optional<MenuElement> rsl = Optional.empty();
        Queue<MenuElement> data = new LinkedList<>();
        for (Map.Entry<String, MenuElement>  rootElem : rootMenu.entrySet()) {
            data.offer(rootElem.getValue());

            while (!data.isEmpty()) {
                MenuElement el = data.poll();
                if (el.getFullNumber().equals(fullNumber)) {
                    rsl = Optional.of(el);
                    break;
                }
                for (Map.Entry<String, MenuElement> child : el.getChildrens().entrySet()) {
                    data.offer(child.getValue());
                }
            }
        }
        return rsl;
    }



    public Optional<MenuElement> findInputKey(String inputKey) {
        Optional<MenuElement> rsl = Optional.empty();
        Queue<MenuElement> data = new LinkedList<>();
        for (Map.Entry<String, MenuElement>  rootElem : rootMenu.entrySet()) {
            data.offer(rootElem.getValue());

            while (!data.isEmpty()) {
                MenuElement el = data.poll();
                if (el.getInputKey().equals(inputKey)) {
                    rsl = Optional.of(el);
                    break;
                }
                for (Map.Entry<String, MenuElement> child : el.getChildrens().entrySet()) {
                    data.offer(child.getValue());
                }
            }
        }
        return rsl;
    }



}
