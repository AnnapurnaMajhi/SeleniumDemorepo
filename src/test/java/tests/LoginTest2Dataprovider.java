package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import PageObjects.LandingPage;
import PageObjects.LoginPage;
import resources.Base;

public class LoginTest2Dataprovider extends Base {
	Logger log;
	public WebDriver driver;

	@Test(dataProvider = "getloginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {

		LandingPage ladingPage = new LandingPage(driver);

		ladingPage.myaccountDropdown().click();
		log.debug("Clicked on myAccount dropDown");
		ladingPage.logIn().click();
		log.debug("Clicked on the login option");
		Thread.sleep(3000);
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.Email().sendKeys(email);
		log.debug("Email address got entered");
		loginPage.password().sendKeys(password);
		log.debug("Password got entered");

		loginPage.login().click();
		log.debug("Clicked on Login Button");
		AccountPage accountPage = new AccountPage(driver);
	 
		String ActualResult = null;
		try {
			if (accountPage.editAccountInformation().isDisplayed()) {
				log.debug("User got loggedin");
				ActualResult = "Successful";
			}
		}

		catch (Exception e) {
			ActualResult  = "failure";
			log.debug("User did not loggedin");
		}
		Assert.assertEquals(ActualResult , expectedResult);

	}
	
	@BeforeMethod 
	public void OpenApplication() throws IOException {
		log = LogManager.getLogger(LoginTest2Dataprovider.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("URL"));
		log.debug("Navigated to Application URL");
	}

	@AfterMethod
	public void closer() {
		driver.close();
	}

	@DataProvider
	public Object[][] getloginData() {
		Object[][] data = { { "annapurnamajhi3@gmail.com", "majhi@345", "Successful" },
				{ "an@gmail.com", "mayuu", "failure" } };
		return data;
	}

}
