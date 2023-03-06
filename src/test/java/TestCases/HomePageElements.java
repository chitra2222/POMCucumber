package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class HomePageElements extends BasePage {
	

	//WebDriver driver;
	
	@FindBy(id = "home_Tab") WebElement home; 
	@FindBy(id = "userNavLabel") WebElement profile;
	@FindBy(xpath = "//a[@title=\"Logout\"]") WebElement logout;
	
	public HomePageElements(WebDriver driver) {
		super(driver);
		}
	
	public void profileClick() {
		profile.click();
		//clickButton(profile, "Profile link");
	}
	
	public void logout() {
		logout.click();
		//clickButton(logout, "logout link");

	}
	
	public String homepageText() {
		return home.getText();
		//return getTextmethod(home);
		
	}

}
