package multithread.reentrantlock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Сначала каждый поток пытается в течение определенного времени (TIME_WAIT, мс) блокировать доступ к ресурсу resource
 * с использованием метода tryLock. Если блокировка получена, то текст строки resource изменяется. После этого в потоке
 * выполняется некоторая задержка по времени (TIME_SLEEP, мс) и поток завершает свою работу с освобождением блокировки
 * методом unlock. Если поток в течение времени TIME_WAIT не смог блокировать ресурс, то он переходит к стадии задержки
 * и завершению работы. *
 * Оперируя временем ожидания блокировки TIME_WAIT и временем задержки TIME_SLEEP можно дать возможность либо каждому из
 * потоку изменить значение resource, либо только одному.
 */
public class ReentrantLockSample {
    private String resource = "Resourse";
    SimpleDateFormat sdf;
    Lock  lock;
    private final static int  TIME_WAIT  = 7000;
    private final static int  TIME_SLEEP = 5000;

    ReentrantLockSample() {
        sdf = new SimpleDateFormat("HH:mm:ss  ");
        lock = new ReentrantLock();
    }

    public void start() {
        Thread thread1;
        Thread thread2;
        thread1 = new Thread(new LockClass("first",
                "Первый поток"));
        thread2 = new Thread(new LockClass("second",
                "Второй поток"));
        thread1.start();
        thread2.start();

        printMessage(null);

        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nЗавершение работы примера");
        System.exit(0);
    }


    void printMessage(final String msg) {
        String text = sdf.format(new Date());
        if (msg == null) {
            text += resource;
        } else {
            text += msg;
            System.out.println(text);
        }
    }


    class LockClass implements Runnable {
        String name;
        String text;

        public LockClass(String name, String text) {
            this.name = name;
            this.text = text;
        }

        @Override
        public void run() {
            boolean locked = false;
            try {
                // Получение блокировки в течение TIME_WAIT
                locked = lock.tryLock(TIME_WAIT,
                        TimeUnit.MILLISECONDS);
                if (locked) {
                    resource = text;
                    printMessage(null);
                }
                Thread.sleep(TIME_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Убираем блокировку
                String text = name + " : завершил работу";
                printMessage(text);
                if (locked) {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        ReentrantLockSample rls = new ReentrantLockSample();
        rls.start();
    }
}

