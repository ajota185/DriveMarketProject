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
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{
			connection= Storage.getConnection();
            ps=connection.prepareStatement("select * from ShoppingCart where nick=? and id_order IS NULL");
            ps.setString(1, u.getNickName());

            ResultSet rs=ps.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();
            ArrayList<Integer> quantity = new ArrayList<Integer>();
            ProductDAO productDAO = new ProductDAO();
            
            while (rs.next()){
            	
            	Product product = productDAO.searchProduct(rs.getInt(3));
            	product.setPrice(rs.getFloat(6));
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
        }finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					Storage.releaseConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
        return null;
	}

	@Override
	public int addProductToShoppingCart(String nick, int id_prod, int quantity, float price) {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try{
			connection=Storage.getConnection();
			int res = 0;
            ps= connection.prepareStatement("insert into ShoppingCart (id_prod, nick, quantity, price) values (?,?,?,?)");
            ps.setInt(1,id_prod);
            ps.setString(2,nick);
            ps.setInt(3,quantity);
            ps.setFloat(4, price);
            ps.execute();
            ps2= connection.prepareStatement("SELECT * from ShoppingCart");
            ResultSet rs = ps2.executeQuery();
            while(rs.next()) {
            	res = rs.getInt(1);
            }
            return res;
		
		}catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }finally {
			try {
				if (ps != null)
					ps.close();
				if(ps2!=null)
					ps2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					Storage.releaseConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void updateProductToShoppingCart(String nick, int id_prod, int quantity, float price) {
		Connection connection = null;
		PreparedStatement ps = null;
		try  {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("update ShoppingCart set quantity = ?, price=? "+
            "where nick = ? AND id_prod = ? AND id_order IS NULL", Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1,quantity);
            ps.setFloat(2, price);
            ps.setString(3,nick);
            ps.setInt(4,id_prod);
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					Storage.releaseConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }
	}

	@Override
	public void deleteProductToShoppingCart(String nick, int id_prod) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("delete from ShoppingCart where nick=? AND id_prod=? AND id_order IS NULL");
            ps.setString(1, nick);
            ps.setInt(2, id_prod);
            
            ps.execute();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					Storage.releaseConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }
		
	}
	
	
	@Override
	public void doOrder(String nick, int id_prod, int id_order) {
		Connection connection = null;
		PreparedStatement ps = null;
		try  {
			connection = Storage.getConnection();
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
        }finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					Storage.releaseConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }
		
	}

	@Override
	public ShoppingCart getShoppingCartByOrder(Order o) {
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection= Storage.getConnection();

            ps=connection.prepareStatement("select * from ShoppingCart where nick=? and id_order=?");
            ps.setString(1, o.getUser().getNickName());
            ps.setInt(2, o.getId_order());

            ResultSet rs=ps.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();
            ArrayList<Integer> quantity = new ArrayList<Integer>();
            ProductDAO productDAO = new ProductDAO();
            
            while (rs.next()){
            	
            	Product product = productDAO.searchProduct(rs.getInt(3));
            	product.setPrice(rs.getFloat(6));
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
        }finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					Storage.releaseConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
        return null;
	}


	

}
