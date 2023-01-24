package Stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import PageObjects.AccountPage;
import PageObjects.LandingPage;
import PageObjects.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Base;

public class login extends Base {
	WebDriver driver;
	LandingPage ladingPage;
	LoginPage loginPage;
	AccountPage accountPage ;

    @Given("^Open any browser$")
    public void open_any_browser() throws IOException  {
    	 driver = initializeDriver();
  
 	
        
    }

    @And("^User navigate to the login page$")
    public void user_navigate_to_the_login_page() throws InterruptedException{
    	driver.get(prop.getProperty("URL"));
    	 ladingPage = new LandingPage(driver);
    	ladingPage.myaccountDropdown().click();
 		
 		ladingPage.logIn().click();
 	
 		Thread.sleep(3000);
       
    }

    @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void user_enters_username_as_something_and_password_as_something(String username, String password)  {
    	loginPage = new LoginPage(driver);
		loginPage.Email().sendKeys(username);
	
		loginPage.password().sendKeys(password);

    }
    @And("^user clicks on Login button$")
    public void user_clicks_on_login_button()  {
    	loginPage.login().click();
        
    }

    @Then("^Verify user is able to successfully login$")
    public void verify_user_is_able_to_successfully_login()  {
    	accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.editAccountInformation().isDisplayed());
    }


	@After
	public void closeBrowser() {
		driver.close();
	}


}
