package com.pratian.automation.filehandling;

import java.io.FileNotFoundException;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	public static String getProperty(String propName) throws IOException {
		FileReader fileReader=new FileReader("app.properties");
		Properties properties=new Properties();
		properties.load(fileReader);
		return properties.getProperty(propName);
		
	}

}
