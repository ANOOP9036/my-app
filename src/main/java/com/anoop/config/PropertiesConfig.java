package com.anoop.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesConfig {
	
	private static final Logger logger = Logger.getLogger(PropertiesConfig.class.getName());

	private Properties properties;
	private static final String PROPERTIES_FILE_PATH = "src/main/resources/application.properties";
	
	public Properties loadProperties(){
		
		properties = new Properties();
		String filePath = Paths.get(PROPERTIES_FILE_PATH).toAbsolutePath().toString();
		
		try(FileInputStream inputStream = new FileInputStream(filePath);) {
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			logger.info("Exception when accessing the properties file = "+e.getMessage());
		} catch (IOException e) {
			logger.info("Exception when reading the properties file = "+e.getMessage());
		} 
		return properties;
	}
}
