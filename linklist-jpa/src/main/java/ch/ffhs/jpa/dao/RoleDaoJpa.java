package ch.ffhs.jpa.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.ffhs.jpa.dao.intf.RoleDao;
import ch.ffhs.jpa.domain.Role;

@Named("roleDao")
public class RoleDaoJpa implements RoleDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public Role save(Role role) {
		em.persist(role);
		return role;
	}

}
