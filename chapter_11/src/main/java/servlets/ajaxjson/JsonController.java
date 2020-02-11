package servlets.ajaxjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Создайте сервлет, который будет принимать JSON объект.
 *  Поместить json в ConcurrentHashMap
 *          2. Через JQuery создайте событие на нажатие кнопки, которые будет создавать вызов на сервер.
 *
 *          Когда данные пришли на клиент, нужно добавить новую запись в таблицу.
 */

public class JsonController   extends HttpServlet {
    private ConcurrentHashMap<Integer, Map<String, String>> storage = new ConcurrentHashMap();
    private AtomicInteger id = new AtomicInteger(1);


    public JsonController() {
        HashMap<String, String> oneUser = new HashMap<>();
        oneUser.put("name", "vaso");
        oneUser.put("surname", "bu");
        oneUser.put("gender", "male");
        oneUser.put("desc", "sth");
        this.storage.put(this.id.get(), oneUser);
        id.addAndGet(1);
    }



    /**
     * Отдаем мапу клиенту как json
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(storage);
        response.getWriter().write(jsonInString);
        response.getWriter().flush();
    }

    /**
     *    принимаем не JSON объект, просто параметры, данные пользователя
     *    и пишем его в storage
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Для получения JSON объектов
        //BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        //String requestData = reader.lines().collect(Collectors.joining());
        //ObjectMapper mapper = new ObjectMapper();
        //User user = mapper.readValue(requestData, User.class);

        //Set<String> parameterNames = request.getParameterMap().keySet();
        Enumeration keys = request.getParameterNames();
        HashMap oneUserData = new HashMap();

        String key;
        while (keys.hasMoreElements()) {
            key = keys.nextElement().toString();
            oneUserData.put(key, request.getParameter(key));
        }

        storage.put(this.id.get(), oneUserData);
        id.addAndGet(1);

        if (oneUserData.size() > 0) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("error add user");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
