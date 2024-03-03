package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match=productNames.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		JSExecutorClick(checkOutEle);
		return new CheckoutPage(driver);
	}
	
	

}
