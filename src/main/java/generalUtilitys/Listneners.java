package generalUtilitys;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listneners extends Baseclass implements ITestListener {
	
	ExtentTest test;
	WebDriver driver;
	ExtentReports extent = extentReporterNG.getReportObject();

	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	/* thread safe in parallel execution */
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extendTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.get().log(Status.PASS, "Test is Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		extendTest.get().fail(result.getThrowable());

		try {

			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e)

		{
			e.printStackTrace();

		}

		String filePath = null;

		try {

			filePath = getScreenShots(result.getMethod().getMethodName(), driver);

		} catch (IOException e) {

			e.printStackTrace();
		}

		extendTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}


}
