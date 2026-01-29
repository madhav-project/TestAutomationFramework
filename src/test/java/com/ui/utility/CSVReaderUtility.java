package com.ui.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {

	public static Iterator<User> readCSVFile(String fileName) {

		File csvFile = new File(System.getProperty("user.dir") + "//testData//"+fileName);
		FileReader csvFileReader = null;
		CSVReader csvReader = null;
		String[] line = null;
		List<User> userList = new ArrayList<User>();
		User userData = null;
		try {
			csvFileReader = new FileReader(csvFile);
			csvReader = new CSVReader(csvFileReader);
			csvReader.readNext();// retrieve the column names or 1st row, should be skipped. because, you are
									// reading the immediate line
			userList = new ArrayList<User>();
			while ((line = csvReader.readNext()) != null) {
				userData = new User(line[0], line[1]);
				userList.add(userData);
			}
			
			for(User eachUser: userList) {
				System.out.println(eachUser);
			}

		} catch (CsvValidationException | IOException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();
	}
}
