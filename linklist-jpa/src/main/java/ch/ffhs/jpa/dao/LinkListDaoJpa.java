package ch.ffhs.jpa.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.ffhs.jpa.dao.intf.LinkListDao;
import ch.ffhs.jpa.domain.LinkList;

@Named("linkListDao")
public class LinkListDaoJpa implements LinkListDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public LinkList save(LinkList linkList) {
		em.persist(linkList);
		return linkList;
	}
}
