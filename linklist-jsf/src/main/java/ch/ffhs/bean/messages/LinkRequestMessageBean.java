package ch.ffhs.bean.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

import ch.ffhs.jsf.controller.LinkController;

@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/linkQueue")})
public class LinkRequestMessageBean implements MessageListener {

	@Inject
	LinkController linkController;
	
	@Override
	public void onMessage(Message message) {	
		linkController.sendPushMessage(message);
	}
}
