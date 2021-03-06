package ch.ffhs.jsf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import org.primefaces.event.RowEditEvent;
import ch.ffhs.jpa.domain.Link;
import ch.ffhs.jpa.service.intf.LinkListService;

@ManagedBean
@ViewScoped
public class LinkController {
    
	@EJB
	LinkListService linkListService;
	
	@Resource(mappedName = "java:jboss/jms/queue/linkQueue")
	private Queue queueLink;
	
	@Inject
	@JMSConnectionFactory("java:/linkConnectionFactory")
	JMSContext context;

	private Link link = new Link();
	private List<Link> linkList;

	public List<Link> getLinks() {
		if (linkList == null) {
			linkList = linkListService.getLinks("state = true");
		}
		
		return linkList;
	}

	public List<Link> getLinksAdmin() {
		if (linkList == null) {
			linkList = linkListService.getLinks();
		}
		
		return linkList;
	}

	public Link getLink() {
		return link;
	}

	public void saveLink() {
		List<Link> links = linkListService.getLinks("url = '" + link.getUrl().trim() + "'");
		
		if (links.size() == 0) {
			linkListService.save(link);
			ObjectMessage message = context.createObjectMessage(new FacesMessage("Neuer Link eingefügt (nicht freigeschaltet)", link.getUrl()));
			link = new Link();
			context.createProducer().send(queueLink, message);
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "URL schon vorhanden", link.getUrl());
			FacesContext.getCurrentInstance().addMessage("messages", msg);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Link link = (Link) event.getObject();
		
		ObjectMessage message;
		if (link.getState()) {
			message = context.createObjectMessage(new FacesMessage("Link freigeschaltet", link.getUrl()));
		} else {
			message = context.createObjectMessage(new FacesMessage("Link deaktiviert", link.getUrl()));
		}
		context.createProducer().send(queueLink, message);
		
		linkListService.save(link);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich gespeichert", "");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Änderung abgebrochen", "");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
	}

}