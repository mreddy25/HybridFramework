package com.ccs.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ccs.pageObjects.LoginPage;
import com.ccs.utilities.XLUtils;


public class TC_LoginDDT_002 extends BaseClass
{
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("user name provided");
		lp.setPassword(password);
		logger.info("password provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		//Alert method
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);	
			logger.warn("Login failed");
		
		}
		else
		{
			Assert.assertTrue(true);
			logger.warn("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}

	}
	
	
	//if you get any alerts like invalid password- it will verify that the alert is present or not
	public boolean isAlertPresent()//user defined method created to check alert is present or not
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/ccs/testData/LoginData.xlsx";
	
	//read the data from xlsx
		//get row count
		int rownum=XLUtils.getRowCount(path, "Sheet1");//path- xl location path and sheet1 - sheet name
		//get cell count
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1); //here 1- row number
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)//outer for loop for row
		{
			for(int j=0;j<colcount;j++)//inner for loop for column
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
				
	return 	logindata;
	}
}
