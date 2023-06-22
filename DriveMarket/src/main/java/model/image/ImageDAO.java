package model.image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.product.Product;
import model.storage.Storage;

public class ImageDAO implements ImageDAOMethod{

	@Override
	public ArrayList<Image> getImagesByProduct(int id_prod) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Imagenes WHERE id_prod=?");
            ps.setInt(1, id_prod);
            ResultSet rs = ps.executeQuery();
            ArrayList<Image> lista = new ArrayList<>();
            while (rs.next()) {
                Image image= new Image();
                image.setId_img(rs.getInt(1));
                image.setPath(rs.getString(2));
                image.setId_prod(rs.getInt(3));
                image.setPie(rs.getString(4));
                lista.add(image);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
	}

	@Override
	public void insertImage(Image image) {
		try(Connection connection=Storage.getConnection()){
            PreparedStatement ps= connection.prepareStatement("insert into Imagenes (ruta, id_prod, pie) values (?,?,?)");
            ps.setString(1,image.getPath());
            ps.setDouble(2,image.getId_prod());
            ps.setString(3,image.getPie());
            ps.execute();
		
		}catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public void updateImagesOfProduct(ArrayList<Image> images, int id_prod) {
		try(Connection connection=Storage.getConnection()){
            PreparedStatement ps = connection.prepareStatement("delete from Imagenes where id_prod=?");
            ps.setInt(1, id_prod);
            ps.execute();
            
            for(Image img : images) {
            	PreparedStatement ps2= connection.prepareStatement("insert into Imagenes (ruta, id_prod, pie) values (?,?,?)");
                ps2.setString(1,img.getPath());
                ps2.setDouble(2,img.getId_prod());
                ps2.setString(3,img.getPie());
                ps2.execute();
            }
            
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public void deleteImages(int id_prod) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("delete from Imagenes where id_prod=?");
            ps.setInt(1, id_prod);
            ps.execute();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
		
	}

}
