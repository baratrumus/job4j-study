package servlets.crudservlet;

import java.util.Map;

public interface Logic {
    boolean add(String name, String login, String pass, String email, String photoId, String roleNum);
    boolean update(String id, String name, String login, String email, String pass, int roleNum);
    boolean delete(String id);
    Map<Integer, User> findAll();
    User findById(String id);
    Map<Integer, String> getRoles();
    User getUserByLoginPass(String login, String pass);

}
