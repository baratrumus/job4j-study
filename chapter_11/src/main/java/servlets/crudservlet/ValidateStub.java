package servlets.crudservlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс - заглушка для мок-тестов, который будет эммулировать поведение ValidateService
 */
public class ValidateStub implements Logic {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    public ValidateStub() {
        this.store.put(1, new User(1, "root", "root", "root@m.ru",
                "", "root", new Role(1)));
    }

    @Override
    public boolean add(String name, String login, String pass, String email, String photoId, String roleNum) {
        boolean result = false;
        int id = this.ids++;
        if (id != 0) {
            User user = new User(id, name, login, email, photoId, pass, new Role(Integer.parseInt(roleNum)));
             if (this.store.put(id, user) != null) {
                result = true;
             }
        }
        return result;
    }

    @Override
    public boolean update(String id, String name, String login, String email, String pass, int roleNum) {
        boolean res = false;
        int idNum = Integer.parseInt(id);
        User user = store.get(idNum);
        if (user != null) {
            user.setEmail(email);
            user.setLogin(login);
            user.setPass(pass);
            user.setRole(roleNum);
            user.setName(name);
            if (store.put(idNum, user) != null) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        return (store.remove(id) != null);
    }

    @Override
    public Map<Integer, User> findAll() {
        return  this.store;
    }

    @Override
    public User findById(String id) {
        return store.get(id);
    }

    @Override
    public Map<Integer, String> getRoles() {
        return new Role().getRoles();
    }

    @Override
    public User getUserByLoginPass(String login, String pass) {
        return null;
    }
}

