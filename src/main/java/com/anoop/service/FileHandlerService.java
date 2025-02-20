package com.anoop.service;

public interface FileHandlerService {

	/**
	 * Read words from the given file, performs string manipulations such as 
	 * checking if words start with 'm' or counting words with a length greater than 5, 
	 * and writes the processed results to another file. 
	 */
	void processWordFile();
}
