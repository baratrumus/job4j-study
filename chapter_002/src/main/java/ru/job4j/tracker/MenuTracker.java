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


    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }


    /**
     * Метод заполняет список.
     * 1. создание объекта внутреннего класса происходит через обращение к объекту внешнего класса
     * MenuTracker через this
     * this.new
     * <p>
     * 2. ShowAll() сделан статическим классом и его объект создается через обращение к имени внешнего класса
     * MenuTracker
     *
     * 3. ExitProgram сделан через внешний класс
     */
    public void fillActions(StartUI ui) {
        this.actions.add(this.new AddItem(0, "Добавление новой заявки"));
        this.actions.add(new MenuTracker.ShowAll(1, "Вывод всех заявок из хранилища"));
        this.actions.add(this.new EditItem(2, "Редактирование заявки"));
        this.actions.add(this.new DeleteItem(3, "Удаление заявки"));
        this.actions.add(this.new FindItemById(4, "Поиск по Id"));
        this.actions.add(this.new FindItemsByName(5, "Поиск заявок по имени"));
        this.actions.add(new ExitProgram(6, "Выход из программы", ui));
    }


    /**
     * Метод позволяет добавлять добавлять новый action в список actions.
     * Это позволяет реализовать принцип класс открыт для расширения через этот метод, но закрыт для изменения,
     * т.к. не надо редактировать внутренний код класса для добавления нового действия в меню.
     * Для расширения есть интерфейс доступа
     *
     * @param action
     */
    public void addAction(UserAction action) {
        this.actions.add(action);

    }

    /**
     * @return геттер длины списка
     */
    public int getActionsLength() {
        return actions.size();
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
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
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
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

    }


    /**
     * Метод выводит все заявки из хранилища
     */
    private static class ShowAll extends BaseAction {

        public ShowAll(int key, String name) {
            super(key, name);
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
    }


    /**
     * Метод реализует Редактирование заявки по Id
     */
    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
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

    }


    /**
     * Метод реализует удаление заявки по Id
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
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

    }


    /**
     * Метод реализует поиск заявки по Id
     */
    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
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

    }


    /**
     * Метод реализует поиск заявок по имени
     */
    private class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявок по имени --------------");
            String name = input.ask("Введите имя для поиска :");
            Item[] item = tracker.findByName(name);
            if (item.length != 0) {
                for (Item it : item) {
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

    }

}

/**
 * "Внешний" внутренний класс. Сделан через имплементацию, а не наследование,
 * в отличие от других методов меню
 * Выход из программы
 */
class ExitProgram implements UserAction {
    private final StartUI ui;
    private final int key;
    private final String name;

    public ExitProgram(int key, String name, StartUI ui) {
        this.ui = ui;
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        this.ui.stop();
    }

    @Override
    public String info() {
        return String.format("%s : %s", this.key, this.name);
    }

}


