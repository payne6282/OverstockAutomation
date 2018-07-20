package com.SeleniumOverstock;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AModCanada {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 60);
@Test
public void FirefoxTest() throws InterruptedException {
	String AMod1;
	String AMod2;
	String AMod3;
	String AMod4;
	System.setProperty("webdriver.chrome.driver", "/home/sachinkayath/workspaceLuna/Selenium/chromedriver");

	driver.get("http://www.overstock.com");
	driver.manage().window().maximize();
	Thread.sleep(3000);
		if(!driver.findElements(By.xpath(".//*[@id='oboxInitial']")).isEmpty()) {
		driver.findElement(By.xpath(".//*[@id='cboxClose']")).click();
	}
		//Changing the country
		WebElement element = driver.findElement(By.xpath(".//*[@id='ft']/div[1]/div[4]/div/div/div[1]/ul/li/a[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);

		driver.findElement(By.xpath(".//*[@id='ft']/div[1]/div[4]/div/div/div[1]/ul/li/a[2]")).click();
		driver.findElement(By.xpath(".//*[@id='bd']/div[2]/div[1]/div[2]/div[1]/ul/li[13]/a")).click();
		Thread.sleep(3000);

		//A Mods
		driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[2]/ul/li[1]")).click();
		AMod1 = driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[1]/div/div/div[1]/a")).getAttribute("href");
		driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[2]/ul/li[2]")).click();
		AMod2 = driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[1]/div/div/div[2]/a")).getAttribute("href");
		driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[2]/ul/li[3]")).click();
		AMod3 = driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[1]/div/div/div[3]/a")).getAttribute("href");
		driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[2]/ul/li[4]")).click();
		AMod4 = driver.findElement(By.xpath(".//*[@id='bd']/div/div[2]/div[1]/div/div/div[4]/a")).getAttribute("href");
		AModCanada AModC = new AModCanada();
		AModC.openTabA1(AMod1);
		AModC.openTabA2(AMod2);
		AModC.openTabA3(AMod3);
		AModC.openTabA4(AMod4);
		//AMod1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div/div[1]/div[1]/div/div[0]/a/img"))).getAttribute("href");
		//driver.quit();
}

private void openTabA1(String A1Mod) {
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
	driver.manage().window().maximize();
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    driver.get(A1Mod);
    driver.switchTo().window(tabs.get(0));

}

private void openTabA2(String A2Mod) {
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(2));
    driver.get(A2Mod);
    driver.switchTo().window(tabs.get(0));

}

private void openTabA3(String A3Mod) {
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(3));
    driver.get(A3Mod);
    driver.switchTo().window(tabs.get(0));

}

private void openTabA4(String A4Mod) {
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(4));
    driver.get(A4Mod);
    driver.switchTo().window(tabs.get(0));

}
}
