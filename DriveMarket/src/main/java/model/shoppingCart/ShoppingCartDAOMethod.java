package model.shoppingCart;

import model.order.Order;
import model.product.Product;
import model.user.User;

public interface ShoppingCartDAOMethod {
	public ShoppingCart searchShoppingCart(User u);
	public int addProductToShoppingCart (String nick, int id_prod, int quantity);
	public void updateProductToShoppingCart (String nick, int id_prod, int quantity);
	public void doOrder(String nick, int id_prod, int id_order);
	public ShoppingCart getShoppingCartByOrder(Order o);
}
