package isp;

import java.util.Optional;

public class StartMenu {
    Boolean exit = false;
    TreeMenu menu = new TreeMenu();
    private ConsoleInput input = new ConsoleInput();

    public void init() {
        String elNum = menu.addElement("To add element push 1", Optional.empty(),
                "1", this.new AddElement());
        elNum = menu.addElement("For exit push Q", Optional.empty(), "q", this.new ExitProgram());

        do {
            menu.show();
            String ret = input.ask("Input key: : ");
            Optional<MenuElement> me = menu.findInputKey(ret);
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
                    System.out.println("element added");
                }
            } else {
                String elNum = menu.addElement(name, Optional.empty(),
                        "", null);
            }
        }
    }

    public static void main(String[] args) {
        StartMenu sm = new StartMenu();
        sm.init();
    }
}
