package model.product;

import model.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ProductDAO implements ProductDAOMethod{

	@Override
	public Product searchProduct(int id_prod) {
		Connection connection = null;
		PreparedStatement ps = null;
		Product product = null;
		try{
			connection = Storage.getConnection();
            ps=connection.prepareStatement("select * from Producto where id_prod=?");
            ps.setInt(1,id_prod);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                product= new Product();
                product.setId_prod(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                product.setDescription(resultSet.getString(4));
                product.setMain_photo(resultSet.getString(5));
                product.setLink(resultSet.getString(6));
                product.setActive(resultSet.getBoolean(7));
                
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
        return product;
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Product> lista = new ArrayList<>();
		try  {
			connection = Storage.getConnection();
            
            ps = connection.prepareStatement("select * from Producto WHERE active=TRUE");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product= new Product();
                product.setId_prod(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setDescription(rs.getString(4));
                product.setMain_photo(rs.getString(5));
                product.setLink(rs.getString(6));
                product.setActive(rs.getBoolean(7));
                lista.add(product);
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
	public void deleteProduct(int id_prod) {
		Connection connection = null;
		PreparedStatement ps = null;
		try  {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("update Producto set active=? where id_prod=?");
            ps.setBoolean(1, false);
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
	public int insertProduct(Product p) {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int res = 0;
		try{
			connection=Storage.getConnection();
            ps= connection.prepareStatement("insert into Producto (nombre, precio, descripcion, foto_portada, enlace) values (?,?,?,?,?)");
            ps.setString(1,p.getName());
            ps.setDouble(2,p.getPrice());
            ps.setString(3,p.getDescription());
            ps.setString(4,p.getMain_photo());
            ps.setString(5,p.getLink());
            ps.execute();
            ps2 = connection.prepareStatement("SELECT * from Producto");
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
	public void updateProduct(Product p, int id_prod) {
		Connection connection = null;
		PreparedStatement ps = null;
		try  {
			connection = Storage.getConnection();
            
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
	public ArrayList<Product> getProductsByText(String text) {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Product> lista = new ArrayList<>();
		try  {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("select * from Producto WHERE nombre LIKE ?");
            text="%"+text+"%";
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product= new Product();
                product.setId_prod(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setDescription(rs.getString(4));
                product.setMain_photo(rs.getString(5));
                product.setLink(rs.getString(6));
                product.setActive(rs.getBoolean(7));
                lista.add(product);
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

	

}
