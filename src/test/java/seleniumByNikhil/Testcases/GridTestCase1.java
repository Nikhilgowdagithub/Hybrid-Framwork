package seleniumByNikhil.Testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class GridTestCase1 {
	DesiredCapabilities caps = new DesiredCapabilities();

	@Test
	public void seleniumGridTest() throws MalformedURLException {

		caps.setCapability(CapabilityType.BROWSER_NAME, "edge");

		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.102:4444"), caps);
		driver.get("https://www.youtube.com/");
		System.out.println(driver.getTitle());
		driver.close();

	}
}