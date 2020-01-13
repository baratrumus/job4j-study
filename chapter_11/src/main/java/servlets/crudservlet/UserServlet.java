package servlets.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Все приложение должно быть разбита на слои.
 * Presentation - Это слой сервлетов. В них приложение должно получать данные от клиента и отдавать данные клиенту.
 * Logic - Выполнение бизнес логики. Например, проверить существует ли такой уже пользователь или нет. Если существует, то вернуть ошибку в слой Presentation.
 * Persistent - Слой для хранения данных. Может быть: базой данных, памятью или файловой системой.
 * Каждый слой взаимодействует с другим слоем за счет абстракции(интерфейса). Слои могут взаимодействовать только с ниже стоящим слоем.
 * Например. Presentation - Logic. Logic - Persistent. Нельзя Presentation - Persistent или Persisten - Logic.
 */

/***
 * Presentation layout.
 */
public class UserServlet extends HttpServlet {
    //получаем экземпляр нижестоящего слоя logic
    private final ValidateService logic = ValidateService.getInstance();

    //dispatch pattern contains destinations for Action parameter
    private final Actions actions;


    public UserServlet() {
        this.actions = new Actions();
    }

    /**
     * должен отдавать список всех пользователей в системе.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<Integer, User> users = logic.findAll();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = entry.getValue();
            writer.append(user.toString() + "\n");
        }
        writer.flush();
    }

    /**
     * должен  уметь создавать пользователя, обновлять поля пользователя, удалять пользователя.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String id = req.getParameter("id");
        String[] parameters = new String[] {id, name, login, email};
        actions.getDispatcher().get(action).apply(parameters);

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        doGet(req, resp);
    }


    public class Actions {
        /**
         * Function gets array of parameters and returns boolean
         */
        private final Map<String, Function<String[], Boolean>> dispatcher;

        public Actions() {
            dispatcher = new HashMap<>();
            this.dispatcher.put("add", this.create());  //Load handlers for destinations.
            this.dispatcher.put("update", this.update());
            this.dispatcher.put("delete", this.delete());
        }

        public Map<String, Function<String[], Boolean>> getDispatcher() {
            return this.dispatcher;
        }

        //добавление юзера передаем имя, логин, емайл
        public Function<String[], Boolean> create() {
            return params -> {
                return logic.add(params[1], params[2], params[3]);
            };
        }

        //обновление передаем новые имя, логин, емайл и id который обновляем
        public Function<String[], Boolean> update() {
            return params -> {
                    return logic.update(params[0], params[1], params[2], params[3]);
            };
        }

        //удаление передаем id
        public Function<String[], Boolean> delete() {
            return params -> {
                return logic.delete(params[0]);
            };
        }
    }
}
