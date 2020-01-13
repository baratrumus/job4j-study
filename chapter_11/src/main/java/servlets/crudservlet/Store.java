package servlets.crudservlet;

import java.util.Map;

public interface Store {
    boolean add(User user);
    boolean update(int id, User user);
    boolean delete(int id);
    User findById(int id);
    Map findAll();
    int getNextId();
}
