package ch.ffhs.jpa.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ch.ffhs.jpa.dao.intf.RoleDao;
import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.domain.User;

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

	@Override
	public Role find(int id) {
		return em.find(Role.class, id);
	}

	@Override
	public List<Role> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Role> query = cb.createQuery(Role.class);
		CriteriaQuery<Role> qr = query.select(query.from(Role.class));
		return em.createQuery(qr).getResultList();
	}

	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> rolesByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
