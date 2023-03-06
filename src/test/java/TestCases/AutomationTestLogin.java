package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Utility.PropertiesUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(Utility.MyTestNGListeners.class)
public class AutomationTestLogin extends BaseTestMethods {

@Test(priority = 1, alwaysRun = true)
public void TestCase1() throws InterruptedException {
	logger.info("Inside Test case1");
	extentReport.logTestinfo("Inside Test case1");
	String expected = "Please enter your password.";
	PropertiesUtility propertyutility = new PropertiesUtility();
	propertyutility.loadFile("TestDataProperties");
	String uname = propertyutility.getPropertyValue("login.valid.userid");
	String pwd = "";
	Thread.sleep(4000);
	LoginPageElements loginpage = new LoginPageElements(driver);
	loginpage.enterUsername(uname);
	loginpage.enterPassword(pwd);
	driver = loginpage.clickLogin();
	String actual = loginpage.errorgetText();
	Assert.assertEquals(actual, expected);
}
@Test(priority = 2, alwaysRun = true)
public void TestCase2() throws InterruptedException {
	String expected = "Home";
	logger.info("Inside Test case2");
	extentReport.logTestinfo("Inside Test case2");
	PropertiesUtility propertyutility = new PropertiesUtility();
	propertyutility.loadFile("TestDataProperties");
	String uname = propertyutility.getPropertyValue("login.valid.userid");
	String pwd = propertyutility.getPropertyValue("login.valid.password");
	Thread.sleep(4000);
	LoginPageElements loginpage = new LoginPageElements(driver);
	loginpage.enterUsername(uname);
	loginpage.enterPassword(pwd);
	driver = loginpage.clickLogin();
	Thread.sleep(4000);
	HomePageElements homepage = new HomePageElements(driver);
	String actual = homepage.homepageText();
	Assert.assertEquals(actual, expected);	
}
@Test(priority = 3, alwaysRun = true)
public void TestCase3() throws InterruptedException {
	String expected ="chitra@tekarch.com";
	logger.info("Inside Test case3");
	extentReport.logTestinfo("Inside Test case3");
	PropertiesUtility propertyutility = new PropertiesUtility();
	propertyutility.loadFile("TestDataProperties");
	String uname = propertyutility.getPropertyValue("login.valid.userid");
	String pwd = propertyutility.getPropertyValue("login.valid.password");
	Thread.sleep(4000);
	LoginPageElements loginpage = new LoginPageElements(driver);
	loginpage.enterUsername(uname);
	loginpage.enterPassword(pwd);
	loginpage.clickremember();
	driver = loginpage.clickLogin();
	Thread.sleep(4000);
	HomePageElements homepage = new HomePageElements(driver);
	homepage.profileClick();
	homepage.logout();
	Thread.sleep(4000);
	String actual = loginpage.usernameText();
	Assert.assertEquals(actual, expected);
}
@Test(priority = 4, alwaysRun = true)
public void TestCase4() throws InterruptedException {
	String expected = "Forgot Your Password";
	logger.info("Inside Test case4");
	extentReport.logTestinfo("Inside Test case4");
	PropertiesUtility propertyutility = new PropertiesUtility();
	propertyutility.loadFile("TestDataProperties");
	Thread.sleep(4000);
	LoginPageElements loginpage = new LoginPageElements(driver);
	loginpage.forgotPassword();
	Thread.sleep(4000);
	String actual = loginpage.forgotPagegetText();
	Assert.assertEquals(actual, expected);
}
@Test(priority = 5, alwaysRun = true)
public void TestCase5() throws InterruptedException {
	String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
	logger.info("Inside Test case5");
	extentReport.logTestinfo("Inside Test case5");
	PropertiesUtility propertyutility = new PropertiesUtility();
	propertyutility.loadFile("TestDataProperties");
	String unameinvalid = propertyutility.getPropertyValue("login.invalid.userid");
	String pwdinvalid = propertyutility.getPropertyValue("login.invalid.password");
	Thread.sleep(4000);
	LoginPageElements loginpage = new LoginPageElements(driver);
	loginpage.enterUsername(unameinvalid);
	loginpage.enterPassword(pwdinvalid);
	loginpage.clickremember();
	driver = loginpage.clickLogin();
	Thread.sleep(4000);
	String actual = loginpage.wrongNamegetText();
	Assert.assertEquals(actual, expected);		
}

}
