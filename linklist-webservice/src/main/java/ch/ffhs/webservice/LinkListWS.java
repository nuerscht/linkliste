package ch.ffhs.webservice;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import ch.ffhs.jpa.domain.Link;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC, use=SOAPBinding.Use.ENCODED)
public interface LinkListWS {
	public List<Link> synchronize(List<Link> linkList);
}
