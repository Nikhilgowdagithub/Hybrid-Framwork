package objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generalUtilitys.Abstractrepository;

public class Productcatalog extends Abstractrepository {

	public Productcatalog(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[class*='col-sm-10']")
	List<WebElement> product;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {
		waitForElementToApper(By.cssSelector("[class*='col-sm-10']"));
		return product;

	}

	public WebElement getProductbyName(String productName) {
		WebElement product = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		return product;

	}

	public void addProducttoCart(String productname) {
		getProductbyName(productname).findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToApper(By.cssSelector("#toast-container"));
		waitForWebelementToDisapper(spinner);
	}
}
