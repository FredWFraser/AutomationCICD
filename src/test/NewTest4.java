package test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewTest4 {
	@Test
	public void f() {

		String[] officialProductList = { "Brocolli", "Tomato", "Onion", "Mango" };

		WebDriver driver = new ChromeDriver();

		WebDriverWait w = new WebDriverWait(driver, Duration.ofMillis(3000));

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().window().maximize();
		addItems(driver, officialProductList);
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.cssSelector("div[class='action-block'] button")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));
		driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector(".promoBtn")).click();
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		driver.findElement(By.cssSelector("option[value='India']")).click();
		driver.findElement(By.cssSelector(".chkAgree")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();

	    driver.close();

	}

	public static void addItems(WebDriver driver, String[] officialProductList) {

		List<WebElement> productsOnWebsite = driver.findElements(By.cssSelector("h4[class='product-name']"));
		int j = 0;

		for (int i = 0; i < productsOnWebsite.size(); i++) {

			System.out.println(productsOnWebsite.get(i).getText()); // Brocolli - 1 Kg

			String[] splitProductName = productsOnWebsite.get(i).getText().split("-");
			String formattedProductName = splitProductName[0].trim();

			System.out.println(formattedProductName); // Brocolli

			List officialProductsAsList = Arrays.asList(officialProductList);

			if (officialProductsAsList.contains(formattedProductName)) {

				j++;

				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

				if (j == officialProductList.length) {

					break;

				}

			}

		}
	}
}
