package ch.ffhs.jpa.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.ffhs.jpa.dao.intf.UserDao;
import ch.ffhs.jpa.domain.User;

@Named("userDao")
public class UserDaoJpa implements UserDao {
	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public User save(User user) {
		em.persist(user);
		
		return user;
	}
}
