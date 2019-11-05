package isp;

import java.util.Optional;

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

public class StartMenu {
    private Boolean exit = false;
    public TreeMenu menu;
    private ConsoleInput input = new ConsoleInput();

    public StartMenu(TreeMenu menu) {
        this.menu = menu;
    }

    public void init() {
        String elNum = menu.addElement("To add element push 1", Optional.empty(),
                "1", this.new AddElement());
        elNum = menu.addElement("For exit push Q", Optional.empty(), "q", this.new ExitProgram());
        do {
            menu.show();
            String ret = input.ask("Input key: : ");
            Optional<MenuElement> me = menu.findElementBy(ret, "inputKey");
            if (!me.isEmpty()) {
                me.get().execute();
            } else {
                System.out.println("Menu element not found");
            }
        } while (!this.exit);
    }


    class ExitProgram implements Action {
        public void doAction() {
            exit = true;
        }
    }

    class AddElement implements Action {
        public void doAction() {
            String name = input.ask("Input element name : ");
            String parentNumber = input.ask("Input structure number of parent, for example 1.2.2 : ");
            if (!parentNumber.isBlank()) {
                String elNum = menu.addElement(name, Optional.of(parentNumber),
                        "", null);
                if (elNum == null) {
                    System.out.println("Wrong structure number, element not added");
                } else {
                    System.out.println("Element added");
                }
            } else {
                String elNum = menu.addElement(name, Optional.empty(),
                        "", null);
            }
        }
    }

    public static void main(String[] args) {
        StartMenu sm = new StartMenu(new TreeMenu());
        sm.init();
    }
}
