package servlets.crudservlet;

import java.util.List;
import java.util.Map;

/**
 * Logic layout
 * Singleton Eager loading, при инициализации класса
 * HTTP может передавать только текст.
 * На стороне сервера текст в слое контроллера, logic layout мы преобразовываем в объект модели.
 * Дальше на Persistent мы передаем объект с полями. Этот шаблон называется Data Transfer Object.
 * Основные преимущества этого шаблона в том, что при изменении требований (модели)
 * он не затронет большую часть кода.
 * Каждый метод должен производить валидацию данных.
 * Например, при обновлении полей нужно проверить. что такой пользователь существует.
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class ValidateService implements Logic {
    private final static Logic SINGLETON_INSTANCE = new ValidateService();
    //получаем экземпляр нижестоящего слоя Persistent
    private final Store store = MemoryStore.getInstance();
    //private final Store store = DBStore.getInstance();

    public static Logic getInstance() {
        return SINGLETON_INSTANCE;
    }

    @Override
    public boolean add(String name, String login, String pass, String email, String photoId, String country, String city, String roleNum) {
        boolean result = false;
        int id = store.getNextId().get();
        if (id != 0) {
            User user = new User(id, name, login, email, photoId, pass, country, city, new Role(Integer.parseInt(roleNum)));
            result = store.add(user);
        }
        return result;
    }

    @Override
    public boolean update(String id, String name, String login, String email, String pass, String country,
                          String city, int roleNum) {
        boolean res = false;
        int idNum = Integer.parseInt(id);
        User user = (User) store.findById(idNum);
        if (user != null) {
            user.setEmail(email);
            user.setLogin(login);
            user.setPass(pass);
            user.setCity(city);
            user.setCountry(country);
            user.setRole(roleNum);
            user.setName(name);
            res = store.update(idNum, user);
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        return store.delete(Integer.parseInt(id));
    }

    @Override
    public Map<Integer, User> findAll() {
        return store.findAll();
    }

    public User findById(String id) {
        return (User) store.findById(Integer.parseInt(id));
    }

    public Map<Integer, String> getRoles() {
        return new Role().getRoles();
    }

    public User getUserByLoginPass(String login, String pass) {
        return (User) store.userExists(login, pass);
    }

}
