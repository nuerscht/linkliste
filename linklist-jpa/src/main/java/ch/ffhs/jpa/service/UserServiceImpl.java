package ch.ffhs.jpa.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ch.ffhs.jpa.dao.intf.UserDao;
import ch.ffhs.jpa.domain.User;
import ch.ffhs.jpa.service.intf.UserService;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {
	@Inject
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}
}
