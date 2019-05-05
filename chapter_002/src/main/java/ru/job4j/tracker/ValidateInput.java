package ru.job4j.tracker;

import java.util.List;

public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);          //вызов метода ask родителя
                invalid = false;
            } catch (MenuOutException moe) {
                moe.printStackTrace();                      //Вывод стека Exception
                System.out.println("Выберите цифру из меню");
            } catch (NumberFormatException nfe) {            //сюда попадем если введем буквы
                System.out.println("Введите правильную цифру");
            }
        } while (invalid);
        return value;
    }
}
