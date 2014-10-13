package ch.ffhs.jpa.service;

import java.util.List;

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

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
}
