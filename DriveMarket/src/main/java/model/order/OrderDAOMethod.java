package model.order;

public interface OrderDAOMethod {
	public Order searchOrder(int id_order);
	public int addOrder(Order o);
}
