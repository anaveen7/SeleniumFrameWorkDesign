package interviewQuetions.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LowestPriceProductAddToCart {

	public static void main(String[] args) {
		ArrayList<Integer> priceList = new ArrayList<Integer>();
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("naveenpractice7@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		List<WebElement> prices=driver.findElements(By.xpath("//div[@class='text-muted']"));
		for(WebElement priceText:prices) {
			String productPriceText= priceText.getText();
			String[] priceSplit=productPriceText.split("\\s");
			String priceArr=priceSplit[1];
			int price=Integer.parseInt(priceArr);
			priceList.add(price);
		}
		System.out.println(priceList);
		
//		getText
//		split 
//		convert to int
//		add to array lisy sort
		int maxPrice=Collections.max(priceList);
		System.out.println(maxPrice);
		String priceToStr=String.valueOf(maxPrice);
		System.out.println(priceToStr);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
		System.out.println(driver.findElement(By.xpath("(//div[@class='text-muted'])[3]")).getText()
				.split("\\s")[1].equalsIgnoreCase(priceToStr));
		List<WebElement> products= driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector(".text-muted"))
				.getText().split("\\s")[1].equalsIgnoreCase(priceToStr)).findFirst().orElse(null);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Add To Cart']")));
		WebElement element=prod.findElement(By.cssSelector(".card-body button:last-of-type"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",element);
		
	}

}
