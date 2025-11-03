package com.parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	private static final String PROPERTIES_FILE = "src/test/resources/PropertyData/Object.properties"; 
	
public static String readProperty(String Key) throws IOException {
		
		// Method to load properties can be implemented here
		File file = new File(PROPERTIES_FILE);
		FileInputStream fis = new FileInputStream(file);
		Properties property = new Properties();
		property.load(fis);
		
		String value = property.getProperty(Key);
		
		return value;
		
	}

}
