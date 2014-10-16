package ch.ffhs.jpa.service.intf;

import java.util.List;

import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.domain.User;

public interface RoleService {
	public Role save(Role role);
	public void delete(Role role);
	public void delete(int id);
	  public Role find(int id);
	  public List<Role> getAll();
	  public Role findByName(String name);
	  public List<Role> rolesByUser(User user);
}
