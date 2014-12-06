package ch.ffhs.jpa.dao.intf;

import java.util.List;

import ch.ffhs.jpa.domain.Link;

public interface LinkListDao {
	  public Link save(Link linkList);
	  public List<Link> getLinks();
	  public Link getById(int id);
}
