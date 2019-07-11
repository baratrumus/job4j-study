package ru.job4j.generic;

public class AbstractStore<T extends Base> implements Store<T> {
    protected SimpleArray<T> store;
    protected int size;

    AbstractStore(int size) {
        store = new SimpleArray<>(size);
        this.size = size;
    }

    @Override
    public void add(T model) {
            store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        Integer index = getIndexById(id);
            if (index != null) {
                store.set(index, model);
                res = true;
            }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        Integer index = getIndexById(id);
        if (index != null) {
            store.remove(index);
            res = true;
        }

        return res;
    }

    @Override
    public T findById(String id) {
        T res = null;
        Integer index = getIndexById(id);
        if (index != null) {
              res = store.get(index);
            }
        return res;
    }

    /**
     * @return индекс или null если такого id нет
     */
    private Integer getIndexById(String id) {
        Integer res = null;
        for (int i = 0; i < size; i++) {
            if (store.get(i) != null) {
                if (id.equals(store.get(i).getId())) {
                    res = i;
                    break;
                }
            }
        }
        return res;
    }



}
