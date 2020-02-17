package data;

import models.Order;

import java.util.List;

public interface Db {
    Order addNewOrder(Order order);
    List<Order> getOrders();
}
