package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	@SuppressWarnings("deprecation")
	WebDriver driver;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException {
	    prop = new Properties();
		String propPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream (propPath);
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

@SuppressWarnings("deprecation")
public String takeScreenshort(String testCaseName,WebDriver driver) throws IOException {
	  
			TakesScreenshot ts = (TakesScreenshot) driver;
			File SourceFile  = ts.getScreenshotAs(OutputType.FILE); 
		
			String destinationFilepath = System.getProperty("user.dir")+"\\screenshort\\"+ testCaseName +".png";
			FileUtils.copyFile(SourceFile,new File(destinationFilepath));
			//FileHandler.copy(SourceFile,new File(destinationFilepath));
			return destinationFilepath;
			
	    	
		   
	
}
}
