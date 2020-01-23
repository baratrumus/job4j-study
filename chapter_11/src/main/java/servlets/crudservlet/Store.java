package servlets.crudservlet;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface Store<User> {
    boolean add(User user);
    boolean update(int id, User user);
    boolean delete(int id);
    User findById(int id);
    Map findAll();
    AtomicInteger getNextId();
}
