package model.user;

import model.shoppingCart.ShoppingCart;
import model.order.Order;

import java.util.ArrayList;
import java.util.Objects;

public class User {
	private String nickName;
	private String passw;
	private String email;
	private boolean isAdmin;
	private ShoppingCart shoppingCart;
	
	public User() {
	}
	
	public User(String nickName, String passw, String email, boolean isAdmin, ShoppingCart shoppingCart) {
		this.nickName = nickName;
		this.passw = passw;
		this.email = email;
		this.isAdmin = isAdmin;;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	

	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(nickName, user.nickName);
	}
	
	
}
