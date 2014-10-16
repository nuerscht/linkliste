package ch.ffhs.jpa.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
		Role existing = em.find(Role.class, role.getId());
		if(existing != null){
			role = em.merge(role);
		}
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
		TypedQuery<Role> q = em.createQuery("SELECT r FROM role r WHERE r.name LIKE :name",Role.class);
		q.setParameter("name", name);
		List<Role> result = q.getResultList();
		return result.size() != 0 ? result.get(0) : null;
	}

	@Override
	public List<Role> rolesByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Role role) {
		delete(role.getId());
	}

	@Override
	public void delete(int id) {
		Role r = em.find(Role.class, id);		
		em.remove(r);		
	}
	
}