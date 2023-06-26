package model.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.product.Product;
import model.product.ProductDAO;
import model.shoppingCart.ShoppingCart;
import model.storage.Storage;
import model.user.User;

public class OrderDAO implements OrderDAOMethod{

	@Override
	public Order searchOrder(int id_order) {
		Connection connection = null;
		PreparedStatement ps = null;
		Order order = null;
		try{
			connection= Storage.getConnection();
            ps=connection.prepareStatement("select * from Order where id_order=?");
            ps.setInt(1,id_order);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                order= new Order();
                order.setId_order(resultSet.getInt(1));
                order.setDate(resultSet.getTimestamp(2));
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
        return order;
	}

	@Override
	public int addOrder(Order o) {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int res = 0;
		try{
			connection=Storage.getConnection();
            ps= connection.prepareStatement("insert into Pedido (date,nick) values (?,?)");
            ps.setTimestamp(1,o.getDate());
            ps.setString(2, o.getUser().getNickName());
            ps.execute();
            ps2= connection.prepareStatement("SELECT * from Pedido");
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
	public ArrayList<Order> getOrdersByUser(User u) {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Order> orders = new ArrayList<Order>();
		try{
			connection = Storage.getConnection();
            ps=connection.prepareStatement("select * from Pedido where nick=?");
            ps.setString(1, u.getNickName());

            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
            	Order order = new Order();
            	order.setId_order(rs.getInt(1));
            	order.setDate(rs.getTimestamp(2));
            	order.setUser(u);
            	orders.add(order);
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
		
		if(!orders.isEmpty()) {
			return  orders;
		}
        return null;
	}

	@Override
	public ArrayList<Order> getOrdersByUserAndDate(User u, Timestamp date_s, Timestamp date_t) {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Order> orders = new ArrayList<Order>();
		try{
			connection = Storage.getConnection();
            ps=connection.prepareStatement("select * from Pedido where nick=? AND (? <=date AND date<=?)");
            ps.setString(1, u.getNickName());
            ps.setTimestamp(2, date_s);
            ps.setTimestamp(3, date_t);

            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
            	Order order = new Order();
            	order.setId_order(rs.getInt(1));
            	order.setDate(rs.getTimestamp(2));
            	order.setUser(u);
            	orders.add(order);
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
		
		if(!orders.isEmpty()) {
        	return  orders;
        }
		
        return null;
	}

}
