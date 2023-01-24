package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);}

	@FindBy(id = "input-email")
	private WebElement email;
	@FindBy(id = "input-password")
	private WebElement Password;
	@FindBy(xpath = "//form[@method='post']//input[@value='Login']")
	private WebElement Login;

		public WebElement Email() {
			return email;
		}
		public WebElement password() {
			return Password;
		}
		public WebElement login() {
			return Login;
		}
	}


