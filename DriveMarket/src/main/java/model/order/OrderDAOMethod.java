package model.order;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.user.User;

public interface OrderDAOMethod {
	public Order searchOrder(int id_order);
	public int addOrder(Order o);
	public ArrayList<Order> getOrdersByUser(User u);
	public ArrayList<Order> getOrdersByUserAndDate(User u, Timestamp date_s, Timestamp date_t);
}
