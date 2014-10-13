package ch.ffhs.jpa.dao.intf;

import java.util.List;

import ch.ffhs.jpa.domain.User;

public interface UserDao {
	  public User save(User user);
	  public List<User> getAll();
	  public User findByUsername(String username);
	  public User findByUsernameAndPassword(String username, String password);
}
