package com.ui.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.ui.constants.Env;

public class PropertiesUtil {

	public static String readProperty(Env env, String propertyName) {
		File propFile = new File(System.getProperty("user.dir") + "\\config\\" + env + ".properties");
		FileReader fileReader = null;
		Properties properties = null;

		try {

			fileReader = new FileReader(propFile);
			properties = new Properties();
			properties.load(fileReader);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(propertyName.toUpperCase());
	}
}
