package servlets.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;
import servlets.crudservlet.User;
import servlets.crudservlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserCreateServlet  extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServlet.class);
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder();
        sb.append("<form action='");
        sb.append(req.getContextPath());
        sb.append("/create' method='post'>");
        sb.append("<table><tr><td><label for='name'>name</label></td>");
        sb.append("<td><input type='text' name='name'></td></tr>");
        sb.append("<tr><td><label for='login'>login</label></td>");
        sb.append("<td><input type='text' name='login'></td></tr>");
        sb.append("<tr><td><label for='email'>email</label></td>");
        sb.append("<td><input type='text' name='email'></td></tr>");
        sb.append("<tr><td><input type='submit' value='create'></td></tr></table></form>");

        writer.append(sb.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String name = req.getParameter("name");
        if (logic.add(name,
                req.getParameter("login"),
                req.getParameter("email"))) {
            writer.append("user ").append(name).append(" created");
            writer.flush();
        }
        doGet(req, resp);
    }
}
