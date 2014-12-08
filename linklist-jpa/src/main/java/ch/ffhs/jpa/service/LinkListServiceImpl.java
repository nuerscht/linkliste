package ch.ffhs.jpa.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.inject.Inject;

import ch.ffhs.jpa.dao.intf.LinkListDao;
import ch.ffhs.jpa.domain.Link;
import ch.ffhs.jpa.service.intf.LinkListService;

@Stateful
@Local(LinkListService.class)
public class LinkListServiceImpl implements LinkListService {
	
	@Inject
	private LinkListDao linkListDao;
	
	public void setLinkListDao(LinkListDao linkListDao) {
		this.linkListDao = linkListDao;
	}

	@Override
	public Link save(Link linkList) {
		return linkListDao.save(linkList);
	}
	
	public List<Link> getLinks() {
		return linkListDao.getLinks();
	}

	@Override
	public Link getById(int id) {
		return linkListDao.getById(id);
	}
}
