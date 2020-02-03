package servlets.userapp;

import servlets.crudservlet.Logic;
import servlets.crudservlet.User;
import servlets.crudservlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInController   extends HttpServlet {

    private final Logic logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //autentification
        User u = logic.getUserByLoginPass(login, password);
        if (u != null) {
            HttpSession session = req.getSession();
            //synchronized (session) {
                session.setAttribute("login", login);
                session.setAttribute("roleName", u.getRole());
                session.setAttribute("id", u.getId());
            //}
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid crefentionals");
            doGet(req, resp);
        }
    }
}
