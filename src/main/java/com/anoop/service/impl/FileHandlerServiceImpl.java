package com.anoop.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

import com.anoop.config.PropertiesConfig;
import com.anoop.service.FileHandlerService;

public class FileHandlerServiceImpl implements FileHandlerService {
	
	private static final Logger logger = Logger.getLogger(FileHandlerServiceImpl.class.getName());
	
	private static final String SEPARATOR = "----------------------------------------";
	private static final String PREFIX = "m";
	private static final int LENGTH_LIMIT = 5;
	
	private static PropertiesConfig config = new PropertiesConfig();
	private static Properties properties = config.loadProperties();;
	
	@Override
	public void processWordFile() {
		logger.info("Processing the word file");

		String wordFilePath = Paths.get(properties.getProperty("input.file.path")).toAbsolutePath().toString();
		String outputFilePath = Paths.get(properties.getProperty("output.file.path")).toAbsolutePath().toString();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(wordFilePath));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, false));) {
			
			processWordsFromFile(reader, writer);
		} catch (FileNotFoundException e) {
			logger.info("Exception when accessing the file = "+e.getMessage());
		} catch (IOException e) {
			logger.info("Exception when reading the file = "+e.getMessage());
		}
		logger.info("Successfully processed the word file");
	}

	public void processWordsFromFile(BufferedReader reader, BufferedWriter writer) throws IOException {
		logger.info("Reading the word file");
		int count = 0;
		String word = reader.readLine();
		writer.write("Words longer than 5 characters : ");
		writer.newLine();
		while(word != null) {
			if(word.toLowerCase().startsWith(PREFIX))
				count++;
			if(word.length() > LENGTH_LIMIT) {
				writer.write(word);
				writer.newLine();
			}
			word = reader.readLine();
		}
		writer.write(SEPARATOR);
		writer.newLine();
		writer.write("The NUMBER of words that start with \"M\" or \"m\": "+count);
		writer.newLine();
		logger.info("Successfully written contents to the output file");
	}
	
}
