package servlets.crudservlet;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class MemoryStore implements Store {
    private final static MemoryStore SINGLETON_INSTANCE = new MemoryStore();
    private final ConcurrentHashMap<Integer, User> store;
    private int idCount;

    public  MemoryStore() {
        this.store = new ConcurrentHashMap();
        this.idCount = 0;
    }

    public static MemoryStore getInstance() {
        return SINGLETON_INSTANCE;
    }

    public synchronized int getNextId() {
        idCount++;
        return idCount;
    }

    @Override
    public boolean add(User user) {
        return (store.put(user.getId(), user) == null);
    }

    @Override
    public boolean update(int id, User user) {
        return (store.replace(id, user) != null);
    }

    @Override
    public boolean delete(int id) {
        return (store.remove(id) != null);
    }

    @Override
    public User findById(int id) {
        return store.get(id);
    }

    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        return store;
    }
}
