package gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;



/**
 *1 Создать объект User c полями и перекрытым методом finalize
 *2 Создать несколько объектов User и руками рассчитать сколько он будет занимать памяти.
 *3 Нужно найти информацию. Сколько памяти занимает пустой объект без полей.
 *4 Добиться состояния, когда виртуальная машины вызывает сборщик мусора самостоятельно. За счет ключей xmx.
 *5 Объяснить поведение программы в текстовом файле.
 */
/**
 *The flag Xmx specifies the maximum memory allocation pool for a Java virtual machine (JVM),
 *  while Xms specifies the initial memory allocation pool -Xmx24m -Xms16m
 *  Serial GC включается опцией -XX:+UseSerialGC
 */
public class MemoryUsage {



    /**
     * На WeakReference finalize запускает даже если user не null
     * SoftReference и Strong только если user null
     */
    private static final Logger LOG = LoggerFactory.getLogger(MemoryUsage.class);
    private static Runtime rt = Runtime.getRuntime();

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + '}';
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(String.format("finalize %s", name));
            LOG.warn("finalized " + name);
        }

    }

    public static class User33 {
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("start");
        info();
        //SoftReference<User> user = new SoftReference<>(new User("test"));
        //WeakReference<User> user = new WeakReference<>(new User("test"));
        //PhantomReference<User> user = new PhantomReference<User>(new User("test"), ReferenceQueue<User> queue);
        //User user = new User("test");

        System.out.println(String.format("integer size %s B", sizeOf(5)));
        System.out.println(String.format("string size %s B", sizeOf(new String())));
        System.out.println(String.format("empty object size %s B", sizeOf(new User33())));
        System.out.println(String.format("bare object size %s B", sizeOf(new Object())));
        System.out.println(String.format("user size %s B", sizeOf(new User("test Next user"))));

        for (int i = 0; i < 10000; i++) {
            User user = new User("User " + i);
            info();
            info(i + 1);
            System.out.println(String.format("average size %s", sizeOf(user)));
            user = null;

        }
        info();
        //System.gc();
        System.out.println("finish");
    }


    /** этот метод оценки работает плохо
     * лучше использовать библиотеки
     */
    public static void info(int cnt) {
        int kb = 1024;
        long avgSize = (rt.totalMemory() - rt.freeMemory()) / cnt;
        //average size of User
        System.out.println("Average user size[Bait] " + avgSize);
    }

    public static void info() {
        int kb = 1024;
        long used = (rt.totalMemory() - rt.freeMemory()) / kb;
        System.out.println("Memory in heap [KB] ");

        //Used memory
        System.out.println("Used memory " + used);

        //free memory
        System.out.println("free memory " + rt.freeMemory() / kb);

        //total available memory
        System.out.println("total available memory " + rt.totalMemory() / kb);
        System.out.println();
    }
}
