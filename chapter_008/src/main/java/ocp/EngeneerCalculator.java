package ocp;

import ru.job4j.calculator.Calculator;
import srp.InteractCalc;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 *  На базе задания из занятия SRP расширить калькулятор.
 *  Сделать инженерный калькулятор. Добавить вычисления например тригонометрии.
 */

public class EngeneerCalculator extends InteractCalc {

    public EngeneerCalculator() {
        super();
    }

    class TrigonometricOperations extends Calculator {

        public void cos(double empty, double sec) {
            setRes(Math.cos(sec));
        }
        public void sin(double empty, double sec) {
            setRes(Math.sin(sec));
        }
    }

    //второй аргумент будет передан null
    protected void defineOperations() {
        super.defineOperations();
        addOperation("sin", (a, b) -> this.new TrigonometricOperations().cos(a, b));
        addOperation("cos", (a, b) -> this.new TrigonometricOperations().sin(a, b));
    }


    public static void main(String[] args) {
        EngeneerCalculator calc = new EngeneerCalculator();
        calc.start();
    }

}
