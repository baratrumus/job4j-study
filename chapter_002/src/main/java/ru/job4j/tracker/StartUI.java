package ru.job4j.tracker;

/**
 * Меню - интерфейс  взаимодействия с пользователем
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String SHOWALL = "1";
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String FINDBYID = "4";
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }


    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }


    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOWALL.equals(answer)) {
                this.showAll();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }


    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        long created = System.currentTimeMillis();
        Item item = new Item(name, desc, created);
        this.tracker.add(item);
        System.out.println("------------ Добавлена новая заявка с Id : " + item.getId() + "-----------");
    }

    /**
     * Метод выводит все заявки из хранилища
     */
    private void showAll() {
        System.out.println("------------ Вывод всех заявок из хранилища --------------");
        for (Item item: this.tracker.findAll()) {
            System.out.println("------------ Id : " + item.getId() + "-----------");
            System.out.println("------------ Имя : " + item.getName() + "-----------");
            System.out.println("------------ Описание : " + item.getDecs() + "-----------");
            System.out.println("------------ Дата создания : " + item.getTime() + "-----------");
            System.out.println(" ");
            System.out.println(" ");
        }
    }


    /**
     * Метод реализует Редактирование заявки по Id
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите Id заявки для редактирования :");
        Item item = this.tracker.findById(id);
        System.out.println("------------ Редактируем заявку с именем : " + item.getName() + "-----------");
        System.out.println("------------ Описание : " + item.getDecs() + "-----------");
        System.out.println("------------ Дата создания(будет заменена на текущую) : " + item.getTime() + "-----------");
        String newName = this.input.ask("Введите новое имя :");
        String newDesc = this.input.ask("Введите новое описание :");
        long created = System.currentTimeMillis();
        Item newItem = new Item(newName, newDesc, created);
        if (this.tracker.replace(item.getId(), newItem)) {
            newItem = this.tracker.findById(id);
            System.out.println("------------ Успешно изменена заявка с Id : " + newItem.getId() + "-----------");
            System.out.println("------------ Имя : " + newItem.getName() + "-----------");
            System.out.println("------------ Описание : " + newItem.getDecs() + "-----------");
            System.out.println("------------ Дата создания : " + newItem.getTime() + "-----------");
        } else {
            System.out.println("------------ Редактирование не удалось -----------");
        }
    }


    /**
     * Метод реализует Редактирование заявки по Id
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите Id заявки для удаления :");
        Item item = this.tracker.findById(id);
        if (this.tracker.delete(item.getId())) {
            System.out.println("------------ Заявка успешно удалена ");
        } else {
            System.out.println("------------ Удаление не удалось -----------");
        }
    }

    private void findById() {
        System.out.println("------------ Поиск по Id --------------");
        String id = this.input.ask("Введите Id заявки для поиска :");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("------------ Id : " + item.getId() + "-----------");
            System.out.println("------------ Имя : " + item.getName() + "-----------");
            System.out.println("------------ Описание : " + item.getDecs() + "-----------");
            System.out.println("------------ Дата создания : " + item.getTime() + "-----------");
        } else {
            System.out.println("------------ Заявок с таким Id не найдено -----------");
        }
    }


    private void findByName() {
        System.out.println("------------ Поиск заявок по имени  --------------");
        String name = this.input.ask("Введите имя для поиска :");
        Item[] item = this.tracker.findByName(name);
        if (item.length != 0) {
            for (Item it: item) {
                System.out.println("------------ Id : " + it.getId() + "-----------");
                System.out.println("------------ Имя : " + it.getName() + "-----------");
                System.out.println("------------ Описание : " + it.getDecs() + "-----------");
                System.out.println("------------ Дата создания : " + it.getTime() + "-----------");
                System.out.println(" ");
            }
        } else {
            System.out.println("------------ Заявок с таким именем не найдено -----------");
        }
    }

}