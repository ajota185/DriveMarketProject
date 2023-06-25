package model.order;

import java.sql.Timestamp;
import java.util.ArrayList;

import model.shoppingCart.ShoppingCart;
import model.user.User;

public class Order {
	private int id_order;
	private Timestamp date;
	private User user;
	


	public Order() { }
	
	
	public Order(int id_order, Timestamp date) {
		this.id_order = id_order;
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

	
	
	
}
