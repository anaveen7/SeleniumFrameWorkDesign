package interviewQuetions.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GettingDropDownValueswithoutSelect {
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		String expectedText="Option2";
		List<String> dropDownValues = new ArrayList<>();;
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		WebElement dropdown=driver.findElement(By.xpath("//select[@id='dropdown-class-example']"));
		List<WebElement> options=dropdown.findElements(By.tagName("option"));
		
		for(WebElement option:options) {
			String originalText=option.getText();
			dropDownValues.add(originalText);
			if(originalText.equalsIgnoreCase(expectedText)) {
				System.out.println("Expected text contains in drop down");
			}
		}
		
		if(dropDownValues.contains(expectedText)) {
			System.out.println("Expected text contains in drop down");
		}
		else {
			System.out.println("Expected text not contains in drop down");
		}
		System.out.println(dropDownValues);
		driver.close();
	}
}
