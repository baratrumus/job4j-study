package lift;

/**
 * Предлагаем вам решить тестовое задание — написать программу «симулятор лифта».
 * Программа запускается из командной строки, в качестве параметров задается:
 *     кол-во этажей в подъезде — N (от 5 до 20);
 *     высота одного этажа;
 *     скорость лифта при движении в метрах в секунду (ускорением пренебрегаем, считаем,
 *     что когда лифт едет — он сразу едет с определенной скоростью);
 *     время между открытием и закрытием дверей.
 * После запуска программа должна постоянно ожидать ввода от пользователя и выводить действия
 * лифта в реальном времени. События, которые нужно выводить:
 *     лифт проезжает некоторый этаж;
 *     лифт открыл двери;
 *     лифт закрыл двери.
 * Возможный ввод пользователя:
 *     вызов лифта на этаж из подъезда;
 *     нажать на кнопку этажа внутри лифта.
 * Считаем, что пользователь не может помешать лифту закрыть двери.
 */
public class Lift {

    private final int stages;
    private final float stageHeight;
    private final float liftSpeed;
    private final int openCloseTime;

    public Lift(int stages, float stageHeight, float liftSpeed, int openCloseTime) {
        this.stages = stages;
        this.stageHeight = stageHeight;
        this.liftSpeed = liftSpeed;
        this.openCloseTime = openCloseTime;
    }

    public static void main(String[] args) {
        int stages;
        float stageHeight;
        float liftSpeed;
        int openCloseTime;
        if (args.length != 0) {
            stages = Integer.parseInt(args[0]);
            stageHeight = Float.parseFloat(args[1]);
            liftSpeed = Float.parseFloat(args[2]);
            openCloseTime = Integer.parseInt(args[3]);
        } else {
            stages = 6;
            stageHeight = 2.5F;
            liftSpeed = 2.5F;
            openCloseTime = 5;
        }
        Lift lift = new Lift(stages, stageHeight, liftSpeed, openCloseTime);
        Thread liftRun = new Thread(lift.new RunLift());
        liftRun.start();
    }


    class RunLift implements Runnable {
        ConsoleInput input = new ConsoleInput();


        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                String inp = input.ask("Введите этаж";
               /* try {

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }*/
            }
        }
    }
}
