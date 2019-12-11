package multithread.dinamiclist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicArray;

/**
 * Необходимо создать многопоточную коллекцию данных.
 * Возьмите код задания из модуля коллекции, где была реализована коллекция динамический список и
 * за счет композиции сделайте многопоточную коллекцию.
 * Этот итератор будет работает в режиме fail-safe - все изменения после получения коллекции не будут отображаться в итераторе.
 * fail-fast - другой режим. при изменении данных во время итерации, коллекция выкинет исключение  ConcurrentModificationException.
 * Чтобы не получить ошибку visibility надо сделать копию данных - snapshot. И уже с нее сделать итератор и другие методы.
 *
 * И уже с него сделать итератор.
 */

@ThreadSafe
public class ThreadSafeList<T> implements Iterable {
    int size;
    @GuardedBy("this")
    private DynamicArray<T> da;

    public ThreadSafeList(int size) {
        this.size = size;
        da = new DynamicArray(size);
    }

    public synchronized Iterator<T> iterator() {
        return snapshot(da).iterator();
    }

    public synchronized void add(T data) {
        da.add(data);
    }

    public synchronized int getSize() {
        return snapshot(da).getSize();
    }

    public synchronized T get(int index) {
        return snapshot(da).get(index);
    }

    private DynamicArray<T> snapshot(DynamicArray<T> list) {
        DynamicArray<T> newDa = new DynamicArray<>(size);
        for (var element : list) {
            newDa.add(element);
        }
        return newDa;
    }
}
