package srp;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import ru.job4j.calculator.Calculator;

/** Модифицировать класс Calculator согласно Single responsible principle.
 * В классе должен быть пользовательский ввод.
 * Повторный выбор операции и переиспользование предыдущего вычисления.
 */
public class InteractCalc {
    //
    // 1 группа (-?\d+\.?\d*)?
    // 2 группа \s*(\D+)\s* --- \s пробел   --- \D+ одна или больше не цифра
    // 3 группа (-?\d+\.?\d*)
    private final Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)?\\s*(\\D+)\\s*(-?\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
    private Calculator calculator;
    private Scanner scanner;
    private Double result = 0D;
    private HashMap<String, BiConsumer<Double, Double>> chooseFunction;


    public InteractCalc() {
        this.calculator = new Calculator();
        this.scanner = new Scanner(System.in);
        chooseFunction = new HashMap<>();
        defineOperations();
    }

    public void start() {

        System.out.println("Input expression or type \"quit\"");
        String input = this.scanner.nextLine();
        while (!("quit".equalsIgnoreCase(input))) {
            Matcher matcher = checkPattern(input);
            if (matcher.find()) {
                getOperands(matcher);
            } else {
                System.out.println("Input appropriate expression");
            }
            input = this.scanner.nextLine();
        }
    }

    private void  getOperands(Matcher matcher) {
        String oper1 = matcher.group(1); //первое число;
        Double operand1;
        if (oper1 != null) {
            operand1 = Double.parseDouble(oper1);
        } else {
            operand1 = 0D;
        }
        Double operand2 = Double.parseDouble(matcher.group(3)); //второе
        String sign =  matcher.group(2).trim(); //знак
        if (result != 0 && operand1 == 0) { //вариант использования результа пред вычисления
            calculate(result, operand2, sign);
        } else {
            calculate(operand1, operand2, sign);
        }
    }


    private void calculate(Double op1, Double op2, String operation) {
        BiConsumer<Double, Double> func = chooseFunction.get(operation);
        if (func != null) {
            func.accept(op1, op2);
            result = calculator.getRes();
            System.out.println(result);
        } else {
            System.out.println("Input appropriate operation");
        }
    }


    protected void defineOperations() {
        chooseFunction.put("+", (a, b) -> calculator.add(a, b));
        chooseFunction.put("-", (a, b) -> calculator.subtract(a, b));
        chooseFunction.put("/", (a, b) -> calculator.div(a, b));
        chooseFunction.put("*", (a, b) -> calculator.multiple(a, b));
    }

    protected void addOperation(String op, BiConsumer<Double, Double> func) {
        chooseFunction.put(op, func);
    }


    private Matcher checkPattern(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc();
        calc.start();
    }

}
