package Amaze;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import objectRepository.landPage;
import utilities.Base;

public class LoginPage extends Base {
	public WebDriver driver; // initializing like this will run paralell tests without failure
	landPage lp;
	//Properties prop = new Properties();
	
	Logger log = LogManager.getLogger(LoginPage.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		driver=initializedriver();
		log.info("initialized the driver 1");
	}
	@Test
	public void SignIn() throws IOException
	{
		
		lp = new landPage(driver);
		
		//initializedriver();
		driver.get("https://www.amazon.in/");
		lp.hover();
		lp.SignInn().click();
		lp.userName().sendKeys("niruaqua.08@gmail.com");
		
		lp.ctnubttn().click();
		log.info("User name is correct");
		lp.pwd().sendKeys("Niru@amazon8");
		lp.submit().click();
		
		log.error("do we have wrong password");
		
	}
	
	@AfterTest
	public void flush()
	{
		driver.close();
	}
	
}
