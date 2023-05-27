package model.user;

import model.storage.Storage;


import java.sql.*;


import java.util.Optional;

public class UserDAO implements UserDAOMethod{

	@Override
	public Optional<User> cercaUtente(String nickName) {
		try(Connection connection= Storage.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Utente where codiceFiscale=?");
            ps.setString(1, nickName);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                User user= new User();
                user.setNickName(rs.getString(1));
                user.setPassw(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAdmin(rs.getBoolean(4));
                return Optional.of(user);
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return Optional.empty();
	}

	@Override
	public void deleteUtente(String nickName) {
		try(Connection connection=Storage.getConnection()){
            PreparedStatement ps = connection.prepareStatement("delete from Usuario where nick=?");
            ps.setString(1, nickName);
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public void insertUtente(User u) {
		try(Connection connection=Storage.getConnection()){

            PreparedStatement ps= connection.prepareStatement("insert into Usuarios value (?,?,?,?)");
            ps.setString(1, u.getNickName());
            ps.setString(2, u.getPassw());
            ps.setString(3, u.getEmail());
            ps.setBoolean(4, u.isAdmin());
            ps.execute();

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
		
	}

	@Override
	public void updateUtente(User u, String nickName) {
		try (Connection connection = Storage.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Usuario set nickName = ?, passw = ?, email = ?, isAdmin = ? " +
                    "where nickName = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getNickName());
            ps.setString(2, u.getPassw());
            ps.setString(3, u.getEmail());
            ps.setBoolean(9, u.isAdmin());
            ps.setString(10, nickName);
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
		
	}

}
