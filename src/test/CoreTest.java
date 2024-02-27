package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import org.testng.Assert;

public class CoreTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();

		// get the title

		System.out.println(driver.findElement(By.cssSelector("h1")).getText());

		// radio button click

		driver.findElement(By.cssSelector("input[value='radio2']")).click();

		// auto suggestive dropdown

		driver.findElement(By.cssSelector(".inputs.inputs.ui-autocomplete-input")).sendKeys("ind");
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class=ui-menu-item] div")));
		// Thread.sleep(3000);
		List<WebElement> options = driver.findElements(By.cssSelector("li[class=ui-menu-item] div"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("India"))

				option.click();

		}

		// dropdown

		driver.findElement(By.cssSelector("option[value='option2']")).click();

		// checkbox

		driver.findElement(By.id("checkBoxOption3")).click();
		Assert.assertTrue(driver.findElement(By.id("checkBoxOption3")).isSelected());

		// switch window

		driver.findElement(By.id("openwindow")).click();
		// driver.switchTo().newWindow(WindowType.TAB); //can be TAB or WINDOW for
		// WindowType

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String ParentWindowId = it.next();
		String ChildWindowId = it.next();
		driver.switchTo().window(ChildWindowId);

		System.out.println(driver.findElement(By.xpath("//span[text()='info@qaclickacademy.com']")).getText());
		driver.close();
		driver.switchTo().window(ParentWindowId);

		// switch tab

		driver.findElement(By.id("opentab")).click();
		// driver.switchTo().newWindow(WindowType.TAB); //can be TAB or WINDOW for
		// WindowType

		Set<String> handles2 = driver.getWindowHandles();
		Iterator<String> it2 = handles2.iterator();
		String ParentWindowId2 = it2.next();
		String ChildWindowId2 = it2.next();
		driver.switchTo().window(ChildWindowId2);

		System.out.println(driver.findElement(By.xpath("//span[text()='info@qaclickacademy.com']")).getText());
		driver.close();
		driver.switchTo().window(ParentWindowId2);

		// handle alert

		driver.findElement(By.id("name")).sendKeys("Fred");
		driver.findElement(By.id("alertbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

		driver.findElement(By.id("confirmbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();

		// get columns

		List<WebElement> valuesColumns = driver.findElements(By.cssSelector(".table-display tr:nth-child(3)"));
		int columnCount = 0;

		for (int i = 0; i < valuesColumns.size(); i++) {
			System.out.println(valuesColumns.get(i).getText());
			columnCount++;
			System.out.println("Column count is " + columnCount);
		}

		List<WebElement> valuesColumns2 = driver.findElements(By.cssSelector(".tableFixHead tr:nth-child(4)"));
		int columnCount2 = 0;

		for (int i = 0; i < valuesColumns2.size(); i++) {
			System.out.println(valuesColumns2.get(i).getText());
			columnCount2++;
			System.out.println("Column count is " + columnCount2);
		}

		// Get rows

		List<WebElement> valuesRows = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));

		int sum = 0;
		int rowCount = 0;

		for (int i = 0; i < valuesRows.size(); i++) {

			System.out.println(valuesRows.get(i).getText());
			sum = sum + Integer.parseInt(valuesRows.get(i).getText());
			rowCount++;

		}

		System.out.println("Sum is " + sum);
		System.out.println("Row count: " + rowCount);

		// iFrames!

		System.out.println("We have " + driver.findElements(By.tagName("iframe")).size() + " iFrame!");
		driver.switchTo().frame(0);
		System.out.println(driver.findElement(By.cssSelector(".copyright")).getText());
		driver.switchTo().defaultContent();

		// broken link

		driver.findElement(By.cssSelector("a[href*='brokenlink']")).click();
		System.out.println(driver.findElement(By.cssSelector(".max-w-xl")).getText());
		driver.navigate().back();

		// flashing green link

		driver.findElement(By.cssSelector("a[href*='documents-request']")).click();
		System.out.println(driver.findElement(By.cssSelector("a[href*='mailto']")).getText());

		// split the string on @

		String s = driver.findElement(By.cssSelector("a[href*='mailto']")).getText();
		String[] splittedString = s.toString().split("@");
		System.out.println(splittedString[0]);
		System.out.println(splittedString[1]);
		driver.navigate().back();

		// hide/show example

		driver.findElement(By.id("hide-textbox")).click();
		Assert.assertFalse(driver.findElement(By.id("displayed-text")).isDisplayed());
		driver.findElement(By.id("show-textbox")).click();
		Assert.assertTrue(driver.findElement(By.id("displayed-text")).isDisplayed());

		// mouse hover

		driver.findElement(By.id("mousehover")).click();
		driver.findElement(By.linkText("Top")).click();

		driver.close();

	}

}
