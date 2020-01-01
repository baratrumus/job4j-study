package multithread.bomberman;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Creature implements Runnable {
    private final Board board;
    private Cell position;
    private final String name;
    private final int movingDelay;

    Creature(Board board, Cell position, String name, int delay) {
        this.board = board;
        this.position = position;
        this.name = name;
        this.movingDelay = delay;
    }

    String getName() {
        return name;
    }

    @Override
    public void run() {
        ReentrantLock lock = board.getLock(position);
        lock.lock();
        System.out.println(String.format("%s locked", position));
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(movingDelay);
                makeMove();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean makeMove() {
        boolean result = false;
        Cell dest = getPossibleDest();
        if (board.move(position, dest)) {
            position = dest;
            result = true;
        }
        return result;
    }

    private Cell getPossibleDest() {
        Cell dest;
        Random random = new Random();
        while (true) {
            int direction = random.nextInt(3);
            if (direction == 0) {            //left
                if ((position.getX() - 1) >= 0) {
                    dest = new Cell(position.getX() - 1, position.getY());
                    break;
                }
            } else if (direction == 1) {    //up
                if ((position.getY() + 1) < board.getSize()) {
                    dest = new Cell(position.getX(), position.getY() + 1);
                    break;
                }
            } else if (direction == 2) {    //right
                if ((position.getX() + 1) < board.getSize()) {
                    dest = new Cell(position.getX() + 1, position.getY());
                    break;
                }
            } else if (direction == 3) {    //down
                if ((position.getY() - 1) >= 0) {
                    dest = new Cell(position.getX(), position.getY() - 1);
                    break;
                }
            }
        }
        return dest;
    }
}
