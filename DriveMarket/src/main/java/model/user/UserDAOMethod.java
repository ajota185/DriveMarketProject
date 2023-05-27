package model.user;

import java.util.Optional;

public interface UserDAOMethod {
	public Optional<User> cercaUtente (String nickName);
	public void deleteUtente (String nickName);
    public void insertUtente (User u);
    public void updateUtente (User u, String codiceFiscale);
}
