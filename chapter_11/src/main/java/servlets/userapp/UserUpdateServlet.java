package servlets.userapp;


import com.google.gson.Gson;
import org.json.JSONObject;
import servlets.crudservlet.Cities;
import servlets.crudservlet.Logic;
import servlets.crudservlet.User;
import servlets.crudservlet.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class UserUpdateServlet  extends HttpServlet {

    private final Logic logic = ValidateService.getInstance();

    /**
     *  from UserList, when certain user update pressed, we send via ajax parameters to list again and fill form
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = logic.findById(req.getParameter("id"));
        /*req.setAttribute("user", user);
        Map<Integer, User> users = logic.findAll();
        req.setAttribute("userMap", users);
        req.setAttribute("roleMap", logic.getRoles());
        String[] cities = Cities.getNames();
        req.setAttribute("citiesList", cities);
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
*/
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String json1 = new Gson().toJson(user);
        String json2 = new Gson().toJson(logic.getRoles());

        JSONObject js1 = new JSONObject(json1);
        JSONObject js2 = new JSONObject(json2);
        JSONObject js3 = new JSONObject(getCitiesJson());
        JSONObject combined = new JSONObject();
        combined.put("User", js1);
        combined.put("Roles", js2);
        combined.put("City", js3);
        resp.getWriter().write(combined.toString());

        /* Записать как строку, не json
        PrintWriter pr = resp.getWriter();
        pr.append(new ObjectMapper().writeValueAsString(user));
        pr.flush();
        */


    }

    /**
     *  updating user in table with ajax on jsp, get Json User
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");

        //установлено enctype="multipart/form-data", поэтому их нельзя принять req.getParameter()
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String requestData = reader.lines().collect(Collectors.joining());
        //User user = new Gson().fromJson(requestData, User.class);

        Map<String, String> paramMap = new HashMap<>();
        String[] parameters = requestData.split("&");
        for (String parameter : parameters) {
            String[] keyValuePair = parameter.split("=");
            paramMap.put(keyValuePair[0], keyValuePair[1]);
        }

        String email = URLDecoder.decode(paramMap.get("emailInput"), "UTF-8");
        Integer roleKey = Integer.parseInt(paramMap.get("roleInput"));
        logic.update(paramMap.get("idReal"),
                paramMap.get("nameInput"),
                paramMap.get("loginInput"),
                email,
                paramMap.get("passInput"),
                paramMap.get("CountryInput"),
                paramMap.get("CityInput"),
                roleKey);
        req.setAttribute("userMap", logic.findAll());
        //String newRoleName = logic.getRoles().get(roleKey);
        //session.setAttribute("roleName", newRoleName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }




    private String getCitiesJson() {
        String json3 = "{\"";
        int size = Cities.getNames().length;
        for (int i = 1; i < size; i++) {
            json3 += i;
            json3 += "\":\"";
            json3 += Cities.getNames()[i - 1];
            json3 += "\",\"";
        }
        json3 += size + "\":\"" + Cities.getNames()[size - 1] + "\"}";
        return json3;
    }


}
