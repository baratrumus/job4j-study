package servlets;

import models.Order;

import java.util.List;

public interface Controller {
    boolean addOrder(Order order);
    List<Order> getOrders();
}
