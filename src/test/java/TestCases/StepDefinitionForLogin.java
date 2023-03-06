package TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import Utility.PropertiesUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class StepDefinitionForLogin extends BaseTestMethods {
	public static Logger logger = null;
	LoginPageElements login;
	HomePageElements home;
	String uname;
	String pwd;
	String unameinvalid;
	String pwdinvalid;
	String url;
	
	@Before
	public void setup() {
		logger=LogManager.getLogger(StepDefinitionForLogin.class.getName());	
		PropertiesUtility propertyutility = new PropertiesUtility();
		propertyutility.loadFile("TestDataProperties");
		url = propertyutility.getPropertyValue("url");
		uname = propertyutility.getPropertyValue("login.valid.userid");
		pwd = propertyutility.getPropertyValue("login.valid.password");
		unameinvalid = propertyutility.getPropertyValue("login.invalid.userid");
		pwdinvalid = propertyutility.getPropertyValue("login.invalid.password");
		
	}
	
	
	@Given("User open the salesforce application")
	public void user_open_the_salesforce_application() throws InterruptedException {
		getBrowser(url);
		logger.info("Entered the Salesforce url");
		Thread.sleep(4000);
	}
	
	@After
	public void afterScenario() throws IOException{
		getscreenshot();
		driver.close();
		logger.info("Exit the Salesforce application");
	}

	@When("user in {string}")
	public void user_in(String page) {
		if(page.equalsIgnoreCase("LoginPage")) {
			login = new LoginPageElements(driver); 
		} 
		else if(page.equalsIgnoreCase("HomePage")) {
			home = new HomePageElements(driver); 
		}
	
	}

	@When("user enters value in username text box")
	public void user_enters_value_in_username_text_box() {
		login.enterUsername(uname);
		logger.info("valid Username is entered " +uname);
	}

	@When("user enters value in password field")
	public void user_enters_value_in_password_field() {
		login.enterPassword(pwd);
		logger.info("valid password is entered " +pwd);

	}

	@When("user clicks on Login")
	public void user_clicks_on_login() {
		login.clickLogin();
		logger.info("login button is clicked");

	}

	@Then("verify the error message {string}")
	public void verify_the_error_message(String exp) {
		String actual = login.errorgetText();
		Assert.assertEquals(actual, exp);
		if(true)
			logger.info("Test scenario passed ");
		else
			logger.info("Test scenario failed ");
		}

	@Then("verify the Home page title {string}")
	public void verify_the_home_page_title(String expected) throws InterruptedException {
		Thread.sleep(4000);
		String actual = home.homepageText();
		Assert.assertEquals(actual, expected);
		if(true)
			logger.info("Test scenario passed ");
		
	}

	@When("user checks remember me")
	public void user_checks_remember_me() {
	  login.clickremember();

	}

	@When("user clicks on logout from profile menu")
	public void user_clicks_on_logout_from_profile_menu() throws InterruptedException {
			Thread.sleep(4000);
		   home.profileClick();
			home.logout();
	}

	@Then("verify {string} is displayed in username field")
	public void verify_is_displayed_in_username_field(String expected) throws InterruptedException {
		Thread.sleep(5000);
		String actual = login.usernameText();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
		if(true)
			logger.info("Test scenario passed ");
		
	}

	@When("user clicks the Forgot password")
	public void user_clicks_the_forgot_password() {
		login.forgotPassword();
	}
	
	@When("user enters wrong value in username text box")
	public void user_enters_wrong_value_in_username_text_box() {
		login.enterUsername(unameinvalid);
		logger.info("Invalid user name is entered" +unameinvalid);
		
	}

	@When("user enters wrong value in password field")
	public void user_enters_wrong_value_in_password_field() {
		login.enterPassword(pwdinvalid);
		logger.info("Invalid user password is entered" +pwdinvalid);
	}

	@Then("verify the displayed forgot password message")
	public void verify_the_displayed_forgot_password_message() throws InterruptedException {
		String expected ="Forgot Your Password";
		Thread.sleep(4000);
		String actual = login.forgotPagegetText();
		Assert.assertEquals(actual, expected);

	}

	@Then("verify the error message for wrong credentials {string}")
	public void verify_the_error_message_for_wrong_credentials(String expected) throws InterruptedException {
		Thread.sleep(4000);
		String actual = login.wrongNamegetText();
		Assert.assertEquals(actual, expected);
		if(true)
			logger.info("Test scenario passed ");
	}

}
