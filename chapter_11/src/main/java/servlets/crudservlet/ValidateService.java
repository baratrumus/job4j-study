package servlets.crudservlet;

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
 */

public class ValidateService {
    private final static ValidateService SINGLETON_INSTANCE = new ValidateService();
    //получаем экземпляр нижестоящего слоя Persistent
    //private final Store store = MemoryStore.getInstance();
    private final Store DbStore = DBStore.getInstance();

    public static ValidateService getInstance() {
        return SINGLETON_INSTANCE;
    }

    public boolean add(String name, String login, String email) {
        int id = store.getNextId();
        User user = new User(id, name, login, email);
        return store.add(user);
    }

    public boolean update(String id, String name, String login, String email) {
        boolean res = false;
        int idNum = Integer.parseInt(id);
        User user = store.findById(idNum);
        if (user != null) {
            user.setEmail(email);
            user.setLogin(login);
            user.setName(name);
            res = store.update(idNum, user);
        }
        return res;
    }

    public boolean delete(String id) {
        return store.delete(Integer.parseInt(id));
    }

    public Map<Integer, User> findAll() {
        return store.findAll();
    }

    public User findById(int id) {
        return  null;
    }

}
