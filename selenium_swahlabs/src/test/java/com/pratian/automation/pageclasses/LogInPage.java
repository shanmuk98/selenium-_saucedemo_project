package com.pratian.automation.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pratian.automation.testclasses.AddToCart;

public class LogInPage extends BasePage {

	@FindBy(xpath="//*[@id=\"user-name\"]")
	WebElement userName;
	@FindBy(xpath="//*[@id=\"password\"]")
	WebElement password;
	@FindBy(id="login-button")
	WebElement logIn;
	AddToCart addProductsToCart;
	
	public LogInPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	public void setUserName(String username) {
		this.userName.sendKeys(username);
		addProductsToCart.logger.info("username hasbeen entered ");
	}
	public void setPassword(String pwd) {
		this.password.sendKeys(pwd);
		addProductsToCart.logger.info("password hasbeen eneterd");
	}
	public HomePage clickLoginBtn() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(logIn)).click();
		addProductsToCart.logger.info("clicked clicked on login button");
//		this.logIn.click();
		return new HomePage(driver);
	}

}
