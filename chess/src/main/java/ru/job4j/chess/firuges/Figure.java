package ru.job4j.chess.firuges;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.GetDeltas;
import ru.job4j.chess.GetLineType;
import ru.job4j.chess.Logic;

public interface Figure {

    Logic LOGIC = new Logic();
    GetDeltas DELTAS = new GetDeltas();
    GetLineType LINE_TYPE = new GetLineType();

    /**
     * @return Ячейку фигуры
     */
    Cell position();

    /**
     * @param source задает исходную ячейку
     * @param dest задает ячейку, куда следует пойти
     * @return  Если фигура может туда пойти - возвращает массив ячеек пути
     * @throws ImpossibleMoveException
     */
    Cell[] way(Cell source, Cell dest)  throws ImpossibleMoveException;

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    Figure copy(Cell dest);

    void moveInfo(Cell source, Cell dest);
}
