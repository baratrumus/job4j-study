/**
 *
 *  * Crosses - Zeros Game
 *  *
 *  * @author Ivannikov Ilya (voldores@mail.ru)
 *  * @version $Id$
 *  * @since 0.1
 *
 *
 Создание игры крестики нолики.
 Вывод данных в консоль в псевдографики.
 Пользователь начинает игру. Вводи координаты точки.
 Предусмотреть, чтобы компьютер мог начать игру.
 Предусмотреть увеличение поле. По умолчанию используется поле 3 на 3.
 Предусмотреть усложнение логики игры. Выигрывает тот кто соберет 5 подряд
 Пользователь ходит Х, компьютер 0
 */

public class StartGame {
    private static final String LN = System.lineSeparator();
    private Boolean exit = false;
    private String userBegins;
    private int fieldSize;
    private DrawField field;
    private Logic logic;
    private ConsoleInput input = new ConsoleInput();

    public StartGame() {
        this.field = new DrawField();
        this.logic = new Logic();
    }

    public void init() {
        this.userBegins = input.ask(
                "Choose who begins, for user begin press \"1\", for computer begin press \"0\":");
        this.fieldSize = Integer.parseInt(input.ask("If field size more than 5, win always is 5 in line. "
                + "Input field size:"));
        String[][] moveTable = new String[fieldSize][fieldSize];
        String[] userMove;
        do {
                field.draw(fieldSize, moveTable);
                if ("1".equals(userBegins)) {
                    String inp = input.ask("Input coordinates of your move, "
                            + "two comma-separated didgits (x - first, y - second), \"q\" to quit:");
                    if ("q".equalsIgnoreCase(inp)) {
                        this.exit = true;
                    } else if (!inp.isBlank()) {
                        userMove = getUserMove(moveTable, inp);
                        int userMoveX = Integer.parseInt(userMove[0]) - 1;
                        int userMoveY = Integer.parseInt(userMove[1]) - 1;
                        moveTable[userMoveX][userMoveY] = "X";
                        if (logic.isWin(moveTable, "X", userMoveX, userMoveY)) {   //user win check
                            this.exit = true;
                            System.out.println("User win");
                        }
                    }
                    userBegins = "0";
                } else if (!logic.fieldIsFull(moveTable)) {

                    String[] compMove = logic.getCompMove(moveTable);
                    int compMoveX = Integer.parseInt(compMove[0]);
                    int compMoveY = Integer.parseInt(compMove[1]);

                    moveTable[compMoveX][compMoveY] = "0";
                    System.out.println(String.format("Computer moves [%d,%d]" + LN,
                            compMoveX + 1, compMoveY + 1));
                    if (logic.isWin(moveTable, "0", compMoveX, compMoveY)) {   //computer win check
                         this.exit = true;
                         System.out.println("Computer win");
                    }
                    userBegins = "1";
                }
            if (logic.fieldIsFull(moveTable)) {
                this.exit = true;
                System.out.println("Field is full. Nobody wins");
            }
        } while (!this.exit);
    }


    private String[] getUserMove(String[][] moveTable, String inp) {
        String[] userMove = inp.split(",[ ]*");
        int userMoveX = Integer.parseInt(userMove[0]);
        int userMoveY = Integer.parseInt(userMove[1]);

        while (logic.checkIfBizy(moveTable, userMoveX - 1, userMoveY - 1)) {
            inp = input.ask("This point is bizy, input another coordinates:");
            userMove = inp.split(",[ ]*");
            userMoveX = Integer.parseInt(userMove[0]);
            userMoveY = Integer.parseInt(userMove[1]);
        }
        return userMove;
    }

    public static void main(String[] args) {
        StartGame sg = new StartGame();
        sg.init();
    }

}
