package servlets.userapp;

import servlets.crudservlet.User;
import servlets.crudservlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UserUpdateServlet  extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        Map<Integer, User> users = logic.findAll();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder();
        sb.append("<form action='");
        sb.append(req.getContextPath());
        sb.append("/update' method='post'>");
        sb.append("<label for='id1'>id</label>");
        sb.append("<input type='text' name='id1' value='");
        sb.append(req.getParameter("id"));
        sb.append("' disabled>");

        sb.append("<input type='hidden' name='id' value='");
        sb.append(req.getParameter("id"));
        sb.append("'><br>");
        sb.append("<label for='name'>name</label>");
        sb.append("<input type='text' name='name' value='");
        sb.append(req.getParameter("name"));
        sb.append("'><br>");
        sb.append("<label for='login'>login</label>");
        sb.append("<input type='text' name='login' value='");
        sb.append(req.getParameter("login"));
        sb.append("'><br>");
        sb.append("<label for='email'>email</label>");
        sb.append("<input type='text' name='email' value='");
        sb.append(req.getParameter("email"));
        sb.append("'><br>");
        sb.append("<input type='submit' value='update'></form>");
        writer.append(sb.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        logic.update(req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"));
        doGet(req, resp);
    }
}
