package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	public WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[4]")
	WebElement Signout;
	
public void selectCpuntry(String Country) {
	
	   Actions a= new Actions(driver);
	   a.sendKeys(country,Country).build().perform();
	   waitForElementToAppear(By.cssSelector(".ta-results"));
	   selectCountry.click();	
}

public ConfirmationPage submitOrder() {
	JSExecutorClick(submit);
	return new ConfirmationPage(driver);
}

public void clickOnSignOut()
{
	Signout.click();
}

}
