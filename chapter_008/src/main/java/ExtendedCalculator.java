
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.job4j.calculator.Calculator;

/** Модифицировать класс Calculator согласно Single responsible principle.
 * В классе должен быть пользовательский ввод.
 * Повторный выбор операции и переиспользование предыдущего вычисления.
 */
public class ExtendedCalculator {

    private final Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)?\\s*(\\S)\\s*(-?\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
    private Calculator calculator;
    private Scanner scanner;
    private Double result;


    public ExtendedCalculator() {
        this.calculator = new Calculator();
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("Input expression or type \"quit\"");
        String input = this.scanner.nextLine();
        while (!("quit".equalsIgnoreCase(input))) {
            Matcher matcher = checkPattern(input);
            if (matcher.find()) {
                getOperands(matcher);
                System.out.println(result);
            } else {
                System.out.println("Input appropriate expression");
            }
            input = this.scanner.nextLine();
        }
    }

    private void  getOperands(Matcher matcher) {
        Double operand1;
        Double operand2;
        String sign;
        if (matcher.group(1) != null) { //первое число
            operand1 = Double.parseDouble(matcher.group(1)); //первое число
            operand2 = Double.parseDouble(matcher.group(3)); //второе
            sign = matcher.group(2); //знак
            calculate(operand1, operand2, sign);
        } else {
            operand2 = Double.parseDouble(matcher.group(3)); //записывает в переменную значение второго числа
            sign = matcher.group(2); //знак
            calculate(result, operand2, sign);
        }
    }


    private void calculate(Double op1, Double op2, String sign) {
        BiConsumer<Double, Double> func = chooseFunction(sign);
        if (func != null) {
            func.accept(op1, op2);
        } else {
            System.out.println("Input appropriate sign");
        }
        result = calculator.getRes();
    }


    private BiConsumer<Double, Double> chooseFunction(String sign) {
        BiConsumer<Double, Double> func = null;
        if ("+".equals(sign)) {
            func = (a, b) -> calculator.add(a, b);
        } else if ("-".equals(sign)) {
            func = (a, b) -> calculator.subtract(a, b);
        } else if ("/".equals(sign)) {
            func = (a, b) -> calculator.div(a, b);
        } else if ("*".equals(sign)) {
            func = (a, b) -> calculator.multiple(a, b);
        }
        return func;
    }

    private Matcher checkPattern(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public static void main(String[] args) {
        ExtendedCalculator calc = new ExtendedCalculator();
        calc.start();
    }

}
