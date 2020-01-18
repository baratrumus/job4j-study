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

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class EchoServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServlet.class);
    private List<String> users = new CopyOnWriteArrayList<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (String login : users) {
            sb.append("<tr><td>" + login + "</td></tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>EchoServlet</title>"
                + "</head> "
                + "<body>"
                + "<form action='" + req.getContextPath() + "/echo' method='post'>"
                + "Name : <input type='text' name='login'/>"
                + "<input type='submit'"
                + "</form>"
                + "<br>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       users.add(req.getParameter("login"));
       doGet(req, resp);
    }
}
