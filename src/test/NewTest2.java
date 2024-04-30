package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewTest2 {
  @Test
  public void f() {
		WebDriver driver = new ChromeDriver();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofMillis(5000));

		driver.manage().window().maximize();

		driver.get("https://jqueryui.com/droppable");
		System.out.println(driver.findElements(By.tagName("iframe")).size());
		driver.findElement(By.xpath("//a[normalize-space()='Revert draggable position']")).click();
		driver.switchTo().frame(0);
		Actions a = new Actions(driver);
		//WebElement source = driver.findElement(By.id("draggable"));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable2")));
		WebElement source2 = driver.findElement(By.id("draggable2"));
		WebElement target = driver.findElement(By.id("droppable"));
		//a.dragAndDrop(source, target).build().perform();
		a.dragAndDrop(source2, target).build().perform();
		driver.switchTo().defaultContent();
		driver.quit();
  }
}
