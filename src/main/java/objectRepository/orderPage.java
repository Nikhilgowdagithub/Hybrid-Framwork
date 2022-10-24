package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generalUtilitys.Abstractrepository;

public class orderPage extends Abstractrepository {

	@FindBy(css = "tbody tr td:nth-child(3)")
	List<WebElement> orderedProductList;

	public orderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	public boolean verifyingorderisDisplayed(String productname) {
		boolean match = orderedProductList.stream().anyMatch((s -> s.getText().equalsIgnoreCase(productname)));
		return match;

	}

}
