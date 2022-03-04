package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporterNG {
	static ExtentReports extent;
	public static ExtentReports getReportObj()
	{
		
		//extentReports, ExtentSparkReporter
				String path = System.getProperty("user.dir")+"\\reports\\index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
				reporter.config().setReportName("Web Automation");
				reporter.config().setDocumentTitle("Test Results");
				
				// below code is imp
				
				extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Niranjan");
				// return the object so testNG can utilize this utility
				return extent;
	}
	
}
