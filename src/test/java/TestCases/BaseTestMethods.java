package TestCases;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Utility.Constants;
import Utility.ExtentReportsUtility;
import Utility.PropertiesUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestMethods {
   protected static WebDriver driver = null;
   protected static WebDriverWait wait = null;
   protected static Logger logger = null;
   protected static ExtentReportsUtility extentReport = ExtentReportsUtility.getInstance();


   
   @BeforeTest
  /* public void setBeforeTest() {
   	logger=LogManager.getLogger(BaseTestMethods.class.getName());
   	
   }*/
   
   @BeforeMethod 
  /* public static void beforemethod(Method method) {
		logger.info("Test Script started" + method.getName());
	   PropertiesUtility propertyutility = new PropertiesUtility();
		propertyutility.loadFile("TestDataProperties");
		String url = propertyutility.getPropertyValue("url");
		getBrowser(url);

    }*/
   
   @AfterMethod
   public static void aftermethod(Method method) throws IOException {
		logger.info("Test Script ended" + method.getName());
		getscreenshot();
	   driver.close();
   }
	public static void getBrowser(String url) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); 
	}
	
	public static void enterText(WebElement element, String text, String name) {
		if(element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
			logger.info(name+" is entered");
			}else {
				logger.info(name+" is not displayed");
			}
	}
	
	public static void clickButton(WebElement element, String btnName) {
		if(element.isEnabled()) {
		element.click();
		logger.info(btnName+" is clicked");
		}else {
			logger.info(btnName+" is not enabled");
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
			}else {
				logger.error("Test case failed");
			}
		}else {
			logger.error(name+ " element is not displayed");
		}
	}
	
	public static void validateTitle(String expected) {
		if(driver.getTitle().contains(expected))
		{
			logger.info("TestCase passed");
		}
		else {
			logger.error("Test case failed");
		}
	}
	public static void assertvalue(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
	public static void getscreenshot() throws IOException {
		String date =  new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		System.out.println(date);
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File imgfile = screenshot.getScreenshotAs(OutputType.FILE);
		String path = Constants.SCREENSHOT_DIRECTORY_PATH;
		File destfile = new File(path + date+".png");
		FileHandler.copy(imgfile, destfile);
	//how to use method here?
	}
	


}
