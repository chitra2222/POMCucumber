package Utility;

import com.aventstack.extentreports.ExtentReports;	
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtility {
	public static ExtentReports report;
	public static ExtentSparkReporter sparkreporter;
	public static ExtentTest testlogger;
	private static ExtentReportsUtility extentobject;
	
	private ExtentReportsUtility() {
		
	}
	public static ExtentReportsUtility getInstance() {
		if(extentobject == null) {
			extentobject = new ExtentReportsUtility();
		}
			return extentobject;
	
	}

	public void startExtentReport() {
		sparkreporter = new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
		report = new ExtentReports();
		report.attachReporter(sparkreporter);
		
		report.setSystemInfo("Host name", "Sales force");
		report.setSystemInfo("Environment", "Automation Teting");
		report.setSystemInfo("User name", "Chitra");
		
		sparkreporter.config().setDocumentTitle("Test Execution Report");
		sparkreporter.config().setReportName("Salesforce");
		sparkreporter.config().setTheme(Theme.DARK);
		
	}
	
	public void startSingleTestReport(String testcasename) {
		testlogger=report.createTest(testcasename);
	}
	
	public void logTestinfo(String text) {
		testlogger.info(text);
	}
	public void logTestpassed(String testcasename) {
		testlogger.pass(MarkupHelper.createLabel(testcasename+" is passed ", ExtentColor.GREEN));
	}
	public void logTestfailed(String testcasename) {
		testlogger.pass(MarkupHelper.createLabel(testcasename+" is failed ", ExtentColor.RED));

	}
	 public void logTestScreenShot(String path) {
		 testlogger.addScreenCaptureFromPath(path);
	 }
	
	public void endReport() {
		report.flush();
	}

}
