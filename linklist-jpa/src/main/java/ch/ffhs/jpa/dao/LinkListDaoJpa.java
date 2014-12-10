package ch.ffhs.jpa.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.ffhs.jpa.dao.intf.LinkListDao;
import ch.ffhs.jpa.domain.Link;

@Named("linkListDao")
public class LinkListDaoJpa implements LinkListDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public Link save(Link linkList) {
		em.merge(linkList);
		return linkList;
	}

	@Override	
	public List<Link> getLinks() {
		return getLinks(null);
	}
	
	@Override	
	public List<Link> getLinks(String where) {
		String jpql = "SELECT l FROM ch.ffhs.jpa.domain.Link AS l WHERE l.deleted = false";
		
		if (where != null) {
			jpql += " AND (" + where + ")";
		}
		
		jpql += " ORDER BY l.name";
		
		TypedQuery<Link> query = em.createQuery(jpql, Link.class);
		return query.getResultList();
	}

	@Override
	public Link getById(int id) {
		return em.find(Link.class, id);
	}
}
