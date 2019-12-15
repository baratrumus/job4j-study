package multithread.blockingqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class BlockingQueueTest {

    @Test
    public void testBlockingQueue() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> result = new CopyOnWriteArrayList<>();
        final BlockingQueue<Integer> bq = new BlockingQueue<>(4);

        //producer
        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 6; i++) {
                        bq.offer(i);
                        System.out.println(String.format("Number \"%s\" have put to queue", i));
                        System.out.println(String.format("queue size is %s", bq.getSize()));
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
                while ((producer.getState() != Thread.State.TERMINATED) || (bq.getSize() > 0)) {
                    try {
                        int num;
                        num = bq.poll();
                        result.add(num);
                        System.out.println(String.format("Number \"%s\" have put to queue", num));
                        System.out.println(String.format("queue size is %s", bq.getSize()));
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println(result.size());
        assertThat(result, is(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }

}