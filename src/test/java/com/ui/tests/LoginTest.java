package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;

@Listeners({ com.ui.listeners.MyTestListener.class })
public class LoginTest extends BaseTest {
	
	@Test(description = "Verify if the valid user is able to login into the application", groups = {
			"smoke" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {
		assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Madhav Pathak");
	}

	/*
	 * @Test(description =
	 * "Verify if the valid user is able to login into the application", groups = {
	 * "smoke" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
	 * dataProvider = "LoginTestCSVDataProvider") public void loginCSVTest(User
	 * user) {
	 * 
	 * assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUserName(), "Madhav Pathak"); }
	 * 
	 * @Test(description =
	 * "Verify if the valid user is able to login into the application", groups = {
	 * "smoke" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
	 * dataProvider = "LoginTestExcelDataProvider", retryAnalyzer =
	 * com.ui.listeners.MyRetryAnalyzer.class) public void loginExcelTest(User user)
	 * {
	 * 
	 * assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUserName(), "Madhav Pathak"); }
	 */

}
