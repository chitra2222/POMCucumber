package TestCases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageElements extends BasePage {
	
	//WebDriver driver;
	
	@FindBy(id = "username") WebElement username; 
	@FindBy(id = "password") WebElement password;
	@FindBy(id = "Login") WebElement login;
	@FindBy(id = "error") WebElement error;
	@FindBy(id = "rememberUn") WebElement remember;
	@FindBy(id = "forgot_password_link") WebElement forgotpwd;
	@FindBy(id = "header") WebElement forgotpage;
	@FindBy(id = "error") WebElement wrongnameError;
	@FindBy(id = "idcard-identity") WebElement usernamecheck;
	
	public LoginPageElements(WebDriver driver) {
	       super(driver);
		}
	
	public void enterUsername(String data) {
		username.sendKeys(data);
		//enterText(username, data, "Username element");
	}
	
	public void enterPassword(String data) {
		password.clear();
		password.sendKeys(data);
		//enterText(password, data, "password element");

	}
	
	public WebDriver clickLogin() {
		login.click();
		//clickButton(login, "Login button");
		return driver;
	}
	
	public void forgotPassword() {
		forgotpwd.click();
		//clickButton(forgotpwd, "Forgot password link");
	}
	
	public void clickremember() {
		remember.click();
		//clickButton(remember, "Remember checkbox");
	}
	
	
	public String errorgetText() {
		return error.getText();
		//return getTextmethod(error);
	}
	
	public String remembergetText() {
		return remember.getText();
		//return getTextmethod(remember);

	}
	
	public String forgotPagegetText() {
		return forgotpage.getText();
		//return getTextmethod(forgotpage);

	}
	
	public String wrongNamegetText() {
		return wrongnameError.getText();
		//return getTextmethod(wrongnameError);

	}
	
	public String usernameText() {
		return usernamecheck.getText();
		//return getTextmethod(usernamecheck);

	}
	
	
 

}
