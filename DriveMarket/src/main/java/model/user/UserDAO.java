package model.user;

import model.storage.Storage;


import java.sql.*;


import java.util.Optional;

public class UserDAO implements UserDAOMethod{

	@Override
	public User searchUser(String nickName) {
		try(Connection connection= Storage.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Usuario where nick=?");
            ps.setString(1, nickName);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                User user= new User();
                user.setNickName(rs.getString(1));
                user.setPassw(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAdmin(rs.getBoolean(4));
                return user;
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
	}

	@Override
	public void deleteUser(String nickName) {
		try(Connection connection=Storage.getConnection()){
            PreparedStatement ps = connection.prepareStatement("delete from Usuario where nick=?");
            ps.setString(1, nickName);
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
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
		
	}

	@Override
	public void changeUser(String nick, boolean type){
		try(Connection connection = Storage.getConnection()){
	        PreparedStatement ps;
	        ps = connection.prepareStatement("update Usuario set admin = ? " +
	                "where nick = ?", Statement.RETURN_GENERATED_KEYS);
	        
	        ps.setBoolean(1, type);
	        ps.setString(2, nick);
	        ps.executeUpdate();
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			throw new RuntimeException(sqlException);
		}
		
	}

}
