package isp;

import java.util.*;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
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
        String[] srtArr  = number.split("\\.");
        String repeated = "-".repeat((srtArr.length - 1) * 4);
        sb.append(repeated);
        sb.append(number).append(" ");
        sb.append(el.getName());
        //sb.append(ln);
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
            Optional<MenuElement> parentElem = findElementBy(parentNumber.get(), "fullNumber");
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

    public Optional<MenuElement> findElementBy(String value, String findBy) {
        Optional<MenuElement> rsl = Optional.empty();
        Queue<MenuElement> data = new LinkedList<>();
        for (Map.Entry<String, MenuElement>  rootElem : rootMenu.entrySet()) {
            data.offer(rootElem.getValue());
            while (!data.isEmpty()) {
                MenuElement el = data.poll();
                if (findBy.equals("fullNumber")) {
                    if (el.getFullNumber().equals(value)) {
                        rsl = Optional.of(el);
                        break;
                    }
                } else if (findBy.equals("inputKey")) {
                    if (el.getInputKey().equals(value)) {
                        rsl = Optional.of(el);
                        break;
                    }
                }
                for (Map.Entry<String, MenuElement> child : el.getChildrens().entrySet()) {
                    data.offer(child.getValue());
                }
            }
        }
        return rsl;
    }
}
