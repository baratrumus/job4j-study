/**
 *
 Создание игры крестики нолики.
 Вывод данных в консоль в псевдографики.
 Пользователь начинает игру. Вводи координаты точки.
 Предусмотреть, чтобы компьютер мог начать игру.
 Предусмотреть увеличение поле. По умолчанию используется поле 3 на 3.
 Предусмотреть усложнение логики игры. Выигрывает тот кто соберет 5 подряд
 */

public class StartGame {
    private Boolean exit = false;
    private Boolean userBegins = true;
    private int fieldSize;
    private DrawField field;
    private ConsoleInput input = new ConsoleInput();

    public StartGame(DrawField field) {
        this.field = new DrawField();
    }

    public void init() {
        this.userBegins = Boolean.valueOf(input.ask(
                "Choose who begins, for user begin press \"1\", for computer begin press \"0\":"));
        this.fieldSize = Integer.parseInt(input.ask("If field size more than 5, win always is 5 in line. "
                + "Input field size:"));
        String[][] moveTable = new String[fieldSize][fieldSize];
        String[] userMove;
        do {
            field.draw(fieldSize, moveTable);
            String inp = input.ask("Input coordinates of your move, two comma-separated didgits, \"q\" to quit:");
            if ("q".equalsIgnoreCase(inp)) {
                this.exit = true;
            } else {
               userMove = inp.split(",[ ]*");

            }
        } while (!this.exit);
    }


}
