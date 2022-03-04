package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Base {
	public WebDriver driver ;
	//public static WebDriver driver ; // this will work in parallel mode.. but sequential mode it might break 
	// we over come this only for parallel by creating local driver in each test. i,e we have sent to another driver
	// in general people won't mark driver static because it will have only one object & 
	// the same instance will be overriden and parallel test have to wait till one test completes. 
	// comment all local driver and run with help of Base class driver object and verify for confirmation
	//we cannot run with static webdriver for parallel execution.
	public WebDriver initializedriver() throws IOException
	{
		Properties prop = new Properties();
		//FileInputStream fis = new FileInputStream("C:\\Users\\nirlm\\eclipse-workspace\\E2EProject\\src\\main\\java\\utilities\\data.properties");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\utilities\\data.properties");
		
		prop.load(fis);
	    String browserName=prop.getProperty("browser");
		
	    /* without head less
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\chromedriver.exe");
			//driver = new ChromeDriver();
			driver = new ChromeDriver();
		}*/
	    if(browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\chromedriver.exe");
			//driver = new ChromeDriver();
			// below code is to run chrome in headless mode
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) // this will check if they have opted for headless and then will go inside if not itv will run without		
			{
				options.addArguments("headless");
				//driver = new ChromeDriver(options);
			}
			driver = new ChromeDriver(options);
		}
	    
		else if (browserName.equals("firefox"))
		{
			
		}
		
		else if(browserName.equals("IE"))
		{
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;	
	}
	
	public String getScreenshot(String testName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile; // added this as step 4
	}
}
