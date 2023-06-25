package model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.product.Product;
import model.product.ProductDAO;
import model.shoppingCart.ShoppingCart;
import model.storage.Storage;
import model.user.User;

public class OrderDAO implements OrderDAOMethod{

	@Override
	public Order searchOrder(int id_order) {
		try(Connection connection= Storage.getConnection()){
            PreparedStatement preparedStatement;
            preparedStatement=connection.prepareStatement("select * from Order where id_order=?");
            preparedStatement.setInt(1,id_order);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Order order= new Order();
                order.setId_order(resultSet.getInt(1));
                order.setDate(resultSet.getDate(2));
                order.setHour(resultSet.getTime(3));
                return order;
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
	}

	@Override
	public int addOrder(Order o) {
		try(Connection connection=Storage.getConnection()){
			int res = 0;
            PreparedStatement ps= connection.prepareStatement("insert into Pedido (date,time,nick) values (?,?,?)");
            ps.setDate(1,o.getDate());
            ps.setTime(2,o.getHour());
            ps.setString(3, o.getUser().getNickName());
            ps.execute();
            PreparedStatement ps2= connection.prepareStatement("SELECT * from Pedido");
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
	public ArrayList<Order> getOrdersByUser(User u) {
		try(Connection connection= Storage.getConnection()){
			ArrayList<Order> orders = new ArrayList<Order>();
            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Pedido where nick=?");
            ps.setString(1, u.getNickName());

            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
            	Order order = new Order();
            	order.setId_order(rs.getInt(1));
            	order.setDate(rs.getDate(2));
            	order.setHour(rs.getTime(3));
            	order.setUser(u);
            	orders.add(order);
            }
            
            if(!orders.isEmpty()) {
            	return  orders;
            }
            
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
	}

}
