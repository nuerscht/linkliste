package ch.ffhs.bean.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.application.FacesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/linkQueue")})
public class LinkRequestMessageBean implements MessageListener {
    private final static String CHANNEL = "/notify";	
	@Override
	public void onMessage(Message message) {	
		try {
			ObjectMessage tm = (ObjectMessage) message;
			FacesMessage fm = (FacesMessage)tm.getObject();
			//Send Push to Client
		    EventBus eventBus = EventBusFactory.getDefault().eventBus();
		    eventBus.publish(CHANNEL, fm);
		} catch (JMSException e) {
			System.out.println(e);
		}
	}
}
