package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

	private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}


	public String getClientId() {
		String prop= properties.getProperty("client_id");
		if (prop!= null) return prop;
		else throw new RuntimeException("property client_id is not specified in the cofig.properties file");
	}

	public String getClient_secret() {
		String prop= properties.getProperty("client_secret");
		if (prop!= null) return prop;
		else throw new RuntimeException("property Client_secret is not specified in the cofig.properties file");
	}

	public String getGrant_type() {
		String prop= properties.getProperty("grant_type");
		if (prop!= null) return prop;
		else throw new RuntimeException("property grant_type is not specified in the cofig.properties file");
	}

	public String getRefresh_token() {
		String prop= properties.getProperty("refresh_token");
		if (prop!= null) return prop;
		else throw new RuntimeException("property refresh_token is not specified in the cofig.properties file");
	}

	public String getUser_id() {
		String prop= properties.getProperty("user_id");
		if (prop!= null) return prop;
		else throw new RuntimeException("property user_id is not specified in the cofig.properties file");
	}


}
