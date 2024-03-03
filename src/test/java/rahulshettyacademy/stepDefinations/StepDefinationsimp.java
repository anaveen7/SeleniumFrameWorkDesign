package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinationsimp extends BaseTest{
	
	LandingPage landingpage;
	ProductCatalogue productcatalogue;
	ConfirmationPage confirmPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingpage = launchApplication();
	}
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password) {
		productcatalogue=landingpage.loginApplication(username,password);
	}
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productname) {
		List<WebElement> products=productcatalogue.getProductsList();
		System.out.println("Test Product "+productname);
		productcatalogue.addProductToCart(productname);
	}
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productname) {
        CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match=cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckoutPage checkout=cartpage.goToCheckout();
		checkout.selectCpuntry("India");
		confirmPage=checkout.submitOrder();
	}
	@Then ("{string} message is displayed on confirmation page")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage=confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.contentEquals("THANKYOU FOR THE ORDER."));
		driver.close();
	}
	@Then ("{string} message is displayed")
	public void message_displayed(String string) {
		Assert.assertEquals(string,landingpage.getErrorMessage());
		driver.close();
	}
}
