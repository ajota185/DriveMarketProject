package model.product;

import model.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ProductDAO implements ProductDAOMethod{

	@Override
	public Product searchProduct(int id_prod) {
		try(Connection connection= Storage.getConnection()){
            PreparedStatement preparedStatement;
            preparedStatement=connection.prepareStatement("select * from Producto where id_prod=?");
            preparedStatement.setInt(1,id_prod);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Product product= new Product();
                product.setId_prod(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                product.setDescription(resultSet.getString(4));
                product.setMain_photo(resultSet.getString(5));
                product.setLink(resultSet.getString(6));
                return product;
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Producto");
            ResultSet rs = ps.executeQuery();
            ArrayList<Product> lista = new ArrayList<>();
            while (rs.next()) {
                Product product= new Product();
                product.setId_prod(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setDescription(rs.getString(4));
                product.setMain_photo(rs.getString(5));
                product.setLink(rs.getString(6));
                lista.add(product);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
	}

	@Override
	public void deleteProduct(int id_prod) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("delete from Producto where id_prod=?");
            ps.setInt(1, id_prod);
            ps.execute();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public int insertProduct(Product p) {
		try(Connection connection=Storage.getConnection()){
			int res = 0;
            PreparedStatement ps= connection.prepareStatement("insert into Producto (nombre, precio, descripcion, foto_portada, enlace) values (?,?,?,?,?)");
            ps.setString(1,p.getName());
            ps.setDouble(2,p.getPrice());
            ps.setString(3,p.getDescription());
            ps.setString(4,p.getMain_photo());
            ps.setString(5,p.getLink());
            ps.execute();
            PreparedStatement ps2= connection.prepareStatement("SELECT * from Producto");
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
	public void updateProduct(Product p, int id_prod) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Producto set nombre = ?, precio = ?," +
                    "descripcion = ?, foto_portada = ?, enlace = ?" +
                    "where id_prod = ?", Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1,p.getName());
            ps.setDouble(2,p.getPrice());
            ps.setString(3,p.getDescription());
            ps.setString(4,p.getMain_photo());
            ps.setString(5,p.getLink());
            ps.setInt(6, id_prod);
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
		
	}

	

}
