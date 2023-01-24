package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class failTest extends Base{
	public WebDriver driver;
	@Test
public void failtest() throws IOException {
	System.out.println("Fail Test Executed");
	driver = initializeDriver();
	driver.get("http://www.tutorialsninja.com/demo/");
	Assert.assertTrue(false);
	
}
	@AfterMethod
	public void closer() {
		driver.close();
	}
}
