package multithread.blockcustomer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class NonBlockAlgoExample {
    //неатомарна, В count зашиты 3 операции - read, increment, write
    //значит volatile с сount использовать нельзя
    private static volatile int count = 0;

    private volatile boolean blockCustomer = true;

    /*
    Неблокирующий алгоритм - нет объекта, который блокируется, просто выполняем полезную работу
     */
    public void doSomething() throws InterruptedException {
            while (blockCustomer) {
                System.out.println(String.format("%s wait ", Thread.currentThread().getName()));
                //usefull work
            }
    }

    public void changeBlock(boolean sign) {
        System.out.println(String.format("%s enable ", Thread.currentThread().getName()));
        blockCustomer = sign;

    }

    public static void main(String[] args)  throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                //В count зашиты 3 операции - read, increment, write
                //значит volatile использовать нельзя
                count++;
            }
        };

        //покобез. лист.  Все методы листа оборачиваются в syncronized. Но работает медленно
        List<String> syncList = Collections.synchronizedList(new ArrayList<String>());

        //создает копию имеющегося листа и делает изменения в копии, а затем обновляет ссылку на измененный лист
        //но могут быть неверные данные, особенно через методы size и empty
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();

        //работает быстрее чем полная синхронизация.
        //По тому же принципу что и предудущая.
        ConcurrentHashMap map = new ConcurrentHashMap();

    }
}
