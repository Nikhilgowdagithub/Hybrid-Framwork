package seleniumByNikhil.Testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generalUtilitys.Baseclass;
import generalUtilitys.retryMechnisam;
import objectRepository.Landingpage;
import objectRepository.Productcatalog;
import objectRepository.cartPage;
import objectRepository.checkoutpage;
import objectRepository.orderPage;
import objectRepository.paymentsPage;

public class endToEndTestcase extends Baseclass {
	

	@Test(groups = { "purchase" }, dataProvider = "getData", retryAnalyzer = retryMechnisam.class)
	public void productorder(HashMap<String, String> input) throws IOException, InterruptedException {

		Landingpage landingPage = new Landingpage(driver);
		Productcatalog productcatalogue = landingPage.loginapplication(input.get("emailid"), input.get("password"));

		productcatalogue.getProductList();
		productcatalogue.addProducttoCart(input.get("product"));
		cartPage cartPage = productcatalogue.cartHeader();

		boolean match = cartPage.verifyingproductisDisplayed(input.get("product"));
		assertTrue(match);
		paymentsPage paymentsPage = cartPage.checkout();
		paymentsPage.searchCountry(input.get("countryName"));
		paymentsPage.selectCountry(input.get("autosuggestCountryselect"));

		paymentsPage.clickingonselectedcountry(input.get("autosuggestCountryselect"));
		checkoutpage checkoutpage = paymentsPage.placeorderclick();
		checkoutpage.validatingPage();
		assertTrue(checkoutpage.validatingPage().contains(input.get("orderconfirmationmessage")));

	}

	@Test(dependsOnMethods = { "productorder" })
	public void orderhistry() {
		String productname = "ZARA COAT 3";
		Landingpage landingPage = new Landingpage(driver);
		Productcatalog productcatalogue = landingPage.loginapplication("nikhilgowda11@gmail.com", "Nikhilgowda11@");
		orderPage orderPage = productcatalogue.orderHeader();
		assertTrue(orderPage.verifyingorderisDisplayed(productname));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsanDataToMap(
				System.getProperty("user.dir") + "//src//test//resources//purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}



}
