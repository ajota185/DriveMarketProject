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

}
