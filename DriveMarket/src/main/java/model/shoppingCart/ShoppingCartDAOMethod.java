package model.shoppingCart;

import model.order.Order;
import model.product.Product;
import model.user.User;

public interface ShoppingCartDAOMethod {
	public ShoppingCart searchShoppingCart(User u);
	public int addProductToShoppingCart (String nick, int id_prod, int quantity, float price);
	public void updateProductToShoppingCart (String nick, int id_prod, int quantity, float price);
	public void deleteProductToShoppingCart(String nick, int id_prod);
	public void doOrder(String nick, int id_prod, int id_order);
	public ShoppingCart getShoppingCartByOrder(Order o);
}
