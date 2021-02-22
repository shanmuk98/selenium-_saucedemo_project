package com.pratian.automation.pageclasses;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCart extends BasePage {

	@FindBy(xpath="//*[@id=\"contents_wrapper\"]/div[2]")  
	WebElement ViewCart;
	@FindBy(xpath="//*[@id=\"item_4_title_link\"]/div")
	WebElement productName;
	public ViewCart(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	public String getProductName() {
		return productName.getText();
	}
}
