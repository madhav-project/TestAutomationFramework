package com.ui.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ui.constants.Browser;

public abstract class BrowserUtility {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public void quit() {
		if(driver.get()!=null) {
			driver.get().quit();
		}
	}
	/**
	 * @param driver - used to initialize the instance variable WebDriver
	 */
	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // initialize the instance variable driver
	}

	public BrowserUtility(String browserName, boolean isHeadless) {
		logger.info("launching browser " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}
		} else {
			logger.error("Invalid Browser Name - Please select chrome, edge or firefox");
			System.err.println("Invalid Browser Name - Please select chrome, edge or firefox");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("launching browser " + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		} else {
			System.err.println("Invalid Browser Name - Please select chrome, edge or firefox");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("launching browser " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}

		} else {
			System.err.println("Invalid Browser Name - Please select chrome, edge or firefox");
		}
	}

	/**
	 * @param url - value will be provided by the external sources This method will
	 *            open the provided URL in the browser
	 */
	public void goToWebsite(String url) {
		logger.info("Visiting the website" + url);
		driver.get().get(url);
	}

	/**
	 * maximizing the current window which is being loaded on the screen
	 */
	public void maximizeWindow() {
		logger.info("maximizing the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("element found and now performing the click ");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("element found and now entering the text " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("finding the element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("element found and now returning the visible text " + element.getText());
		return element.getText();
	}

	public String takeScreenshot(String fileName) {
		TakesScreenshot screenShot = (TakesScreenshot) driver.get();
		File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = dateFormat.format(date);
		String path = "./screenshots/" + fileName + " - " + timeStamp + ".png";
		File screenShotFile = new File(path);
		try {
			FileUtils.copyFile(screenShotData, screenShotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
