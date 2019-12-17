package multithread.counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

//эта аннотация, говорит пользователям данного класса, что класс можно использовать в многопоточном режиме
//и он будет работать правильно.
@ThreadSafe
public class Count {
//эта аннотация выставляется над общим ресурсом. Аннотация имеет входящий параметр. Он указывает на объект монитора,
//по которому мы будет синхронизироваться. Программист должен работать с этим ресурсом только
// в критической секции, которая синхронизируется по объекту монитора, который указан в аннотации.

    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }

    public static void main(String[] args)  throws InterruptedException {
        final Count count = new Count();


        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (count) {
                    count.increment();
                }
                System.out.println("Count " + count.get());
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (count) {
                    /*try {
                        count.wait();

                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }*/
                    for (int i = 0; i < 10; i++) {
                        count.increment();
                        System.out.println("Count " + count.get());
                    }
                    //count.notify();
                }
                System.out.println("Count " + count.get());
            }
        };
        t1.start();
        t2.start();

    }
}
