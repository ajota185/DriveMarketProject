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
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Image> lista = new ArrayList<>();
		try  {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("select * from Imagenes WHERE id_prod=?");
            ps.setInt(1, id_prod);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image image= new Image();
                image.setId_img(rs.getInt(1));
                image.setPath(rs.getString(2));
                image.setId_prod(rs.getInt(3));
                lista.add(image);
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
		return lista;
	}

	@Override
	public void insertImage(Image image) {
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection=Storage.getConnection();
            ps= connection.prepareStatement("insert into Imagenes (ruta, id_prod) values (?,?)");
            ps.setString(1,image.getPath());
            ps.setDouble(2,image.getId_prod());
            ps.execute();
		
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
		
	}

	@Override
	public void updateImagesOfProduct(ArrayList<Image> images, int id_prod) {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try{
			connection=Storage.getConnection();
            ps = connection.prepareStatement("delete from Imagenes where id_prod=?");
            ps.setInt(1, id_prod);
            ps.execute();
            
            for(Image img : images) {
            	ps2= connection.prepareStatement("insert into Imagenes (ruta, id_prod) values (?,?)");
                ps2.setString(1,img.getPath());
                ps2.setDouble(2,img.getId_prod());
                ps2.execute();
            }
            
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
	public void deleteImages(int id_prod) {
		Connection connection = null;
		PreparedStatement ps = null;
		try  {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("delete from Imagenes where id_prod=?");
            ps.setInt(1, id_prod);
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

}
