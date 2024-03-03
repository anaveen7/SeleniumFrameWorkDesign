package interviewQuetions.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class irctcDropDown {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		driver.get("https://www.irctc.co.in/nget/train-search");		
		driver.findElement(By.cssSelector("p-dropdown[id='journeyQuota']")).click();
		List<WebElement> JourneyQuota=driver.findElements(By.cssSelector("li[role='option']"));
		for(WebElement quota:JourneyQuota) {
			if(quota.getText().equalsIgnoreCase("PREMIUM TATKAL")) {
				quota.click();
				System.out.println("Premium tatkal is avaialble");
			}
		}
		Thread.sleep(5000);
		driver.quit();
	}

}
