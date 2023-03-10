package TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
	WebDriver driver = null;
	 ExtentReports extentReport = ExtentReporter.getExtentReports();
	 ExtentTest extentTtest;
	 ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		String testName= result.getName();
		extentTtest =extentReport.createTest(testName+"execution started");
		extentTestThread.set(extentTtest );
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName= result.getName();
		//extentTtest.log(Status.PASS, testName+"got passed");
		extentTestThread.get().log(Status.PASS, testName+"got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testCaseName = result.getName();
		//extentTtest.fail(result.getThrowable());
		extentTestThread.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			String screenShortFilepath = takeScreenshort(testCaseName, driver);
			extentTestThread.get().addScreenCaptureFromPath(screenShortFilepath, testCaseName);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}
