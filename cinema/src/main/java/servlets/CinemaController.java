package servlets;

import data.CinemaDB;
import data.Db;
import models.Order;
import java.util.List;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class CinemaController implements Controller {

    private static final CinemaController INSTANCE = new CinemaController();

    private CinemaController() {
    }

    public static CinemaController getInstance() {
        return INSTANCE;
    }

    private static final Db STORAGE = CinemaDB.getInstance();

    @Override
    public boolean addOrder(Order order) {
        STORAGE.addNewOrder(order);
        return true;
    }

    @Override
    public List<Order> getOrders() {
        return STORAGE.getOrders();
    }
}
