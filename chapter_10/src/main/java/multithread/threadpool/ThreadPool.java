package multithread.threadpool;

import multithread.blockingqueue.BlockingQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1

/**
 * Пул - это хранилища для ресурсов, которые можно переиспользовать.
 * Клиент берет ресурс из пула, выполняет свою работу и возвращается обратно в пул.
 * Инициализация пула должна быть по количеству ядер в системе.
 * В каждую нить передается блокирующая очередь tasks.
 * Количество нитей всегда одинаковое и равно size.
 * В методе run мы должны получить задачу их очереди tasks.
 * tasks - это блокирующая очередь. Если в очереди нет элементов, то нить переводиться в состоянии waiting
 * Когда приходит новая задача, всем нитям в состоянии waiting посылается сигнал проснуться и начать работу.
 */
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final BlockingQueue<Runnable> tasks;
    private final int poolSize;

    public ThreadPool(int queueSize) {
        poolSize = Runtime.getRuntime().availableProcessors();
        tasks = new BlockingQueue<>(queueSize);
    }

    public void init() {
        for (int i = 0; i < poolSize; i++) {
            Thread resourse = new TaskExec(tasks);
            threads.add(resourse);
        }
    }

    public void startPool() {
        for (Thread thread: threads) {
            thread.start();
        }
    }

    /**
     * этот метод должен добавлять задачи в блокирующую очередь tasks.
     */
    public void addWork(Runnable task) throws InterruptedException {
        tasks.offer(task);
    }

    public void shutdown() {
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }




    public static void main(String[] args)  throws InterruptedException {
        ThreadPool pool = new ThreadPool(6);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int lambdaI = i;
            tasks.add(() -> System.out.println(String.format("%s running task %s", Thread.currentThread().getName(), lambdaI)));
        }

        // add tasks to queue
        Thread producer = new Thread(
                () -> {
                    for (Runnable task: tasks) {
                        try {
                            pool.addWork(task);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        pool.init();

        // get task from queue and run in thread from thread pool
       // Thread consumer = new Thread(pool::startPool);
        producer.start();
        pool.startPool();   //consumer
        //consumer.start();
        producer.join();
        //consumer.join();

        pool.shutdown();
       // System.out.println(consumer.getState());
        System.out.println("finish");
    }
}
