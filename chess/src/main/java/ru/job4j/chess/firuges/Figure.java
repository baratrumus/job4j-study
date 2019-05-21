package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;

public interface Figure {
    /**
     * @return Ячейку фигуры
     */
    Cell position();
    Logic LOGIC = new Logic();

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
