package com.pratian.automation.testclasses;

import java.io.File;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.beust.jcommander.Parameters;
import com.pratian.automation.filehandling.PropertyManager;
import com.pratian.automation.pageclasses.HomePage;
import com.pratian.automation.pageclasses.LogInPage;
import com.pratian.automation.pageclasses.ViewCart;



public class AddToCart extends BaseTest {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReports;
	ExtentTest test1;

	public static Logger logger=LogManager.getLogger(AddToCart.class);
//	WebDriver driver=null;
//	String projectpath=System.getProperty("user.dir");
//	
//	
//
//	@org.testng.annotations.Parameters("browserName")
//	@BeforeTest
//	public void setup(String browserName) {
//	System.out.println("Browser Name is: " +browserName);
//		
//		if(browserName.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectpath+"C:\\wedriver\\chromedriver.exe");
//			driver=new ChromeDriver();
//			
//		}else if(browserName.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecho.driver", projectpath+"C:\\wedriver\\geckodriver.exe");
//			driver=new FirefoxDriver();
//		
//		}else if(browserName.equalsIgnoreCase("ie")) {
//			System.setProperty("webdriver.ie.driver", projectpath+"C:\\wedriver\\IEDriverServer.exe");
//			driver=new InternetExplorerDriver();
//		}
//	}

	@Test(priority = 1, dataProvider = "signIn")
	public void addToCart(String username,String password) throws IOException {
		logger.info("testcase addTocart started");
		htmlReporter=new ExtentHtmlReporter("extentReport3.html");
		logger.info("initilazing html reports");
		extentReports=new ExtentReports();
		logger.info("initializing extent reports");
		extentReports.attachReporter(htmlReporter);
		logger.info("attaching html report with extent report");
		
		test1=extentReports.createTest("saucedemo","this is a test to validate www.saucedemo app");
		test1.log(Status.INFO, "starting test case");
		driver.get(PropertyManager.getProperty("url.app"));
		logger.info("naviagation successfull to saucedemo application");
		test1.pass("login page navigation successfull");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		LogInPage login=new LogInPage(driver);
		login.setUserName(username);
		logger.info("usrname hasbeen entered");
		test1.pass("entered the username");
		login.setPassword(password);
		logger.info("password hasbeen enterd");
		test1.pass("entered the password");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		HomePage home=	login.clickLoginBtn();
		logger.info("naviagated to homepage");
		test1.pass("home page navigation successfull");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		home.clickAddToCart();
		logger.info("add to cart action performed");
		test1.pass("product added to cart");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		ViewCart viewCart=	home.clickOnCartLogo();
		logger.info("clicked on cart icon and navigating to viewcart page");
		test1.pass("product visble in cart");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertEquals(viewCart.getProductName(), "Sauce Labs Backpack");	
		logger.info("assertion successfull");
		test1.pass("assert successful");
		test1.pass("browser hasbeen closed");
		logger.info("browser hasbeen closed");
		test1.info("testcase1 completed");
		extentReports.flush();
		logger.info("etent report hasbeen generated");

		File screenshotFile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		logger.info("captured screenshot");

		FileUtils.copyFile(screenshotFile, new File(".//screenshots/screen.png"));
		logger.info("screenshot stored in screenshot folder");


	}
	@DataProvider(name = "signIn")
	public Object[][] getCredentials() {

		return new Object[][] {

			{ "standard_user", "secret_sauce" },

		};
	}
}
