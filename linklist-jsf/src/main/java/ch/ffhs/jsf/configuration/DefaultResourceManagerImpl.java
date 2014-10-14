package ch.ffhs.jsf.configuration;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@DefaultResourceManager
public class DefaultResourceManagerImpl implements ResourceManager {

	Logger logger = LoggerFactory.getLogger(DefaultResourceManagerImpl.class);
	
	@Override
	public String getValue(String properties, String key) {
		return getValue(properties, key, new Locale("de", "CH"),null);
	}

	@Override
	public String getValue(String properties, String key, Locale locale) {
		return getValue(properties, key, locale, null);
	}

	@Override
	public String getValue(String properties, String key, String defaultValue) {
		return getValue(properties, key, new Locale("de", "CH"), defaultValue);
	}

	@Override
	public String getValue(String properties, String key, Locale locale,
			String defaultValue) {
		try {
			logger.debug("Get bundle for property %1$s with locale %2$s and key %3$s", properties, key, locale.toLanguageTag());
			ResourceBundle bundle;
			try {
				bundle = ResourceBundle.getBundle(properties,locale);
			} catch (Exception e) {}
			bundle = ResourceBundle.getBundle(properties);
			if(bundle.containsKey(key)){
				return bundle.getString(key);
			}
			
		} catch (Exception e) {			
			logger.error("Error while getting resource bundle. " + e.getMessage() , e);
		}
		return defaultValue;
	}

}
