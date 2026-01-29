package com.ui.pages;

import static com.ui.constants.Env.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.constants.Browser;
import com.ui.utility.BrowserUtility;
import com.ui.utility.JSONUtility;
import com.ui.utility.LoggerUtility;

//import static com.ui.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility {
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		//goToWebsite(readProperty(QA, "URL"));
		logger.info("");
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}

	public HomePage(WebDriver lambdaDriver) {
		super(lambdaDriver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage gotoLoginPage() {
		logger.info("Trying to perform click to go to sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		return new LoginPage(getDriver());
	}
}
