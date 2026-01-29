package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.ui.constants.Env;
import com.ui.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_NUMBER_OF_ATTEMPTS = Integer
			.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUMBER_OF_ATTEMPTS"));

	/*
	 * private static final int MAX_NUMBER_OF_ATTEMPTS =
	 * JSONUtility.readJSON(Env.QA).getMax_number_of_attempts();
	 */

	private static int currentAttempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}

}
