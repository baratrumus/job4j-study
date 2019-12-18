package multithread.nonblock;


import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import java.util.concurrent.atomic.AtomicReference;


public class NonBlockCacheTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        NonBlockCache cache = new NonBlockCache();
        Base model = new Base(1, 1);
        cache.add(model);
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        cache.update(model);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, 1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("version changed"));

    }
}