package ru.job4j.list;
/**
 *  Используя контейнер на базе связанного списка создать контейнер Stack
 */

public class SimpleQueue<T> {
    private SimpleStack<T> inStack = new SimpleStack<>();
    private SimpleStack<T> outStack = new SimpleStack<>();

    /**
     * должен возвращать значение и удалять его из коллекции.
    */
    public T poll() {
        T ret = null;
        if (outStack.getSize() == 0) {
            while (inStack.getSize() != 0) {
                ret = inStack.poll();
                outStack.push(ret);
            }
            outStack.poll();
        } else {
            ret = outStack.poll();
        }
        return ret;
    }

    /**
     * помещает значение в коллекцию
     */
    public void push(T value) {
        inStack.push(value);
    }
}
