package model.order;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import model.shoppingCart.ShoppingCart;
import model.user.User;

public class Order {
	private int id_order;
	private Date date;
	private Time hour;
	
	public Order() { }
	
	
	public Order(int id_order, Date dataOrder, Time hour) {
		this.id_order = id_order;
		this.date = dataOrder;
		this.hour = hour;
	}
	
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getHour() {
		return hour;
	}
	public void setHour(Time hour) {
		this.hour = hour;
	}

	
	
	
}
