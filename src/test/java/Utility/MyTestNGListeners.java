  package Utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestCases.BaseTestMethods1;
import TestCases.BaseTestMethods;

public class MyTestNGListeners implements ITestListener {
	protected static ExtentReportsUtility extentReport = null;
	protected WebDriver driver;


	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentReport.logTestpassed("Test case");	
		}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentReport.logTestfailed(result.getMethod().getMethodName());
		//BaseTestMethods1 ob = new BaseTestMethods1();
		//String path = ob.getscreenshot(driver);
		//extentReport.logTestScreenShot(path);
	
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test script skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport = ExtentReportsUtility.getInstance();
		extentReport.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.endReport();
	}
	
	

}
