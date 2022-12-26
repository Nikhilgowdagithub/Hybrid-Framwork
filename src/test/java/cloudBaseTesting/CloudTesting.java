package cloudBaseTesting;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CloudTesting {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		String username = "oauth-nikhilgowda013-f44d9";
		String Accesskey = "c305e37b-0bb2-4df6-abbc-7f6a6930b473";

		SafariOptions browserOptions = new SafariOptions();
		browserOptions.setPlatformName("macOS 11.00");
		browserOptions.setBrowserVersion("14");
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("build", "<your build id>");
		sauceOptions.put("name", "<your test name>");
		browserOptions.setCapability("sauce:options", sauceOptions);

		URL url = new URL("https://" + username + ":" + Accesskey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);

		String name = "rahul";
		String password = "rahulshettyacademy";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/locatorspractice/");

		driver.findElement(By.id("inputUsername")).sendKeys(name);

		driver.findElement(By.name("inputPassword")).sendKeys(password);

		driver.findElement(By.className("signInBtn")).click();

		Thread.sleep(2000);

		System.out.println(driver.findElement(By.tagName("p")).getText());

		assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");

		assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(),
				"Hello " + name + ",");

		driver.findElement(By.xpath("//*[text()='Log Out']")).click();
		System.out.println(driver.getTitle());
		driver.close();
	}

}
