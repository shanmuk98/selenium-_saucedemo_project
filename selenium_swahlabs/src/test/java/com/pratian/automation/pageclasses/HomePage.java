package com.pratian.automation.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pratian.automation.testclasses.AddToCart;

public class HomePage extends BasePage{

	@FindBy(xpath="//*[@id=\"inventory_filter_container\"]/div")
	WebElement Products;
	@FindBy(xpath="//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")
	WebElement addToCart;
	@FindBy(xpath="//*[@id=\"shopping_cart_container\"]/a/span")
	WebElement cartLogo;
	
	AddToCart addProductsToCart;
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	public void clickAddToCart() {
		this.addToCart.click();
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOf(addToCart)).click();
		addProductsToCart.logger.info("clicked on addToacrtButton");
	}
	public ViewCart clickOnCartLogo() {
		this.cartLogo.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(cartLogo)).click();
		
		addProductsToCart.logger.info("clicked on cart icon");
		return new ViewCart(driver);
	}

}
