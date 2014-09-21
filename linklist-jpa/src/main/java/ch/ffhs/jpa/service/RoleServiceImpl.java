package ch.ffhs.jpa.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ch.ffhs.jpa.dao.intf.RoleDao;
import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.domain.User;
import ch.ffhs.jpa.service.intf.RoleService;

@Stateless
@Local(RoleService.class)
public class RoleServiceImpl implements RoleService {
	@Inject
	private RoleDao roleDao;
	
	public void setUserDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public Role find(int id) {
		return roleDao.find(id);
	}

	@Override
	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Override
	public Role findByName(String name) {		
		return roleDao.findByName(name);		
	}

	@Override
	public List<Role> rolesByUser(User user) {
		return roleDao.rolesByUser(user);
	}

}
