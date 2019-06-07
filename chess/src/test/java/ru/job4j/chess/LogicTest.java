package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.*;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.*;


public class LogicTest {
    @Test
    public void whenMoveB8C6ThenTrue() {
        addFigures();
        logic.move(Cell.D7, Cell.D6);   //убираем пешку с пути
        boolean result = logic.move(Cell.C8, Cell.E6);
        assertTrue(result);
    }


    @Test
    public void whenMoveC8E5ThenFalse() {
        addFigures();
        boolean result = logic.move(Cell.C8, Cell.E5);
        assertFalse(result);
    }

    /**
     * isWayFree
      */
    @Test
    public void whenisWayFreeBishopC8E6ThenFalse() {
        addFigures();
        boolean result = logic.move(Cell.C8, Cell.E6);
        assertFalse(result);
    }

    @Test
    public void whenisWayFreeBishopC8E6ThenTrue() {
        addFigures();
        logic.move(Cell.D7, Cell.D6);   //убираем пешку с пути
        boolean result = logic.move(Cell.C8, Cell.E6);
        assertTrue(result);
    }

    Logic logic = new Logic();

    public void addFigures() {
        logic.add(new PawnBlack(Cell.A7));
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new PawnBlack(Cell.C7));
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new PawnBlack(Cell.E7));
        logic.add(new PawnBlack(Cell.F7));
        logic.add(new PawnBlack(Cell.G7));
        logic.add(new PawnBlack(Cell.H7));
        logic.add(new RookBlack(Cell.A8));
        logic.add(new KnightBlack(Cell.B8));
        logic.add(new BishopBlack(Cell.C8));
        logic.add(new QeenBlack(Cell.D8));
        logic.add(new KingBlack(Cell.E8));
        logic.add(new BishopBlack(Cell.F8));
        logic.add(new KnightBlack(Cell.G8));
        logic.add(new RookBlack(Cell.H8));

        logic.add(new PawnWhite(Cell.A2));
        logic.add(new PawnWhite(Cell.B2));
        logic.add(new PawnWhite(Cell.C2));
        logic.add(new PawnWhite(Cell.D2));
        logic.add(new PawnWhite(Cell.E2));
        logic.add(new PawnWhite(Cell.F2));
        logic.add(new PawnWhite(Cell.G2));
        logic.add(new PawnWhite(Cell.H2));
        logic.add(new RookWhite(Cell.A1));
        logic.add(new KnightWhite(Cell.B1));
        logic.add(new BishopWhite(Cell.C1));
        logic.add(new QeenWhite(Cell.D1));
        logic.add(new KingWhite(Cell.E1));
        logic.add(new BishopWhite(Cell.F1));
        logic.add(new KnightWhite(Cell.G1));
        logic.add(new RookWhite(Cell.H1));
    }
}
