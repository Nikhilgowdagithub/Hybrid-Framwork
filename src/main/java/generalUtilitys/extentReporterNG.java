package generalUtilitys;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporterNG {

	public static ExtentReports getEXtentReport() {

		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("web Automation results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nikhil");

		return extent;
	}

}
