package servlets;

import com.google.gson.Gson;
import models.Order;
import models.Seat;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class HallServlet extends HttpServlet {

    private final Controller storage = CinemaController.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first = req.getParameter("first");
        if (first == null) {
            resp.sendRedirect(String.format("%s/hall.html", req.getContextPath()));
        } else {
            ArrayList<Seat> listOfSeats = new ArrayList<>();
            for (Order order : storage.getOrders()) {
                listOfSeats.add(new Seat(order.getRow(), order.getColumn()));
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pr = resp.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(listOfSeats);
            pr.write(jsonInString);
            pr.flush();

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
    }
}