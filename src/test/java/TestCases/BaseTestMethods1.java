
package TestCases;
 
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Utility.Constants;
import Utility.ExtentReportsUtility;
import Utility.PropertiesUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestMethods1 {
protected static WebDriver driver = null;
protected static Logger logger = null;
protected static ExtentReportsUtility extentReport = ExtentReportsUtility.getInstance();

@BeforeTest
public void setBeforeTest() {
	//extentReport.logTestinfo("Before Test method has started");
	logger=LogManager.getLogger(BaseTestMethods1.class.getName());	
}
@BeforeMethod
  public static void login(Method method){
	logger.info("Test Script started " + method.getName());
	//extentReport.logTestinfo("Test Script started " + method.getName());
	PropertiesUtility propertyutility = new PropertiesUtility();
	propertyutility.loadFile("TestDataProperties");
    String url = propertyutility.getPropertyValue("url");
	String uname = propertyutility.getPropertyValue("login.valid.userid");
	String pwd = propertyutility.getPropertyValue("login.valid.password");
	getBrowser(url);
	
	WebElement username = driver.findElement(By.id("username"));
	enterText(username, uname,"User i.d");
	WebElement password = driver.findElement(By.id("password"));
	enterText(password, pwd ,"Password");
	WebElement login = driver.findElement(By.id("Login"));
	clickButton(login,"Login");	
}
@AfterMethod	
public static void closing(Method method) {
	logger.info("Test Script ended " + method.getName());
	//extentReport.logTestinfo("Test Script ended " + method.getName());

	driver.close();
	
	}


	public static void getBrowser(String url) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public static void enterText(WebElement element, String text, String name) {
		if(element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
			logger.info(name + "is entered");
			//extentReport.logTestinfo(name + "is entered");
			}else {
				logger.info(name+" is not displayed");
				//extentReport.logTestinfo(name + "is not displayed");

			}
	}
	
	public static void clickButton(WebElement element, String btnName) {
		if(element.isEnabled()) {
		element.click();
		logger.info(btnName+" is clicked");
		//extentReport.logTestinfo(btnName + " is clicked");

		
		}else {
			logger.info(btnName+" is not enabled");
			//extentReport.logTestinfo(btnName + " is not enabled");

		}
	}
	
	public static void selectDropDown(WebElement element, String text) {
		Select ob = new Select(element);
		ob.selectByVisibleText(text);
	}
	
	public static void validate(WebElement element, String expected, String name) {
		if(element.isDisplayed()) {
			String actual = element.getText();
			if(actual.contains(expected)) {
				logger.info("Test case passed");
				//extentReport.logTestpassed("Test case");
			}else {
				logger.error("Test case failed");
				//extentReport.logTestfailed("Test case");
			}
		}else {
			logger.error(name+ " element is not displayed");
			//extentReport.logTestinfo(name+ " element is not displayed");
		}
	}
	
	public static void validateTitle(String expected) {
		if(driver.getTitle().contains(expected))
		{
			logger.info("TestCase passed");
			//extentReport.logTestpassed("Test case");
		}
		else {
			logger.error("Test case failed");
			//extentReport.logTestfailed("Test case");

		}
	}
	public static void assertvalue(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	
	public static void close() {
		driver.close();
	}
	public static void getscreenshot(){
		String date =  new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		System.out.println(date);
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File imgfile = screenshot.getScreenshotAs(OutputType.FILE);
		String path = Constants.SCREENSHOT_DIRECTORY_PATH;
		File destfile = new File(path + date+".png");
		try {
			FileHandler.copy(imgfile, destfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//how to use method here?
	}
	public static String getscreenshot(WebDriver driver) {
		String date =  new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		System.out.println(date);
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File imgfile = screenshot.getScreenshotAs(OutputType.FILE);
		String path = Constants.SCREENSHOT_DIRECTORY_PATH;
		File destfile = new File(path + date+".png");
		try {
			FileHandler.copy(imgfile, destfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destfile.getAbsolutePath();
	//how to use method here?
	}

}
