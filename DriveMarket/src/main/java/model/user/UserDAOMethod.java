package model.user;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAOMethod {
	public User searchUser (String nickName);
	public void deleteUser (String nickName);
    public void insertUser (User u) throws SQLException;
    public ArrayList<User> getAllUsers();
    public void updateUser (User u, String nickName) throws SQLException;
    public void changeUser (String nick, boolean type);
}
