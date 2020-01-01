package multithread.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Реализовать сервис для рассылки почты. Создайте класс EmailNotification. *
 * 2. В классе будет метод emailTo(User user) - он должен через ExecutorService отправлять почту. *
 * Так же добавьте метод close() - он должен закрыть pool. То есть в классе EmailNotification должно быть поле pool,
 * которые используется в emailTo и close(). *
 * 3. Модель User описывают поля username, email. *
 * 4. Метод emailTo должен брать данные пользователя и подставлять в шаблон  *
 * subject = Notification {username} to email {email}. *
 * body = Add a new event to {username} *
 * 5. создайте метод public void send(String suject, String body, String email) - он должен быть пустой. *
 * 6. Через ExecutorService создайте задачу, которая будет создать данные для пользователя и передавать их в метод send.
 */


public class MailSender {
    //пул нитей по количеству доступных процессоров
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );


    /**
     * //Добавляем задачу в пул и сразу ее выполняем
     */
    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        pool.submit(() -> send(subject, body, user.getEmail()));

    }


    public void send(String subject, String body, String email) {
    }

    /**
     * Закрываем пул и ждем пока все задачи завершаться.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }

}

