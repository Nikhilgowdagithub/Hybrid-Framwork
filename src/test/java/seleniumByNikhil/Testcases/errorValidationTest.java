package seleniumByNikhil.Testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generalUtilitys.Baseclass;
import objectRepository.Landingpage;
import objectRepository.Productcatalog;
import objectRepository.cartPage;

public class errorValidationTest extends Baseclass {
	String error = "Incorrect email or password.";
	String productname = "ZARA COAT 33";

	@Test
	public void loginpageerrorvalidation() {
		landingPage = new Landingpage(driver);
		landingPage.loginapplication("nikhilgowda111@gmail.com", "Nikhilgowda11@");
		landingPage.errorvalidation();
		assertEquals(error, landingPage.errorvalidation());
	}

	@Test(groups = { "errorhandaling" }, dataProvider = "getData")
	public void addtocarterrorvalidation(HashMap<String, String> input) {
		Landingpage landingPage = new Landingpage(driver);
		Productcatalog productcatalogue = landingPage.loginapplication(input.get("emailid"), input.get("password"));
		productcatalogue.getProductList();
		cartPage cartPage = productcatalogue.cartHeader();
		boolean match = cartPage.verifyingproductisDisplayed(input.get(productname));
		assertFalse(match);
	}

	@DataProvider
	public Object[][] getData() {

		HashMap<String, String> map = new HashMap<String, String>();


		map.put("emailid", "nikhilgowda11@gmail.com");
		map.put("password", "Nikhilgowda11@");
		map.put(productname, "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("emailid", "nikhilgowda111@gmail.com");
		map1.put("password", "Nikhilgowda111@");
		map1.put(productname, "IPHONE 13 PRO");

		return new Object[][] { { map }, { map1 } };

	}

}