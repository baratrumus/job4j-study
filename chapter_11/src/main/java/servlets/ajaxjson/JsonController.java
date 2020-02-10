package servlets.ajaxjson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.stream.Collectors;
import servlets.crudservlet.*;


/**
 * Создайте сервлет, который будет принимать JSON объект.
 * Для этого нужно в сервлете прочитать content.
 * BufferedReader reader = request.getReader();
 * Преобразовать его в StringBuilder.
 * Далее с помощью библиотеки jakson преобразовать в объект.
 * Данные нужно хранить в памяти в карте ConcurrencyHashMap.
 * 2. Через JQuery в задании #58522 создайте событие на нажатие кнопки, которые будет создавать вызов на сервер.
 * $.ajax({
 *    type: "POST",
 *    url: url,
 *    data: data,
 *    success: success,
 *    dataType: dataType
 * });
 * 3. Когда данные пришли на клиент, нужно добавить новую запись в таблицу.
 */

public class UserCreateJsonController extends HttpServlet {
    private final Logic logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //BufferedReader reader = req.getReader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String requestData = reader.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(requestData, User.class);
        user.setDate(LocalDate.now());
        if (logic.add(user, null, "")) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().append("error create user");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
