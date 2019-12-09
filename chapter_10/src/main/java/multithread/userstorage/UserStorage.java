package multithread.userstorage;

import java.util.ArrayList;
import java.util.List;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> users;

    public UserStorage() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public synchronized boolean add(User user) {
       return users.add(user);
    }

    public synchronized boolean update(User user) {
       boolean ret = false;
       if (users.set(users.indexOf(user), user) != null) {
           ret = true;
       }
       return ret;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean ret = false;
        User userFrom = findById(fromId);
        User userTo = findById(toId);
        if (userFrom != null && userTo != null) {
            ret = true;
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }

    private User findById(int id) {
        User ret = null;
        for (User user : users) {
            if (user.getId() == id) {
                ret = user;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        UserStorage stoge = new UserStorage();
        stoge.add(new User(1, 100));
        stoge.add(new User(2, 200));

        stoge.transfer(1, 2, 50);

    }

}
