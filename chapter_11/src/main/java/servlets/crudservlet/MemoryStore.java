package servlets.crudservlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
*/
public class MemoryStore implements Store<User> {
    private final static MemoryStore SINGLETON_INSTANCE = new MemoryStore();
    private final ConcurrentHashMap<Integer, User> store;
    private AtomicInteger idCount;

    public MemoryStore() {
        this.store = new ConcurrentHashMap();
        this.idCount = new AtomicInteger(1);
        this.store.put(1, new User(1, "root", "root", "root@m.ru", "", "root"));
    }

    public static MemoryStore getInstance() {
        return SINGLETON_INSTANCE;
    }

    public AtomicInteger getNextId() {
        idCount.addAndGet(1);
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

    @Override
    public boolean credentialsExists(String login, String password) {
        Boolean exists = false;
        for (Map.Entry<Integer, User>  user : store.entrySet()) {
            if (user.getValue().getLogin().equals(login)  && user.getValue().getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
