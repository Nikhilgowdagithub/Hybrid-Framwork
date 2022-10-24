package seleniumByNikhil.Testcases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Endtoendframework {
	@Test(dataProvider = "getData")
	public  void endtoendprocess(String emailid,String password,String productname) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys(emailid);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		List<WebElement> list = driver.findElements(By.cssSelector("[class*='col-sm-10']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='col-sm-10']")));

		WebElement product = list.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst()
				.orElse(null);

		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));


		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

		List<WebElement> productsAddedToCart = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = productsAddedToCart.stream().anyMatch((s -> s.getText().equalsIgnoreCase(productname)));
		assertTrue(match);

		driver.findElement(By.cssSelector("ul button[type='button']")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("in");

		List<WebElement> autoSuggest = driver.findElements(By.cssSelector("[class*='list-group']"));
		WebElement indiaAutoSuggest = autoSuggest.stream().filter(
				s -> s.findElement(By.cssSelector("[class='ng-star-inserted']")).getText().equalsIgnoreCase("india"))
				.findFirst().orElse(null);
		wait.until(ExpectedConditions.visibilityOfAllElements(indiaAutoSuggest));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement loc = indiaAutoSuggest.findElement(By.cssSelector("[class='ng-star-inserted']"));
		js.executeScript("arguments[0].click();", loc);

		WebElement placeorder = driver.findElement(By.xpath("//a[text()='Place Order ']"));
		js.executeScript("arguments[0].click();", placeorder);

	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"nikhilgowda11@gmail.com", "Nikhilgowda11@","ZARA COAT 3"},{"nikhilgowda111@gmail.com", "Nikhilgowda111@","IPHONE 13 PRO"}};

	}

}
