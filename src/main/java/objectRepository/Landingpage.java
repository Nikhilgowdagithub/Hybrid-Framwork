package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generalUtilitys.Abstractrepository;

public class Landingpage extends Abstractrepository {

	public Landingpage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement mailid;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginSubmit;

	@FindBy(css = "[class*='trigger']")
	WebElement errormessage;

	public void url() {

		driver.get("https://rahulshettyacademy.com/client/");

	}

	public Productcatalog loginapplication(String mail, String passw) {
		mailid.sendKeys(mail);
		password.sendKeys(passw);
		loginSubmit.click();
		Productcatalog productCatalogue = new Productcatalog(driver);
		return productCatalogue;
	}

	public String errorvalidation() {
		return errormessage.getText();

	}
}
