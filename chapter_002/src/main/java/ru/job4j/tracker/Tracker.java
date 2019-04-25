package ru.job4j.tracker;

import java.util.Random;
import java.util.Arrays;

import static java.lang.System.arraycopy;

/**
 * Обертка над строкой.
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RND = new Random();


    /**
     * Метод реализующий добавление заявки в хранилище
     * position всегда будет на новом, пустом месте, на 1 больше текущего
     * если посл. эл. массива - position не увеличиваем
     * видимо надо вывести инфу что массив заполнен
     * @param item новая заявка
     */

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position] = item;
        if (this.position < this.items.length - 1) {
            this.position++;
        }
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(RND.nextInt());
    }

    /**
     * проверяет в цикле все элементы массива this.items, сравнивая id с аргументом String id
     * и возвращает найденный Item. Если Item не найден - возвращает null.
     * @param id
     * @return Item
     */
    public Item findById(String id) {
        Item found = null;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(id)) {
                found = this.items[i];
                break;
            }
        }
        return found;
    }

    /**
     * проверяет в цикле все элементы массива this.items, сравнивая name
     * (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его;
     * @param key
     * @return результирующий массив
     */

    public Item[] findByName(String key) {
        Item[] found = new Item[this.position];
        int j = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null && this.items[i].getName().equals(key)) {
                found[j++] = this.items[i];
            }
        }
        return Arrays.copyOf(found, j);
    }

    /**
     * @return возвращает копию массива this.items без null элементов;
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }


    /**
     * должен заменить ячейку в массиве this.items. Для этого необходимо найти ячейку в массиве по id.
     * @param id
     * @param item
     * @return boolean результат - удалось ли провести операцию.
     */
    public boolean replace(String id, Item item) {
        boolean res = false;
        if (id == null || item == null) {
            return res;
        }

        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
                this.items[i].setId(id); //ставим id, чтобы он не оказался null, если item берем не из массива
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * должен удалить ячейку в массиве this.items. Для этого необходимо найти ячейку в массиве по id.
     * Далее сместить все значения справа от удаляемого элемента - на одну ячейку влево
     * @param id
     * @return boolean результат - удалось ли провести операцию.
     */
    public boolean delete(String id) {
        boolean res = false;
        if (id == null) {
            return res;
        }
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.items[this.items.length - 1] = null;
                this.position--;
                res = true;
                break;
            }
        }
        return res;
    }
}