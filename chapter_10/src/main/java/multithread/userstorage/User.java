package multithread.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class User {
    private int id;
    @GuardedBy("this")
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId()
                && getAmount() == user.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }
}
