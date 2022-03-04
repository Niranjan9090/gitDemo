package Amaze;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Base;
import utilities.extentReporterNG;

public class Listeners extends Base implements ITestListener {
	// return type will be extent report so we have below '='
	ExtentReports extent=extentReporterNG.getReportObj(); //1 step
	
	ExtentTest test; //2 step
	// step 3 create thread local so wrong test fail report won't be generated while we override same driver this 
	// ->issue might appear(pass parameter for <ExtentTest> we want thread safe )
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //3 step
	
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		 test=extent.createTest(result.getMethod().getMethodName());// 1
		 extentTest.set(test);// 3
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.PASS, "Test Passed"); step 1
		extentTest.get().log(Status.PASS, "Test Passed"); // step 2
	}

	public void onTestFailure(ITestResult result){
		//test.fail(result.getThrowable());// this will mark fail in extent report <- step 1 
		
		extentTest.get().fail(result.getThrowable());//step 3
		
		// This does not fail in parallel execution
		WebDriver driver=null; // assign as null
		// get failed test name
		String testMethodName=result.getMethod().getMethodName();
		// catch the driver and pass it to method 
		// below is to get access to any method in other class for testNG listener kindly use below length code
		// we are getting instance so we get driver(cast with webDriver so it will know )
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName,driver), result.getMethod().getMethodName()); // step 4
			//getScreenshot(testMethodName,driver); this is old stpe before step 4 for screen shot
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		// flush extent
		extent.flush();
	}

}
