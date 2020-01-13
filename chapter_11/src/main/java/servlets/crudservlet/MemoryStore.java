package servlets.crudservlet;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {
    private final static MemoryStore SINGLETON_INSTANCE = new MemoryStore();
    private final ConcurrentHashMap<Integer, User> store;
    private int idCount;

    public MemoryStore() {
        this.store = new ConcurrentHashMap();
        this.idCount = 0;
    }

    public static MemoryStore getInstance() {
        return SINGLETON_INSTANCE;
    }

    public int getNextId() {
        idCount++;
        return idCount;
    }

    @Override
    public boolean add(User user) {
        Boolean result = (store.put(user.getId(), user) == null) ? true : false;
        if (result) {
            idCount++;
        }
        return result;
    }

    @Override
    public boolean update(int id, User user) {
        return (store.replace(id, user) != null) ? true : false;
    }

    @Override
    public boolean delete(int id) {
        return (store.remove(id) != null) ? true : false;
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
