package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;

public interface Figure {
    /**
     * @return Ячейку фигуры
     */
    Cell position();
    Logic logic = new Logic();

    /**
     * @param source задает исходную ячейку
     * @param dest задает ячейку, куда следует пойти
     * @return  Если фигура может туда пойти - возвращает массив ячеек пути
     * @throws ImpossibleMoveException
     */
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    Figure copy(Cell dest);



}
//ValidateInput
    /*boolean invalid = true;
    int value = -1;
        do {
                try {
                //вызов ask того input, что был передан в конструктор ValidateInput, т.е.
                // центрального кода, вокруг которого будет наше дополнение - декоратор
                value = this.input.ask(question, range);
                invalid = false;
                } catch (MenuOutException moe) {
                moe.printStackTrace();                       //Вывод стека Exception
                System.out.println("Выберите цифру из меню");
                } catch (NumberFormatException nfe) {            //сюда попадем если введем буквы
                System.out.println("Введите цифру, а не букву");
                }
                } while (invalid);
                return value;*/


//ConsoleInput
   /* int key = Integer.valueOf(this.ask(question));
    boolean keyExist = false;
        for (int value : range) {
                if (value == key) {
                keyExist = true;
                break;
                }
                }
                if (!keyExist) {
                throw new MenuOutException("Out of menu range");
                }
                return key;
*/