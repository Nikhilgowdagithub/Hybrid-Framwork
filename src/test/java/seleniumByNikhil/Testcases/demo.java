
package seleniumByNikhil.Testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v100.systeminfo.model.Size;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demo {
	public WebDriver driver;

	@Test(dataProvider = "getData")
	public void main(HashMap<String, String> dat) throws IOException, AWTException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Set<String> wind = driver.getWindowHandles();
		Iterator<String> it = wind.iterator();
		String first = it.next();
		String secnd = it.next();
		System.out.println(first);
		System.out.println(secnd);
		driver.switchTo().window(first);
		Actions ac = new Actions(driver);
		Robot rb = new Robot();
		WebElement ele = driver.findElement(By.id("email"));
		ele.sendKeys(dat.get("pass"));
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_A);
		WebElement ele1 = driver.findElement(By.id("pass"));
		ac.moveToElement(ele1).click().build().perform();
		rb.keyPress(KeyEvent.VK_V);
		System.out.println("bye");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		int size = driver.findElements(By.id("pageFooter")).size();
		System.out.println(size);
		List<WebElement> eleli = driver.findElements(By.xpath("(//div[@id='pageFooter'])[1]/ul/li/a"));
		for (int i = 0; i < eleli.size(); i++) {

			WebElement add = eleli.get(i);
			System.out.println(add.getText());
			if (add.getAttribute("title").equalsIgnoreCase("Show more languages")) {

				// add.click(); //
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("u_3_0_Uo"))));
				Thread.sleep(10000);
			}

		}

		List<WebElement> lang = driver.findElements(By.cssSelector("ul.uiList.mrm.regionList li"));
		Thread.sleep(10000);
		for (int i = 0; i < lang.size(); i++) {
			System.out.println(lang.get(i).getText());

		}

		List<WebElement> langvage = driver.findElements(By.xpath("(//tr[@class='_51mx'])[2]/td/ul/li/div/a"));
		Thread.sleep(10000);
		System.out.println(langvage.size());
		for (int i = 0; i < langvage.size(); i++) {

			System.out.println(langvage.get(i).getText());

		}
		driver.findElement(By.cssSelector("a[class*='uiOverlayButton ']")).click();

		List<WebElement> fh = driver.findElements(By.xpath("//div[@id='pageFooterChildren']/ul/li"));
		wait.until(ExpectedConditions.visibilityOfAllElements(fh));
		System.out.println(fh.size());

		driver.quit();

		ele.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));

		screenshot(driver);
		driver.quit();
		driver.switchTo().frame(ele);
		driver.switchTo().defaultContent();
		Select sc = new Select(ele);
		System.out.println(sc.getOptions());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("ScroolToElement", new Size(1400, 900));

		driver.manage().window().setSize(new org.openqa.selenium.Dimension(1400, 900));

		Properties pr = new Properties();
		FileInputStream data = new FileInputStream(new File(""));
		pr.load(data);

		pr.get("");

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> datajso = name();

		return new Object[][] { { datajso.get(0) } };
	}

	public static List<HashMap<String, String>> name() throws IOException {

		String jsonString = FileUtils.readFileToString(new File(".//demo.json"));

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public static void screenshot(WebDriver driver) throws IOException {

		File source = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source, new File("sc//Screenshot.png"));

	}

}
