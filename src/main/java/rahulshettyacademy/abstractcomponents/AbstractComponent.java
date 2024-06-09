package rahulshettyacademy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordertHeader;

	public void waitForElementToAppear(By findby) {
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	public void waitForWebElementElementToAppear(WebElement findby) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
		}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void JSExecutorClick(WebElement ele) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
	}
	public String JSExecutorGetText(WebElement ele) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		 String text = (String) js.executeScript("return arguments[0].text", ele);
		 return text;
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartpage= new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrdersPage() {
		ordertHeader.click();
		OrderPage orderspage= new OrderPage(driver);
		return orderspage;
	}
}
