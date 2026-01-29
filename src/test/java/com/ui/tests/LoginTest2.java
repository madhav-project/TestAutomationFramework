package com.ui.tests;

import com.ui.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;

public class LoginTest2 {

	public static void main(String[] args) {
		HomePage homePage = new HomePage(Browser.EDGE, false);
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.doLoginWith("kifodan154@lawicon.com", "Pass@1234");

	}

}
