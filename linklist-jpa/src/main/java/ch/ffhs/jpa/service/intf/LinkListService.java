package ch.ffhs.jpa.service.intf;

import java.util.List;

import ch.ffhs.jpa.domain.Link;

public interface LinkListService {
	public Link save(Link linkList);
	public List<Link> getLinks();
	public Link getById(int id);
}
