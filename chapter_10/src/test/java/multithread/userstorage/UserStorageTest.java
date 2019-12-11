package multithread.userstorage;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    private class ThreadUserStore extends Thread {

        private final UserStorage store;

        private ThreadUserStore(UserStorage store) {
            this.store = store;
        }

        @Override
        public void run() {
            store.add(new User(1, 100));
            store.add(new User(2, 200));
            store.transfer(1, 2, 50);
            System.out.println("User1 amount: " + store.getUsers().get(0).getAmount()
                    + "\n" + "User2 amount: " + store.getUsers().get(1).getAmount());
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем счетчик.
        final UserStorage store = new UserStorage();
        //Создаем нити.
        Thread first = new UserStorageTest.ThreadUserStore(store);
        Thread second = new UserStorageTest.ThreadUserStore(store);
        //Запускаем нити.
        first.start();
        second.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        //Проверяем результат.
        assertThat(store.getUsers().size(), is(4));
    }
}