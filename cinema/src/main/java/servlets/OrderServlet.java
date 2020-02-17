package servlets;

import models.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class OrderServlet extends HttpServlet {

    private final Controller storage = CinemaController.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String place = req.getParameter("place");
        // 1-й заход в get.  Делаем редирект
        if (place != null) {
            synchronized (getServletContext()) {
                getServletContext().setAttribute("place", place);
                session.setAttribute("orderInfo", "");
            }
            resp.sendRedirect(String.format("%s/pay.html", req.getContextPath()));
            // 2-й заход в get.  Здесь можно записать в response
        } else {
            synchronized (getServletContext()) {
                place = (String) getServletContext().getAttribute("place");
            }
            String info = session.getAttribute("orderInfo").toString();
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pr = resp.getWriter();
            StringBuilder sb = new StringBuilder();
            sb.append("You have chosen row ");
            sb.append(place.charAt(0));
            sb.append(" seat ");
            sb.append(place.charAt(1));
            sb.append(", Sum : 10$");
            sb.append(", " + info);
            LOG.info(sb.toString());
            pr.append(new ObjectMapper().writeValueAsString(sb));
            pr.flush();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        boolean isPlaceFree = true;
        String place;
        synchronized (getServletContext()) {
            place = (String) getServletContext().getAttribute("place");
        }
        if (place != null) {
            if (!storage.getOrders().isEmpty()) {
                for (Order ord : storage.getOrders()) {
                    if ((String.valueOf(place.charAt(1))).equals(ord.getColumn()) & (String.valueOf(place.charAt(0))).equals(ord.getRow())) {
                        String error = "Place is buzy, please select another place!";
                        LOG.info(error);
                        session.setAttribute("orderInfo", error);
                       /*
                        PrintWriter writer = resp.getWriter();
                        writer.append(new ObjectMapper().writeValueAsString(error));
                        writer.flush();*/
                        isPlaceFree = false;
                        break;
                    }
                }
            }
            if (isPlaceFree) {
                resp.setContentType("text/json");
                resp.setCharacterEncoding("UTF-8");
                BufferedReader reader = req.getReader();
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                    System.out.println(sb);
                }
                reader.close();
                Order order = new Gson().fromJson(sb.toString(), Order.class);
                order.setColumn(String.valueOf(place.charAt(1)));
                order.setRow(String.valueOf(place.charAt(0)));
                storage.addOrder(order);
                String message = "Your ticket has added!";
                LOG.info(message);
                session.setAttribute("orderInfo", message);

                /*PrintWriter writer = resp.getWriter();
                writer.append(new ObjectMapper().writeValueAsString(message));
                writer.flush();*/
            }
        }
    }
}