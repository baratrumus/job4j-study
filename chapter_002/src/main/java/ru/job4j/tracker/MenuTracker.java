package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;



/**
 * Класс событий трекера
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    public boolean exit = false;

    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения списка меню.
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет список.
     * 1. создание объекта внутреннего класса происходит через обращение к объекту внешнего класса
     * MenuTracker через this
     * this.new
     *
     * 2. ShowAll() сделан статическим классом и его объект создается через обращение к имени внешнего класса
     * MenuTracker
     *
     * 3. ExitProgram сделан через класс одного уровня с МenuTracker,
     * поэтому вызываем объект просто new ExitProgram()
     */
    public void fillActions() {
        this.actions.add(this.new AddItem());
        this.actions.add(new MenuTracker.ShowAll());
        this.actions.add(this.new EditItem());
        this.actions.add(this.new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(this.new FindItemsByName());
        this.actions.add(new ExitProgram());

}

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     * @param key ключ операции
     */
    public void select(int key) {
        if (key == 6) {
            this.exit = true;
        }
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * внутренний класс - добавление элемента в трекер(новой заявки в хранилище).
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            long created = System.currentTimeMillis();
            Item item = new Item(name, desc, created);
            tracker.add(item);
            System.out.println("------------ Добавлена новая заявка с Id : " + item.getId() + "-----------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавление новой заявки");
        }
    }


    /**
     * Метод выводит все заявки из хранилища
     */
    private static class ShowAll implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Вывод всех заявок из хранилища --------------");
            for (Item item : tracker.findAll()) {
                System.out.println("------------ Id : " + item.getId() + " ------------");
                System.out.println("------------ Имя : " + item.getName() + " ------------");
                System.out.println("------------ Описание : " + item.getDecs() + " ------------");
                System.out.println("------------ Дата создания : " + item.getTime() + " ------------");
                System.out.println("");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Вывод всех заявок из хранилища");
        }
    }



    /**
     * Метод реализует Редактирование заявки по Id
     */
    private class EditItem implements UserAction {

        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование заявки --------------");
            String id = input.ask("Введите Id заявки для редактирования :");
            Item item = tracker.findById(id);
            System.out.println("------------ Редактируем заявку с именем : " + item.getName() + "-----------");
            System.out.println("------------ Описание : " + item.getDecs() + "-----------");
            System.out.println("------------ Дата создания(будет заменена на текущую) : " + item.getTime() + "-----------");
            String newName = input.ask("Введите новое имя :");
            String newDesc = input.ask("Введите новое описание :");
            long created = System.currentTimeMillis();
            Item newItem = new Item(newName, newDesc, created);
            if (tracker.replace(item.getId(), newItem)) {
                newItem = tracker.findById(id);
                System.out.println("------------ Успешно изменена заявка с Id : " + newItem.getId() + "-----------");
                System.out.println("------------ Имя : " + newItem.getName() + "-----------");
                System.out.println("------------ Описание : " + newItem.getDecs() + "-----------");
                System.out.println("------------ Дата создания : " + newItem.getTime() + "-----------");
            } else {
                System.out.println("------------ Редактирование не удалось -----------");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Редактирование заявки");
        }
    }


    /**
     * Метод реализует удаление заявки по Id
     */
    private class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите Id заявки для удаления :");
            Item item = tracker.findById(id);
            if (tracker.delete(item.getId())) {
                System.out.println("------------ Заявка успешно удалена ");
            } else {
                System.out.println("------------ Удаление не удалось -----------");
            }

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки");
        }
    }


    /**
     * Метод реализует поиск заявки по Id
     */
    private class FindItemById implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск по Id --------------");
            String id = input.ask("Введите Id заявки для поиска :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("------------ Id : " + item.getId() + "-----------");
                System.out.println("------------ Имя : " + item.getName() + "-----------");
                System.out.println("------------ Описание : " + item.getDecs() + "-----------");
                System.out.println("------------ Дата создания : " + item.getTime() + "-----------");
            } else {
                System.out.println("------------ Заявок с таким Id не найдено -----------");
            }

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск по Id");
        }
    }


    /**
     * Метод реализует поиск заявок по имени
     */
    private class FindItemsByName implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявок по имени --------------");
            String name = input.ask("Введите имя для поиска :");
            Item[] item = tracker.findByName(name);
            if (item.length != 0) {
                for (Item it: item) {
                    System.out.println("------------ Id : " + it.getId() + " ------------");
                    System.out.println("------------ Имя : " + it.getName() + " ------------");
                    System.out.println("------------ Описание : " + it.getDecs() + " ------------");
                    System.out.println("------------ Дата создания : " + it.getTime() + " ------------");
                    System.out.println("");
                }
            } else {
                System.out.println("------------ Заявок с таким именем не найдено -----------");
            }

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявок по имени");
        }
    }

    /**
     * Выход из программы
     */
    private class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Выход из программы");
        }
    }
}

