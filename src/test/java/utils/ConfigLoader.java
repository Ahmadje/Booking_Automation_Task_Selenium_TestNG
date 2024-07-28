

package utils;

import java.util.Properties;

import static utils.FrameworkConstants.RESOURCES_MAIN_PATH;

/*Singleton Design pattern*/
public class ConfigLoader {


	private static final String URL = "url";
	private static final String BROWSER = "browser";

	private static final String CONFIG_PROPERTIES = "config.properties";

	private Properties properties;

	private static ConfigLoader configLoader;

	private ConfigLoader() {
		properties = getConfigPropertyFile(CONFIG_PROPERTIES);
	}

	private Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(RESOURCES_MAIN_PATH + configFile);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getURL() {
		return getPropertyValue(URL);
	}
	public String getBrowser() {
		return getPropertyValue(BROWSER).toUpperCase();
	}

}
