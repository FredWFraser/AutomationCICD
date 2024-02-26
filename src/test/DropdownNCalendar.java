package test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DropdownNCalendar {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		WebDriverWait w = new WebDriverWait(driver, Duration.ofMillis(3000));

		driver.get("https://rahulshettyacademy.com/dropdownsPractise/#");
		driver.manage().window().maximize();

		// radio button

		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

		// from and to dropdowns

		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.cssSelector("a[value='BLR']")).click();
		driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		driver.findElement(By.cssSelector("a[value='MAA']")).click();

		// depart and return date

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@href='#'][normalize-space()='27'])[1]")));
		driver.findElement(By.xpath("(//a[@href='#'][normalize-space()='27'])[1]")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.xpath("(//a[normalize-space()='16'])[1]")).click();

		// passengers

		driver.findElement(By.cssSelector(".paxinfo")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("hrefIncAdt")));
		driver.findElement(By.id("hrefIncAdt")).click();
		driver.findElement(By.id("btnclosepaxoption")).click();

		// currency - static drowdown

		WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(3); // USD

		// senior citizen checkbox

		driver.findElement(By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		// auto suggestive dropdown

		driver.findElement(By.id("autosuggest")).sendKeys("ind");
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class='ui-menu-item'] a")));
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		
		for (WebElement option : options) {
			
			
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}
		
		// search
		
		driver.findElement(By.name("ctl00$mainContent$btn_FindFlights")).click();
		
		driver.close();

	}

}
