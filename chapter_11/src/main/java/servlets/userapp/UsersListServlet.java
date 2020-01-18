package servlets.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import servlets.crudservlet.*;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class UsersListServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersListServlet.class);
    private final ValidateService logic = ValidateService.getInstance();

    /**
     * открывает таблицу со всеми пользователями.
     * В каждой строчке должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, User> users = logic.findAll();
        req.setAttribute("userMap", users);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        logic.delete(id);
        doGet(req, resp);
    }
}
