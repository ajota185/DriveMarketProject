package model.shoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.order.Order;
import model.product.Product;
import model.product.ProductDAO;
import model.storage.Storage;
import model.user.User;

public class ShoppingCartDAO implements ShoppingCartDAOMethod
{
	
	@Override
	public ShoppingCart searchShoppingCart(User u) {
		try(Connection connection= Storage.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from ShoppingCart where nick=? and id_order IS NULL");
            ps.setString(1, u.getNickName());

            ResultSet rs=ps.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();
            ArrayList<Integer> quantity = new ArrayList<Integer>();
            ProductDAO productDAO = new ProductDAO();
            
            while (rs.next()){
            	
            	Product product = productDAO.searchProduct(rs.getInt(3));
            	products.add(product);
            	quantity.add(rs.getInt(4));
            	
            }
            
            if(!products.isEmpty()) {
            	ShoppingCart shoppingCart = new ShoppingCart();
            	shoppingCart.setProducts(products);
            	shoppingCart.setQuantity(quantity);
            	shoppingCart.setUser(u);
            	return  shoppingCart;
            }
            
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
	}

	@Override
	public int addProductToShoppingCart(String nick, int id_prod, int quantity) {
		try(Connection connection=Storage.getConnection()){
			int res = 0;
            PreparedStatement ps= connection.prepareStatement("insert into ShoppingCart (id_prod, nick, quantity) values (?,?,?)");
            ps.setInt(1,id_prod);
            ps.setString(2,nick);
            ps.setInt(3,quantity);
            ps.execute();
            PreparedStatement ps2= connection.prepareStatement("SELECT * from ShoppingCart");
            ResultSet rs = ps2.executeQuery();
            while(rs.next()) {
            	res = rs.getInt(1);
            }
            return res;
		
		}catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public void updateProductToShoppingCart(String nick, int id_prod, int quantity) {
		// TODO Auto-generated method stub
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update ShoppingCart set quantity = ? "+
            "where nick = ? AND id_prod = ? AND id_order IS NULL", Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1,quantity);
            ps.setString(2,nick);
            ps.setInt(3,id_prod);
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
	}

	@Override
	public void doOrder(String nick, int id_prod, int id_order) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update ShoppingCart set id_order = ? "+
            "where nick = ? AND id_prod = ? AND id_order IS NULL", Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1,id_order);
            ps.setString(2, nick);
            ps.setInt(3, id_prod);
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public ShoppingCart getShoppingCartByOrder(Order o) {
		try(Connection connection= Storage.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from ShoppingCart where nick=? and id_order=?");
            ps.setString(1, o.getUser().getNickName());
            ps.setInt(2, o.getId_order());

            ResultSet rs=ps.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();
            ArrayList<Integer> quantity = new ArrayList<Integer>();
            ProductDAO productDAO = new ProductDAO();
            
            while (rs.next()){
            	
            	Product product = productDAO.searchProduct(rs.getInt(3));
            	products.add(product);
            	quantity.add(rs.getInt(4));
            	
            }
            
            if(!products.isEmpty()) {
            	ShoppingCart shoppingCart = new ShoppingCart();
            	shoppingCart.setProducts(products);
            	shoppingCart.setQuantity(quantity);
            	shoppingCart.setUser(o.getUser());
            	shoppingCart.setOrder(o);
            	return  shoppingCart;
            }
            
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
	}

	

}
