package com.ccs.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.ccs.utilities.ReadConfig;
//import org.testng.log4testng.Logger;


public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();
	
	//without read file
	//public String baseURL="http://demo.guru99.com/v4/";
	//if you have configured read file below code is the alternate for the above one
	//getApplicationURL();- this method is defined in ReadConfig.java for get URL
	public String baseURL=readconfig.getApplicationURL();
	
	//public String username="mngr261425";
	public String username=readconfig.getUsername();
	
	//public String password="gEraqur";
	public String password=readconfig.getPassword();
	
	public static WebDriver driver; 
	
	public static Logger logger;
	
	@Parameters("browser")//this parameter is to be specified in xml file
	@BeforeClass
	public void setup(String br)
	{
		//for logs 
		logger = Logger.getLogger("ccs");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
				//System.setProperty("webdriver.chrome.driver", "D:\Selenium\MyWorkspace\\HybridFramework\\Drivers\\chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
			
			//FirefoxOptions firefoxOptions = new FirefoxOptions();
			//firefoxOptions.setCapability("marionette", true);
			//driver = new FirefoxDriver(firefoxOptions);
			
			/**
			
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability("firefox_binary","C:\......\firefox.exe");
			FirefoxOptions options = new FirefoxOptions(cap);
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			**/
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
				//maximize the browser and page load
		//driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		//driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//enter base URL
		driver.get(baseURL);
	}
		
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		
	}
	
	//this method to be called whenever the test method failed
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public String randomeNum()
	{	
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return(generatedstring2);
	}

}
