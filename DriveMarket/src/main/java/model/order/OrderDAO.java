package model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.storage.Storage;

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
            PreparedStatement ps= connection.prepareStatement("insert into Pedido (date,time) values (?,?)");
            ps.setDate(1,o.getDate());
            ps.setTime(2,o.getHour());
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

}
