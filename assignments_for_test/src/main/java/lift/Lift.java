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
    private int liftOnStage;
    private boolean doorsClosed;
    boolean youInLift;


    public Lift(int stages, float stageHeight, float liftSpeed, int openCloseTime) {
        this.stages = stages;
        this.stageHeight = stageHeight;
        this.liftSpeed = liftSpeed;
        this.openCloseTime = openCloseTime;
        this.liftOnStage = 1;
        this.doorsClosed = true;
        youInLift = false;
    }


    public void userAction(Lift lift) {
        ConsoleInput input = new ConsoleInput();
        String inp = "";
        System.out.println("To call lift to your stage print c+stage (example c4)");
        System.out.println("To move in lift to stage print m+stage (example m1)");
        System.out.println("To exit program print q");

        while (!"q".equals(inp)) {
            inp = input.ask("Your input:");
            if (inp.contains("c") && !youInLift) {
                int yourStage = Character.getNumericValue(inp.charAt(1));
                Thread callToStage = new Thread(lift.new MoveLiftToStage(lift, yourStage));
                callToStage.start();
            } else if (inp.contains("m")) {
                youInLift = true;
                System.out.println(String.format("You are in lift on the %s stage", liftOnStage));
                int toStage = Character.getNumericValue(inp.charAt(1));
                Thread moveToStage = new Thread(lift.new MoveLiftToStage(lift, toStage));
                moveToStage.start();
            } else if (!inp.contains("q")) {
                System.out.println("Wrong input, make correct input.");
            }
        }
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
            stageHeight = 3F;
            liftSpeed = 1.5F;
            openCloseTime = 3;
        }
        Lift lift = new Lift(stages, stageHeight, liftSpeed, openCloseTime);
        lift.userAction(lift);
    }


    class MoveLiftToStage implements Runnable {
        Lift lift;
        int moveToStage;
        long oneStageTime = (long) (1000 * stageHeight / liftSpeed);


        public MoveLiftToStage(Lift lift, int stage) {
            this.lift = lift;
            this.moveToStage = stage;
        }

        @Override
        public void run() {
            System.out.println();
            System.out.println(String.format("Lift is on the %s stage", liftOnStage));
            if (youInLift) {
                doorOperation();
            }
            synchronized (lift) {
               do {
                    try {
                        liftOnStage = (liftOnStage < moveToStage) ? liftOnStage + 1 : liftOnStage - 1;
                        Thread.currentThread().sleep(oneStageTime);
                        System.out.println(String.format("Lift is on the %s stage", liftOnStage));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                } while (liftOnStage != moveToStage);
            }
            doorOperation();
        }


        private void doorOperation() {
            synchronized (lift) {
                try {
                    if (doorsClosed) {
                        System.out.printf("Doors are opening...");
                        Thread.currentThread().sleep(openCloseTime * 1000);
                        System.out.println(" Doors opened");
                        doorsClosed = false;
                    } else {
                        System.out.printf("Doors are closing...");
                        Thread.currentThread().sleep(openCloseTime * 1000);
                        System.out.println(" Doors closed");
                        doorsClosed = true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
