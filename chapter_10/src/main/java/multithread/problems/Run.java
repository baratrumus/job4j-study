package multithread.problems;


/**
 *
 Создать пример иллюстрирующий проблемы, которые могут случиться при использовании многопоточности.
 В ответе к задаче напишите текстом в комментарии. какие проблемы есть и почему они возникают в вашем коде.
 */
public class Run {
    public static void main(String[] args) {
        Work work = new Work();
        Thread run1 = new Thread(work);
        run1.start();


        Thread run2 = new Thread(work);
        run2.start();

    }
}
