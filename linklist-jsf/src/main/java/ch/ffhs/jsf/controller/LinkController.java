package ch.ffhs.jsf.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import ch.ffhs.jpa.domain.Link;
import ch.ffhs.jpa.service.intf.LinkListService;

@ManagedBean
@ViewScoped
public class LinkController {
    
	@EJB
	LinkListService linkListService;

	private Link link = new Link();
	private List<Link> linkList;

	public List<Link> getLinks() {
		if (linkList == null) {
			linkList = linkListService.getLinks();
		}
		
		return linkList;
	}

	public Link getLink() {
		return link;
	}

	public void saveLink() {
		linkListService.save(link);
		link = new Link();
	}
	
	//Send Push to Client
    //private final static String CHANNEL = "/notify";
    //EventBus eventBus = EventBusFactory.getDefault().eventBus();
    //eventBus.publish(CHANNEL, new FacesMessage("Gespeichert", "Link gespeichert."));

	public void onRowEdit(RowEditEvent event) {
		Link link = (Link) event.getObject();
		
		linkListService.save(link);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich gespeichert", "");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Änderung abgebrochen", "");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
	}

}