package Amaze;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import objectRepository.landPage;
import utilities.Base;

public class LoginPage2 extends Base {
	public WebDriver driver;  // initializing like this will run paralell tests without failure
	landPage lp;
	//Properties prop = new Properties();
	@BeforeTest
	public void initialize() throws IOException {
		driver=initializedriver();
	}
	@Test
	public void SignIn1() throws IOException
	{
		
		lp = new landPage(driver);
		
		//initializedriver();
		driver.get("https://www.amazon.in/");
		lp.hover();
		lp.SignInn().click();
		lp.userName().sendKeys("niruaqua.08@gmail.com");
		lp.ctnubttn().click();
		lp.pwd().sendKeys("Niru@amazon8");
		lp.userName().click();
		
	}
	
	@AfterTest
	public void flush()
	{
		driver.close();
	}
}
