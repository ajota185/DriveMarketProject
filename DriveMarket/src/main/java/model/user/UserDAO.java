package model.user;

import model.product.Product;
import model.storage.Storage;


import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserDAO implements UserDAOMethod{

	@Override
	public User searchUser(String nickName) {
		Connection connection = null;
		PreparedStatement ps = null;
		User user = null;
		try{
			connection= Storage.getConnection();

            ps=connection.prepareStatement("select * from Usuario where nick=?");
            ps.setString(1, nickName);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                user= new User();
                user.setNickName(rs.getString(1));
                user.setPassw(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAdmin(rs.getBoolean(4));
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
		
        return user;
	}

	@Override
	public void deleteUser(String nickName) {
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection=Storage.getConnection();
            ps = connection.prepareStatement("delete from Usuario where nick=?");
            ps.setString(1, nickName);
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
	public void insertUser(User u) throws SQLException {
			Connection connection=Storage.getConnection();

            PreparedStatement ps= connection.prepareStatement("insert into Usuario (nick, passw, email, admin) values (?,?,?,?)");
            ps.setString(1, u.getNickName());
            ps.setString(2, u.getPassw());
            ps.setString(3, u.getEmail());
            ps.setBoolean(4, u.isAdmin());
            ps.execute();
            
            try {
				if (ps != null)
					ps.close();
			} finally {
				Storage.releaseConnection(connection);
				
			}
	}

	@Override
	public void updateUser(User u, String nickName) throws SQLException{
			Connection connection = Storage.getConnection();
            PreparedStatement ps;
            ps = connection.prepareStatement("update Usuario set nick = ?, passw = ?, email = ?, admin = ? " +
                    "where nick = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getNickName());
            ps.setString(2, u.getPassw());
            ps.setString(3, u.getEmail());
            ps.setBoolean(4, u.isAdmin());
            ps.setString(5, nickName);
            ps.executeUpdate();
            
            try {
				if (ps != null)
					ps.close();
			} finally {
				Storage.releaseConnection(connection);
				
			}
		
	}

	@Override
	public void changeUser(String nick, boolean type){
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = Storage.getConnection();
	        ps = connection.prepareStatement("update Usuario set admin = ? " +
	                "where nick = ?", Statement.RETURN_GENERATED_KEYS);
	        
	        ps.setBoolean(1, type);
	        ps.setString(2, nick);
	        ps.executeUpdate();
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
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
	public ArrayList<User> getAllUsers() {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<User> lista = new ArrayList<>();
		try {
			connection = Storage.getConnection();
            ps = connection.prepareStatement("select * from Usuario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user= new User();
                user.setNickName(rs.getString(1));
                user.setPassw(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAdmin(rs.getBoolean(4));
                lista.add(user);
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
