package com.SeleniumOverstock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import org.testng.reporters.Files;




public class CheckXsymbol {
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 60);

	@Test
	public void FirefoxTest() throws InterruptedException, IOException {


		System.setProperty("webdriver.chrome.driver", "/home/sachinkayath/workspaceLuna/Selenium/chromedriver");
		driver.manage().window().maximize();
		driver.get("https://www.overstock.com/sitetests");
		driver.manage().window().maximize();
		driver.findElement(By.xpath(".//*[@id='SITETEST_FOOTER_ADS_FOOTER_ADS_YES']")).click();
		driver.findElement(By.xpath("html/body/div[3]/div/form/input")).click();
		driver.findElement(By.xpath(".//*[@id='secureLogo']/a")).click();



		Thread.sleep(6000);

		if(!driver.findElements(By.xpath(".//*[@id='cb_lightbox']")).isEmpty()) {
			System.out.println("The LIGHTBOX IS FOUND!");
			driver.findElement(By.xpath(".//*[@id='cb_lightbox']/div[1]/div")).click();
		}

		for (int i = 0; i < 50; i++) {
			long startTime = System.currentTimeMillis();
			driver.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='amodCarousel']/div[1]/a")));
			long loadTime = System.currentTimeMillis();
			long time = loadTime - startTime;

			if (time > 5500) {
				System.out.println("Load time greater at " + i + " attempt " + "with time " +time );
			}


		}

	}
}
