package multithread.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Board {
    private final ReentrantLock[][] board;
    private final int size;

    public Board(int size) {
        this.size = size;
        board = new ReentrantLock[this.size][this.size];
    }

    public void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    //метод занимает позицию, если она не занята
    boolean put(Cell cell) {
        boolean res = false;
        ReentrantLock lock = board[cell.getX()][cell.getY()];
        try {
            if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                res = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return res;
    }


    int getSize() {
        return size;
    }

    public boolean move(Cell source, Cell destination) {
        boolean res = false;
        ReentrantLock destinationLock = board[destination.getX()][destination.getY()];
        ReentrantLock sourceLock = board[source.getX()][source.getY()];
        try {
            if (destinationLock.tryLock(500, TimeUnit.MILLISECONDS)) {
                sourceLock.unlock();
                System.out.println(String.format("%s moved from %s to %s", Thread.currentThread().getName(), source, destination));
                System.out.println(String.format("%s locked", destination));
                System.out.println(String.format("%s unlocked", source));
                res = true;
            } else {
                System.out.println(String.format("%s can't move from %s to %s, cell is bizy", Thread.currentThread().getName(), source, destination));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return res;
    }
}
