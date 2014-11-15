package ch.ffhs.jsf.configuration;

import java.util.Locale;

public interface ResourceManager {
	
	public final String MESSAGES = "messages";
	public String getValue(String properties, String key);
	public String getValue(String properties, String key, Locale locale);
	public String getValue(String properties, String key, String defaultValue);
	public String getValue(String properties, String key, Locale locale, String defaultValue);
}
