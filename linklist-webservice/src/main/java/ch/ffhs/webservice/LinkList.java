package ch.ffhs.webservice;

import java.util.ArrayList;
import java.util.Iterator;
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
		//Prüfen ob Links schon vorhanden. Wenn nicht, dann anlegen.
		ArrayList<String> ids = new ArrayList<String>();
		Iterator<Link> it = linkList.iterator();
		while (it.hasNext()) {
			Link link = it.next();
			
			List<Link> links = linkListService.getLinks("url = '" + link.getUrl() + "'");
			
			if (links.size() == 0) {
				
				Link linkNew = new Link();

				linkNew.setName(link.getName());
				linkNew.setUrl(link.getUrl());
				linkNew.setDeleted(link.isDeleted());
				linkNew.setState(link.getState());
				
				linkListService.save(linkNew);

				ids.add(String.valueOf(linkNew.getId()));
			} else {
				Iterator<Link> itLinks = links.iterator();
				while (itLinks.hasNext()) {
					Link linkAct = itLinks.next();
					ids.add(String.valueOf(linkAct.getId()));
				}
			}
			
		}
		
		
		String where = null;
		
		if (ids.size() > 0) {
			String where_id = "";
			
			Iterator<String> itId = ids.iterator();
			while (itId.hasNext()) {
				if (!"".equals(where_id)) 
					where_id += ",";
				where_id += itId.next();
			}
			
			where = "id NOT IN (" + where_id + ")";
		}
		
		return linkListService.getLinks(where);
	}
}