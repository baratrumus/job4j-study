package multithread.problems;


/**
 Thread-0:   classPrimitive is 10000, classObject is 30000, localPrimitive is 300, localObject is 800
 Thread-1:   classPrimitive is 10000, classObject is 30000, localPrimitive is 300, localObject is 800
 Thread-1:   classPrimitive is 26639, classObject is 18599, localPrimitive is 10300, localObject is -9200
 Thread-0:   classPrimitive is 29179, classObject is 16059, localPrimitive is 10300, localObject is -9200
 *
 * Как мы видим переменные класса classPrimitive и  classObject, хранящиеся в куче имеют спутанные значения, в результате проблемы
 * Visibility, когда один поток считал зачение, изменил его, но не успел записал в основную память и
 * второй поток видит неизмененное значение.
 * И из-за проблемы Race condition, когда оба потока читают значение из памяти, меняют его,
 * но не знают об изменениях в соседнем потоке, т.к. они не синхронизированы.
 * Однако локальные переменные localPrimitive и localObject обрабатываются нормально, т.к.
 * сохраняются каждая в своем стеке потока и не пересекаются.
 */
public class Work implements Runnable {
    public int classPrimitive = 10000;
    public Integer classObject = 30000;

    @Override
    public void run() {
        int localPrimitive = 300;
        Integer localObject = 800;
        System.out.printf("%s:   classPrimitive is %d, classObject is %d, localPrimitive is %d, localObject is %d \n",
                Thread.currentThread().getName(), classPrimitive, classObject, localPrimitive, localObject);

        for (int i = 0; i < 10000; i++) {
            classPrimitive++;
            classObject--;
            localPrimitive++;
            localObject--;
            //int j = i * 300;
        }

        System.out.printf("%s:   classPrimitive is %d, classObject is %d, localPrimitive is %d, localObject is %d \n",
                Thread.currentThread().getName(), classPrimitive, classObject, localPrimitive, localObject);
    }
}
