package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generalUtilitys.Abstractrepository;



public class cartPage extends Abstractrepository {
	
	@FindBy(css = ".cart h3")
	List<WebElement> productitles;
	
	@FindBy(css = "ul button[type='button']")
	WebElement checkout;

	public cartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}
	public boolean verifyingproductisDisplayed(String productname) {
		boolean match = productitles.stream().anyMatch((s -> s.getText().equalsIgnoreCase(productname)));
		return match;

	}

	public paymentsPage checkout() {
		checkout.click();
		paymentsPage paymentsPage = new paymentsPage(driver);
		return paymentsPage;

	}

}
