package servlets.userapp;

import servlets.crudservlet.User;
import servlets.crudservlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class UserUpdateServlet  extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = logic.findById(req.getParameter("id"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logic.update(req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"));
        req.setAttribute("userMap", logic.findAll());
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }
}
