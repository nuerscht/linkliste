package ch.ffhs.jsf.configuration;

import java.util.Locale;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;

@DefaultResourceManager
public class DefaultResourceManagerImpl implements ResourceManager {

	Logger logger = Logger.getLogger(DefaultResourceManagerImpl.class);
	
	@Override
	public String getValue(String properties, String key) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(properties);
			if(bundle.containsKey(key)){
				return bundle.getString(key);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public String getValue(String properties, String key, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue(String properties, String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue(String properties, String key, Locale locale,
			String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
