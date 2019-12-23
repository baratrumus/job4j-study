package multithread.threadpool;

import multithread.blockingqueue.BlockingQueue;

public class TaskExec extends Thread {
    private final BlockingQueue<Runnable> tasks;

    public TaskExec(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Runnable task;
            try {
                task = tasks.poll();
                if (task != null) {
                   task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }
}
