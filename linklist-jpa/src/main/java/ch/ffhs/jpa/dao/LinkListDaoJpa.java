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
		/*CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Link> query = cb.createQuery(Link.class);
		CriteriaQuery<Link> qr = query.select(query.from(Link.class));
		qr.where(cb.equal(query.from(Link.class).get("deleted"), false));
		qr.orderBy(cb.asc(query.from(Link.class).get("name")));
		return em.createQuery(qr).getResultList();*/
		
		TypedQuery<Link> query = em.createQuery("SELECT l FROM ch.ffhs.jpa.domain.Link AS l WHERE l.deleted = false ORDER BY l.name", Link.class);
		return query.getResultList();
	}

	@Override
	public Link getById(int id) {
		return em.find(Link.class, id);
	}
}
