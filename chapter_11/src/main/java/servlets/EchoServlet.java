package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServlet.class);
    private List<String> users = new CopyOnWriteArrayList<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String login = req.getParameter("login");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        writer.append("hello world, " + users);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       users.add(req.getParameter("login"));
       doGet(req, resp);
    }
}
