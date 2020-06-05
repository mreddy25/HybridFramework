package com.ccs.testCases;

import org.testng.annotations.Test;

import com.ccs.pageObjects.LoginPage;

import java.io.IOException;

//import junit.framework.Assert;
import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;

public class TC_LoginTest_001 extends BaseClass
{
	
	//test cases contains only Test method

	@Test
	public void loginTest() throws InterruptedException, IOException
	{
		
		logger.info("URL is opened");
		//here LoginPage- pageObject class
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickSubmit();
		Thread.sleep(3000);
		
		 if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		 
		 { 
			 //Thread.sleep(3000);
			 Assert.assertTrue(true);
			 logger.info("Login test passed");
		 }
		 else 
		 {
			 //call the captuerescreen method which is declared in basescalass
			 captureScreen(driver,"loginTest");
			 Assert.assertTrue(false);
			 logger.info("Login test filed");
		 
		 }
		 
	}
	
	

}
