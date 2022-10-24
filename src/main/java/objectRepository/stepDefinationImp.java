package objectRepository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import generalUtilitys.Baseclass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImp extends Baseclass {


	public Landingpage landingPage;
	public Productcatalog productcatalogue;
	public checkoutpage checkoutpage;
	public cartPage cartPage;

	@Given("i landed on ecommers page")

	public void i_Landed_on_Ecommers_Page() throws IOException {
		landingPage = lanchingBrowser();

	}

	@Given("^login with username (.+) and password (.+)")
	public void login_With_Username_And_password(String username, String password) {

		productcatalogue = landingPage.loginapplication(username, password);

	}

	@When("^i add product to cart(.+)$")
	public void i_Add_Product_To_Cart(String productName) {
		productcatalogue.getProductList();
		productcatalogue.addProducttoCart(productName);

	}

	@And("^checkout Submit Order of product(.+) country(.+)suggested(.+)$")
	public void checkout_Submit_Order(String productName, String countryName, String autosuggestCountryselect) {
		cartPage = productcatalogue.cartHeader();

		boolean match = cartPage.verifyingproductisDisplayed(productName);
		assertTrue(match);
		paymentsPage paymentsPage = cartPage.checkout();
		paymentsPage.searchCountry(countryName);
		paymentsPage.selectCountry(autosuggestCountryselect);

		paymentsPage.clickingonselectedcountry(autosuggestCountryselect);
		checkoutpage = paymentsPage.placeorderclick();

	}

	@Then("{string} message is displayed on confirmation page")

	public void message_is_Displayed_on_Confirmation_Page(String confirmationMessage) {
		checkoutpage.validatingPage();
		assertTrue(checkoutpage.validatingPage().contains(confirmationMessage));
		driver.close();

	}

	@Then("{string} error message is displayed")
	public void something_error_message_is_displayed(String message) throws Throwable {

		landingPage.errorvalidation();
		assertEquals(message, landingPage.errorvalidation());
		driver.close();
	}

}
