package objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generalUtilitys.Abstractrepository;

public class paymentsPage extends Abstractrepository {

	public paymentsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(css = "[class*='list-group']")
	List<WebElement> autosuggest;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeorderclick;

	public String searchCountry(String countryname) {
		selectCountry.sendKeys(countryname);
		return countryname;

	}

	public WebElement selectCountry(String autosuggestCountryselect) {

		WebElement indiaAutoSuggest = autosuggest.stream()
				.filter(s -> s.findElement(By.cssSelector("[class='ng-star-inserted']")).getText()
						.equalsIgnoreCase(autosuggestCountryselect))
				.findFirst().orElse(null);
		return indiaAutoSuggest;

	}

	public WebElement clickingonselectedcountry(String autosuggestCountrySelect) {
		waitTillvisibilityofallelement(selectCountry);
		WebElement loc = selectCountry(autosuggestCountrySelect)
				.findElement(By.cssSelector("[class='ng-star-inserted']"));
		javaScriptExecuter(loc);
		return loc;

	}

	public checkoutpage placeorderclick() {
		javaScriptExecuter(placeorderclick);
		return new checkoutpage(driver);

	}
}
