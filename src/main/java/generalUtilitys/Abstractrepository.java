package generalUtilitys;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectRepository.cartPage;
import objectRepository.orderPage;


public class Abstractrepository {
	public WebDriver driver;

	public Abstractrepository(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void waitForElementToApper(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

	}

	public void waitForWebElementTodisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitForWebelementToDisapper(WebElement loction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfAllElements(loction));

	}

	public void waitTillvisibilityofallelement(WebElement location) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(location));

	}

	public cartPage cartHeader() {
		cartHeader.click();
		cartPage cartPage = new cartPage(driver);
		return cartPage;

	}

	public void javaScriptExecuter(WebElement loc) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loc);

	}

	public orderPage orderHeader() {
		orderHeader.click();
		orderPage orderPage = new orderPage(driver);
		return orderPage;

	}
}
