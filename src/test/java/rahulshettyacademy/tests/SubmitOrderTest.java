package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	String productName="ZARA COAT 3";	
	
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException{
		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products=productcatalogue.getProductsList();
		productcatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage=productcatalogue.goToCartPage();
		
		Boolean match=cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkout=cartpage.goToCheckout();
		checkout.selectCountry("India");
		ConfirmationPage confirmPage=checkout.submitOrder();
		
		String confirmMessage=confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.contentEquals("THANKYOU FOR THE ORDER."));
		//checkout.clickOnSignOut();
		
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue=landingpage.loginApplication("naveenpractice7@gmail.com", "Test@1234");
		OrderPage orderspage=productcatalogue.goToOrdersPage();
		orderspage.verifyProductDisplay(productName);
		//tt8526884@gmail.com

	}
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data= getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\purchaseorder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"naveenpractice7@gmail.com","Test@1234","ZARA COAT 3"},{"tt8526884@gmail.com","Test@1234","ADIDAS ORIGINAL"}};
//	}
//	@DataProvider
//	public Object[][] getData(){
//		HashMap<String,String> map= new HashMap<String,String>();
//		map.put("email", "naveenpractice7@gmail.com");
//		map.put("password","Test@1234");
//		map.put("productName","ZARA COAT 3");
//		
//		HashMap<String,String> map1= new HashMap<String,String>();
//		map1.put("email", "tt8526884@gmail.com");
//		map1.put("password","Test@1234");
//		map1.put("productName","ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map1}};
//	}

}
