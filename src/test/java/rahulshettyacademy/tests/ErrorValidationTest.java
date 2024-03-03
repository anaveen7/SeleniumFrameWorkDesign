package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{

	
	
	@Test(priority=1,groups="ErrorValidation")
	public void produtValidaion() throws IOException, InterruptedException{
		
		String productName="ZARA COAT 3";			
		ProductCatalogue productcatalogue=landingpage.loginApplication("naveenpractice7@gmail.com", "Test@1234");
		List<WebElement> products=productcatalogue.getProductsList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match=cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkout= new CheckoutPage(driver);
		checkout.clickOnSignOut();
	}
	
	@Test(groups={"ErrorValidation"},retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		landingpage.loginApplication("anaveenpractice7@gmail.com", "Test@1234");
		Assert.assertEquals("Incorrect email  password.",landingpage.getErrorMessage());
	}

}
