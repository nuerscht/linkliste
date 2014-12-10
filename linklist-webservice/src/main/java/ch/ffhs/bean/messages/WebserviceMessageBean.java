package ch.ffhs.bean.messages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;

import ch.ffhs.jpa.domain.Link;
import ch.ffhs.jpa.service.intf.LinkListService;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/webserviceQueue") })
public class WebserviceMessageBean implements MessageListener {

	@EJB
	LinkListService linkListService;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage tm = (TextMessage) message;

			if (!tm.getText().equals(null)) {
				DynamicClientFactory dcf = DynamicClientFactory.newInstance();
				Client client = dcf.createClient(tm.getText());

				Object[] links = client.invoke("synchronize", getListArray());
				
				synchronize(links);

				client.destroy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void synchronize(Object[] links) {
		try {
			Object linkArray = links[0];
			Field field = linkArray.getClass().getDeclaredField("item");

			field.setAccessible(true);
			Object object = field.get(linkArray);
			field.setAccessible(false);
			if (object != null) {
				ArrayList arrayList = (ArrayList)object;
				
				Iterator it = arrayList.iterator();
				while (it.hasNext()) {
					Object obj = it.next();
					
					List<Link> linksCheck = linkListService.getLinks("url = '" + getFieldValue(obj, "url").toString() + "'");
					if (linksCheck.size() == 0) {
						Link link = new Link();
						link.setName(getFieldValue(obj, "name").toString());
						link.setUrl(getFieldValue(obj, "url").toString());
						link.setState(Boolean.parseBoolean(getFieldValue(obj, "state").toString()));
						link.setDeleted(Boolean.parseBoolean(getFieldValue(obj, "deleted").toString()));
	
						linkListService.save(link);
					}
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getListArray() {
		List<Link> links = linkListService.getLinks();

		Object linkArray = null;
		try {

			linkArray = Thread.currentThread().getContextClassLoader()
					.loadClass("ch.ffhs.webservice.LinkArray").newInstance();

			Field field = linkArray.getClass().getDeclaredField("item");
			ArrayList arrayList = new ArrayList();

			Iterator<Link> it = links.iterator();
			while (it.hasNext()) {
				Link l = it.next();
				Object link = Thread.currentThread().getContextClassLoader()
						.loadClass("ch.ffhs.webservice.Link").newInstance();

				parseField(link, "id", l.getId());
				parseField(link, "name", l.getName());
				parseField(link, "url", l.getUrl());
				parseField(link, "state", l.getState());
				parseField(link, "deleted", l.isDeleted());

				arrayList.add(link);
			}

			field.setAccessible(true);
			field.set(linkArray, arrayList);
			field.setAccessible(false);

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SecurityException
				| IllegalArgumentException | NoSuchFieldException e) {
			e.printStackTrace();
		}

		return linkArray;
	}
	
	private Object getFieldValue(Object link, String attribute) {
		Object value = null;
		Field field;
		try {
			field = link.getClass().getDeclaredField(attribute);
			field.setAccessible(true);
			value = field.get(link).toString();
			field.setAccessible(false);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	private void parseField(Object link, String fieldName, Object value) {
		Field field;
		try {
			field = link.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(link, value);
			field.setAccessible(false);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
