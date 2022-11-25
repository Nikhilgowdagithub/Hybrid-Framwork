package generalUtilitys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.Landingpage;

public class Baseclass {
	public WebDriver driver;
	public Landingpage landingPage;
	
	@BeforeMethod(alwaysRun = true)
	public Landingpage lanchingBrowser() throws IOException {
		driver = initilizeDriver();
		 landingPage = new Landingpage(driver);
		landingPage.url();
		return landingPage;

	}

	public WebDriver initilizeDriver() throws IOException {

		Properties property = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//globaldata.properties");
		property.load(fis);
		
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: property.getProperty("browser");
		
		if (browser.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			if (browser.contains("headless")) {

				options.addArguments("headless");

			}

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1400, 900));
		}

		else if (browser.contains("firefox")) {

			FirefoxOptions options = new FirefoxOptions();
			if (browser.contains("headless")) {

				options.addArguments("headless");

			}

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1400, 900));

		}

		else if (browser.contains("edge")) {

			EdgeOptions options = new EdgeOptions();
			if (browser.contains("headless")) {

				options.addArguments("headless");

			}

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().setSize(new Dimension(1400, 900));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;

	}

	

	public List<HashMap<String, String>> getJsanDataToMap(String filepath) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenShots(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@AfterMethod(alwaysRun = true)
	public void closingtheBrowser() {
		driver.quit();
	}

}
