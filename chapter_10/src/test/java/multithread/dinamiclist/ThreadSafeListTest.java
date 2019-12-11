package multithread.dinamiclist;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ThreadSafeListTest {


    @Test
    public void whenAddAndIterateThenSuccess() throws InterruptedException {
        ListWork tlist = new ListWork();
        Thread thread1 = new Thread(tlist);
        thread1.start();
        Thread thread2 = new Thread(tlist);
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(tlist.da.get(1), is(1));
        assertThat(tlist.da.get(7), is(2));
    }
}