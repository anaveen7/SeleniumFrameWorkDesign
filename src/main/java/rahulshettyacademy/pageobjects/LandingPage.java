package rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	//class="ng-tns-c4-19 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error"
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;		
			
	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productcatalogue= new ProductCatalogue(driver);
		return productcatalogue;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public String getErrorMessage() {
		waitForWebElementElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
