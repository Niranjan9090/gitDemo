package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class landPage {

	WebDriver driver;
	
	public landPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By hover = By.id("nav-link-accountList");
	By SignInn = By.className("nav-action-inner");
	By userName = By.id("ap_email");
	By ctnubttn = By.id("continue");
	By pwd = By.name("password");
	By submit = By.id("signInSubmit");
	 
	public void hover()
	{
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(hover)).build().perform();
	}
	
	public WebElement SignInn()
	{
		return driver.findElement(SignInn);
	}
	
	public WebElement userName()
	{
		return driver.findElement(userName);
	}
	
	public WebElement ctnubttn()
	{
		return driver.findElement(ctnubttn);
	}
	
	public WebElement pwd()
	{
		return driver.findElement(pwd);
	}
	
	public WebElement submit()
	{
		return driver.findElement(submit);
	}
}
