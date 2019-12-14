package multithread.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1

/**
 * Реализуйте шаблон Producer Consumer. *
 * Для этого вам необходимо реализовать собственную версию bounded blocking queue. Это блокирующая очередь,
 * ограниченная по размеру. В данном шаблоне Producer помещает данные в очередь, а Consumer извлекает данные
 * из очереди. *
 * Если очередь заполнена полностью, то при попытке добавления поток Producer блокируется, до тех пор пока Consumer
 * не извлечет очередные данные, т.е. в очереди появится свободное место. И наоборот если очередь пуста поток
 * Consumer блокируется, до тех пор пока Producer не поместит в очередь данные. *
 * В задании нельзя использовать потокобезопасные коллекции реализованные в JDK. Ваша задача используя,
 * wait/notify реализовать блокирующую очередь.
 */
@ThreadSafe
public class BlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int size;

    public BlockingQueue(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    //producer
    public void offer(T value) throws InterruptedException {
        synchronized (this.queue) {
            while (queue.size() >= size) {
                System.out.println(String.format("queue is full %s wait", Thread.currentThread().getName()));
                queue.wait();
            }
            queue.offer(value);
            System.out.println(String.format("Number \"%s\" have put to queue", value));
            System.out.println(String.format("queue size is %s", queue.size()));
            queue.notify();
        }

    }
    //Consumer
    //Метод poll() должен вернуть объект из внутренней коллекции.
    // Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания.
    public T poll() throws InterruptedException {
        synchronized (this.queue) {
            T value = null;
            while (queue.size() <= 0) {
                System.out.println(String.format("queue is empty, %s wait ", Thread.currentThread().getName()));
                queue.wait();
            }
            value = queue.poll();
            System.out.println(String.format("Number \"%s\" have gotten", value));
            System.out.println(String.format("queue size is %s", queue.size()));
            queue.notify();
            return value;
        }



    }

    public static void main(String[] args)  throws InterruptedException {
        final BlockingQueue<Integer> bq = new BlockingQueue<>(3);

        //producer
        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 7; i++) {
                        bq.offer(i);
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        //consumer
        Thread consumer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        bq.poll();
                    }

                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        producer.start();
        consumer.start();
        //producer.join();
        //consumer.join();

    }
}
