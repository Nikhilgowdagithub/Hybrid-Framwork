package seleniumByNikhil.Testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import generalUtilitys.dataDrivenFromExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class dataDrivenExcelTestcase {
	@Test
	public void instaLogin() throws IOException, InvalidFormatException {
		dataDrivenFromExcel data = new dataDrivenFromExcel();
		ArrayList<String> hh = data.getData("instaLogin");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.secure.instagram.com/accounts/login/?next=%2F__nrsalinaaaaa%2F&source=desktop_nav");
		driver.findElement(By.cssSelector("[aria-label*=Phone]")).sendKeys(hh.get(1));
		driver.findElement(By.cssSelector("[aria-label*=Password]")).sendKeys(hh.get(2));
		driver.findElement(By.cssSelector("div[class*='DhRcB'] :first-of-type")).click();

	}

}
