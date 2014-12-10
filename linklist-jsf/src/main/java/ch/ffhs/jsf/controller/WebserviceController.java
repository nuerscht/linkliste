package ch.ffhs.jsf.controller;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@ManagedBean
@ViewScoped
public class WebserviceController {

	@Resource(mappedName = "java:jboss/jms/queue/webserviceQueue")
	private Queue queueWebservice;

	@Inject
	@JMSConnectionFactory("java:/webserviceConnectionFactory")
	JMSContext context;
	
	private String url;

	public void synchronize() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Synchronisation gestartet", "");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
		TextMessage message = context
				.createTextMessage(url);
		context.createProducer().send(queueWebservice, message);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}