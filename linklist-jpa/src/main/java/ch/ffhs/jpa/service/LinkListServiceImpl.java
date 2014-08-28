package ch.ffhs.jpa.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ch.ffhs.jpa.dao.intf.LinkListDao;
import ch.ffhs.jpa.domain.LinkList;
import ch.ffhs.jpa.service.intf.LinkListService;

@Stateless
@Local(LinkListService.class)
public class LinkListServiceImpl implements LinkListService {
	
	@Inject
	private LinkListDao linkListDao;
	
	public void setLinkListDao(LinkListDao linkListDao) {
		this.linkListDao = linkListDao;
	}

	@Override
	public LinkList save(LinkList linkList) {
		return linkListDao.save(linkList);
	}
}
