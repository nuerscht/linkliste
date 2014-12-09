package ch.ffhs.jsf.controller;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
	
	public void synchronize() {
		TextMessage message = context.createTextMessage("http://localhost:8080/linklist-webservice/LinkList");
		context.createProducer().send(queueWebservice, message);
	}
}