package multithread.blockcustomer;

public class ProducerConsumer {

    private final Object lock = new Object();
    private boolean blockCustomer = true;

    public void doSomething() throws InterruptedException {
        synchronized (this.lock) {
            while (blockCustomer) {
                System.out.println(String.format("%s wait ", Thread.currentThread().getName()));
                lock.wait();
            }
            System.out.println(String.format("%s usefull work ", Thread.currentThread().getName()));
            //usefull work
        }
    }

    public void changeBlock(boolean sign) {
        synchronized (this.lock) {
            System.out.println(String.format("%s %s", Thread.currentThread().getName(), sign));
            blockCustomer = sign;
            lock.notify();
        }
    }

    public static void main(String[] args)  throws InterruptedException {
        final ProducerConsumer blockingWork = new ProducerConsumer();

        //producer
        Thread producer = new Thread() {
            @Override
            public void run() {
                blockingWork.changeBlock(false);
            }
        };
        //consumer
        Thread consumer = new Thread() {
            @Override
            public void run() {
                try {
                    blockingWork.doSomething();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        producer.start();
        consumer.start();

    }
}
