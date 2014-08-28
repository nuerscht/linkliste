package ch.ffhs.jpa.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ch.ffhs.jpa.dao.intf.RoleDao;
import ch.ffhs.jpa.domain.Role;
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

}
