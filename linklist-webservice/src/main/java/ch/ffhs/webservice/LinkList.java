package ch.ffhs.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import ch.ffhs.jpa.domain.Link;
import ch.ffhs.jpa.service.intf.LinkListService;

@Stateless
@WebService
public class LinkList implements LinkListWS {
    
	@Inject
	LinkListService linkListService;

	@Override
	public List<Link> synchronize(List<Link> linkList) {				
		return linkListService.getLinks();
	}

}
