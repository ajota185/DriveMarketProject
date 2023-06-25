package model.order;

import java.util.ArrayList;

import model.user.User;

public interface OrderDAOMethod {
	public Order searchOrder(int id_order);
	public int addOrder(Order o);
	public ArrayList<Order> getOrdersByUser(User u);
}
