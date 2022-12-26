package seleniumByNikhil.Testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class GridTestCase {
	@Test
	public void seleniumGridExecution() throws MalformedURLException, SQLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setBrowserName("chrome");
		caps.setPlatform(Platform.WIN10);
		caps.setVersion("14.7");

		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.148.1:5555"), caps);
		driver.get("https://mvnrepository.com/");
		System.out.println(driver.getTitle());
		driver.close();
	}

}
