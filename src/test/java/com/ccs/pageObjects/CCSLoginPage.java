package com.ccs.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CCSLoginPage {
	WebDriver ldriver;
public CCSLoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	@FindBy(how = How.XPATH, using ="//div[@class='oj-flex-item oj-sm-12']/input")
	@CacheLookup
	WebElement txtUserName;
	
	//@FindBy(name="password")
	//@CacheLookup
	//WebElement txtPassword;
	//WebElement signInBtn = driver.findElement(By.xpath("//oj-button[@id='idcs-signin-basic-signin-form-submit']"));
	
	@FindBy(name="signInBtn")
	@CacheLookup
	WebElement signInBtn;
	
	@FindBy(xpath="/html/body/div[2]/div/oj-navigation-list/div[2]/div[2]/div/ul/li[6]/id")
	@CacheLookup
	WebElement lnkSignoff;
	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);	
	}
	
	//public void setPassword(String pwd)
	//{
	//	txtPassword.sendKeys(pwd);	
	//}
	
	public void clickSignin()
	{
		signInBtn.click();	
	}
	public void clickSignoff()
	{
		lnkSignoff.click();
	}
}

