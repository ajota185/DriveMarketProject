package model.product;

import model.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ProductDAO implements ProductDAOMethod{

	@Override
	public Optional<Product> cercaProdotto(int id_prod) {
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
                return Optional.of(product);
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return Optional.empty();
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Prodotto");
            ResultSet rs = ps.executeQuery();
            ArrayList<Product> lista = new ArrayList<>();
            while (rs.next()) {
                Product prodotto=new Product();
                Product product= new Product();
                product.setId_prod(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setDescription(rs.getString(4));
                product.setMain_photo(rs.getString(5));
                product.setLink(rs.getString(6));
                lista.add(prodotto);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
	}

	@Override
	public void deleteProdotto(int id_prod) {
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
	public void insertProdotto(Product p) {
		try(Connection connection=Storage.getConnection()){
            PreparedStatement ps= connection.prepareStatement("insert into Prodotto value (?,?,?,?,?,?,?,?)");
            ps.setInt(1,p.getId_prod());
            ps.setString(2,p.getName());
            ps.setDouble(3,p.getPrice());
            ps.setString(4,p.getDescription());
            ps.setString(5,p.getMain_photo());
            ps.setString(6,p.getLink());
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public void updateProdotto(Product p, int id_prod) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Producto set id_prod = ?, nombre = ?, precio = ?," +
                    "descripcion = ?, foto_portada = ?, enlace = ?" +
                    "where id_prod = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,p.getId_prod());
            ps.setString(2,p.getName());
            ps.setDouble(3,p.getPrice());
            ps.setString(4,p.getDescription());
            ps.setString(5,p.getMain_photo());
            ps.setString(6,p.getLink());
            ps.setInt(7, id_prod);
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
		
	}

}
