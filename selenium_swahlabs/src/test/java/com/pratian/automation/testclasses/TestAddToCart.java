package com.pratian.automation.testclasses;




import java.io.File;




import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.beust.jcommander.Parameters;
import com.pratian.automation.filehandling.PropertyManager;
import com.pratian.automation.pageclasses.HomePage;
import com.pratian.automation.pageclasses.LogInPage;
import com.pratian.automation.pageclasses.ViewCart;


public class TestAddToCart extends BaseTest {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReports;
	ExtentTest test1;


//	WebDriver driver=null;
	//			String projectpath=System.getProperty("user.dir");
	//			
	//
	//
//	@org.testng.annotations.Parameters("browserName")
//	@Parameters("browserName")
//	@BeforeTest
//	
//	public void setup(String browserName) {
//		System.out.println("Browser Name is: " +browserName);
//
//		if(browserName.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver","C:\\wedriver\\chromedriver.exe");
//			driver=new ChromeDriver();
//
//		}else if(browserName.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecho.driver","C:\\wedriver\\geckodriver.exe");
//			driver=new FirefoxDriver();
//
//		}else if(browserName.equalsIgnoreCase("ie")) {
//			System.setProperty("webdriver.ie.driver","C:\\wedriver\\IEDriverServer.exe");
//			driver=new InternetExplorerDriver();
//		}
//	}
////
	@Test(priority = 1, dataProvider = "signIn")
	public void addToCart(String username,String password) throws IOException {
		htmlReporter=new ExtentHtmlReporter("extentReport3.html");

		extentReports=new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		test1=extentReports.createTest("saucedemo app","this is a test to validate www.saucedemo app");
		test1.log(Status.INFO, "starting test case");
		driver.get(PropertyManager.getProperty("url.app"));
		test1.pass("login page navigation successfull");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		LogInPage login=new LogInPage(driver);
		login.setUserName(username);
		test1.pass("entered the username");
		login.setPassword(password);
		test1.pass("entered the password");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		HomePage home=	login.clickLoginBtn();
		test1.pass("home page navigation successfull");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		home.clickAddToCart();
		test1.pass("product added to cart");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		ViewCart viewCart=	home.clickOnCartLogo();
		test1.pass("product visble in cart");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Assert.assertEquals(viewCart.getProductName(), "Sauce Labs Backpack");	
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		test1.pass("assert successful");
		test1.pass("browser hasbeen closed");
		test1.info("testcase1 completed");
//		driver.close();
		//			extentReports.flush();

		//			File screenshotFile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//
		//			FileUtils.copyFile(screenshotFile, new File(".//screenshots/screen.png"));


	}
	//		@DataProvider(name = "signIn")
	//		public Object[][] getCredentials() {
	//
	//			return new Object[][] {
	//
	//				{ "standard_user", "secret_sauce" },
	//
	//			};
	//		}
	@DataProvider(name="signIn")
	String [][] getData() throws IOException
	{
		String path= System.getProperty("user.dir")+"/src/test/java/com/pratian/automation/datadriven/datadriven.xlsx";

		int rownum=com.pratian.automation.utils.UtilsJava.getRowCount(path, "Sheet1");
		int colcount=com.pratian.automation.utils.UtilsJava.getCellCount(path,"Sheet1",1);

		String logindata[][]=new String[rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=com.pratian.automation.utils.UtilsJava.getCellData(path,"Sheet1", i,j);
			}

		}
		return logindata;
	}
}


