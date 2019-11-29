package multithread.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1

 * Игра пинг понг.
 * Квадратик должен отталкиваться от краев окна и менять направление.
 * Движение квадратика должно задаваться через Thread.
 * В вечном цикле надо добавить проверку. что выставлен флаг остановки.
 * Если он выставлен, то нужно завершить выполнение цикла.
 */
public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";
    public final static int LIMITX = 400;
    public final static int LIMITY = 300;

    @Override
    public void start(Stage stage) {
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread run = new Thread(new RectangleMove(rect));
        run.start();
        stage.setScene(new Scene(group, LIMITX, LIMITY, Color.OLIVE));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(
                event -> run.interrupt()); // execute interrupt
    }
}
