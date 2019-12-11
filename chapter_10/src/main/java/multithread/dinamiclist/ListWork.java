package multithread.dinamiclist;

public class ListWork implements Runnable {
    public ThreadSafeList da = new ThreadSafeList(11);

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            da.add(i);
           /* try {
                Thread.currentThread().sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public static void main(String[] args)  throws InterruptedException {
        ListWork tlist = new ListWork();
        Thread thread1 = new Thread(tlist);
        thread1.start();
        Thread thread2 = new Thread(tlist);
        thread2.start();
        thread1.join();
        thread2.join();

        for (var el : tlist.da) {
            System.out.println(el);
        }
       // Thread.currentThread().sleep(100L);
    }

}
