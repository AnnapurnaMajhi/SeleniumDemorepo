package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import PageObjects.LandingPage;
import PageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	
	@Test
	public void login() throws IOException {
	    driver = initializeDriver();
		driver.get(prop.getProperty("URL"));
		LandingPage ladingPage = new LandingPage(driver);
				
	  ladingPage.myaccountDropdown().click();
	  ladingPage.logIn().click();
	  LoginPage loginPage =new LoginPage(driver);
	  loginPage.Email().sendKeys(prop.getProperty("email"));
	  loginPage.password().sendKeys(prop.getProperty("password"));
	  
	  loginPage.login().click();
	  AccountPage accountPage =new AccountPage(driver);
	Assert.assertTrue(accountPage.editAccountInformation().isDisplayed());
	}
	
	@AfterMethod
	public void closer() {
		driver.close();
	}
	

	
}
