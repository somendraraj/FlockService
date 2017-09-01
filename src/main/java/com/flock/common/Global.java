package com.flock.common;

import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Global {
	private static final Logger log = LoggerFactory.getLogger(Global.class);

	private static Properties prop_config;


	public static Set<Entry<Object, Object>> serviceUrls;

	public static void loadClass() {
		log.info("**********Loading all Properties ***********");
	}

	public static final class HeaderType {
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
		public static final String AUTHORIZATION = "Authorization";
	}

	static {
		try {
			prop_config = PropertiesLoaderUtils.loadAllProperties("config.properties");

			serviceUrls = prop_config.entrySet();

			log.info("Urls: "+serviceUrls.size());

		} catch (Exception e) {
			log.error("*****Properties exception****", e);
		}

	}

}
