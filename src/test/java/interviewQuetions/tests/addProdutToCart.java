package interviewQuetions.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class addProdutToCart {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("naveenpractice7@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
		List<WebElement> products= driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase("LAPTOP")).findFirst().orElse(null);	
//		for(WebElement prod:products) {
//			if(prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("LAPTOP")) {
//				WebElement element=prod.findElement(By.cssSelector(".card-body button:last-of-type"));
//				JavascriptExecutor js= (JavascriptExecutor)driver;
//			 js.executeScript("arguments[0].click()",element);
//			}
//		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Add To Cart']")));
		WebElement element=prod.findElement(By.cssSelector(".card-body button:last-of-type"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",element);
		
	}

}
