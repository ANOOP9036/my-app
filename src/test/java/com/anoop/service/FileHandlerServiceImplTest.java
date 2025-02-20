package com.anoop.service;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.anoop.config.PropertiesConfig;
import com.anoop.service.impl.FileHandlerServiceImpl;

public class FileHandlerServiceImplTest {

	FileHandlerServiceImpl handler = new FileHandlerServiceImpl();

	@Test
	void processWordsFromFileTest() throws IOException {
		
		PropertiesConfig config = new PropertiesConfig();
		Properties properties = config.loadProperties();
		
		String wordFilePath = Paths.get(properties.getProperty("input.file.path")).toAbsolutePath().toString();
		BufferedReader reader = new BufferedReader(new FileReader(wordFilePath));
		BufferedWriter writer = mock(BufferedWriter.class);
		
		Mockito.doNothing().when(writer).write(Mockito.anyString());
		Mockito.doNothing().when(writer).newLine();
		
		handler.processWordsFromFile(reader, writer);
		
		Mockito.verify(writer, atLeast(1)).write(Mockito.anyString());
	}
}
