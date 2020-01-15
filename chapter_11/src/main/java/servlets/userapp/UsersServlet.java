package servlets.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import servlets.crudservlet.*;

public class UsersServlet  extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);
    //private List<String> users = new CopyOnWriteArrayList<>();
    private final ValidateService logic = ValidateService.getInstance();

    /**
     * открывает таблицу со всеми пользователями.
     * В каждой строчке должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<Integer, User> users = logic.findAll();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = entry.getValue();
            sb.append("<tr><td>").append(user.toString()).append("</td>");
            sb.append("<td><form action='");
            sb.append(req.getContextPath());
            sb.append("/update' method='get'>");
            sb.append("<input type='hidden' name='id' value='");
            sb.append(user.getId());
            sb.append("'>");
            sb.append("<input type='hidden' name='name' value='");
            sb.append(user.getName());
            sb.append("'>");
            sb.append("<input type='hidden' name='login' value='");
            sb.append(user.getLogin());
            sb.append("'>");
            sb.append("<input type='hidden' name='email' value='");
            sb.append(user.getEmail());
            sb.append("'>");
            sb.append("<input type='submit' value='update'></form></td>");

            sb.append("<td><form action='");
            sb.append(req.getContextPath());
            sb.append("/list' method='post'>");
            sb.append("<input type='hidden' name='id' value='");
            sb.append(user.getId());
            sb.append("'>");
            sb.append("<input type='submit' value='delete'></form></td></tr>");
        }
        sb.append("</table>");
        writer.append(sb.toString());
        writer.flush();

       req.getRequestDispatcher("<урл второго сервлета>").forward(req, resp);
       //req.getServletContext().getNamedDispatcher(servletName).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        logic.delete(req.getParameter("id"));
        doGet(req, resp);
    }
}
