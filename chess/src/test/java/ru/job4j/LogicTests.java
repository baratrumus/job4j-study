package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.Chess;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.*;


public class LogicTests {
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

    /**
     * Тест движения пешки
     */
    @Test
    public void whenPawnMoveD7E5ThenFalse() {
        //Stage stage = new Stage();
        //Chess chess = new Chess();
        //chess.start(stage);

        addFigures();
        boolean result = logic.move(Cell.F7, Cell.D5);
        assertFalse(result);
    }


    @Test
    public void whenPawnMoveG6F6ThenTrue() {

        addFigures();
        boolean result = logic.move(Cell.F7, Cell.F6);
        assertTrue(result);
    }

    /**
     * Тест движения коня
     */
    @Test
    public void whenKnightMoveB8D7ThenFalse() {

        addFigures();
        boolean result = logic.move(Cell.B8, Cell.D7);
        assertFalse(result);
    }

    @Test
    public void whenKnightMoveB8C6ThenTrue() {

        addFigures();
        boolean result = logic.move(Cell.B8, Cell.C6);
        assertTrue(result);
    }

    /**
     * Тест движения слона
     */
    @Test
    public void whenBishopMoveC8E5ThenFalse() {

        addFigures();
        boolean result = logic.move(Cell.C8, Cell.E5);
        assertFalse(result);
    }

    @Test
    public void whenBishopMoveB8C6ThenTrue() {

        addFigures();
        logic.move(Cell.D7, Cell.D6);   //убираем пешку с пути
        boolean result = logic.move(Cell.C8, Cell.E6);
        assertTrue(result);
    }

    /**
     * Тест движения туры
     */
    @Test
    public void whenRookMoveA8A5ThenFalse() {

        addFigures();
        boolean result = logic.move(Cell.A8, Cell.A5);
        assertFalse(result);
    }

    @Test
    public void whenRookMoveA8A7ThenTrue() {

        addFigures();
        logic.move(Cell.A7, Cell.A6);   //убираем пешку с пути
        boolean result = logic.move(Cell.A8, Cell.A7);
        assertTrue(result);
    }

    /**
     * Тест движения короля
     */
    @Test
    public void whenKingMoveE8E7ThenFalse() {

        addFigures();
        boolean result = logic.move(Cell.E8, Cell.E7);
        assertFalse(result);
    }

    @Test
    public void whenKingMoveE8E7ThenTrue() {

        addFigures();
        logic.move(Cell.E7, Cell.E6);   //убираем пешку с пути
        boolean result = logic.move(Cell.E8, Cell.E7);
        assertTrue(result);
    }

    /**
     * Тест движения ферзя
     */
    @Test
    public void whenQueenMoveD8F6ThenFalse() {

        addFigures();
        boolean result = logic.move(Cell.D8, Cell.F5);
        assertFalse(result);
    }

    @Test
    public void whenQueenMoveD8F6ThenTrue() {

        addFigures();
        logic.move(Cell.E7, Cell.E6);   //убираем пешку с пути
        boolean result = logic.move(Cell.D8, Cell.F6);
        assertTrue(result);
    }
}
