package com.SeleniumOverstock;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
public class CheckLinkClickable {


	//global variables
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		ArrayList<String> AUrls = new ArrayList<String>(); //A mod URLS
		ArrayList<String> Amod = new ArrayList<String>();  //Amod Coop part
		String excelFilePath = "URLsToCheckAMod.xlsx";
		int count = 1;

		@Test
		public void FirefoxTest() throws InterruptedException, IOException {


			System.setProperty("webdriver.chrome.driver", "/home/sachinkayath/workspaceLuna/Selenium/chromedriver");
			driver.manage().window().maximize();
			driver.get("https://www.overstock.com");
			driver.manage().window().maximize();

			Thread.sleep(6000);

			if(!driver.findElements(By.xpath(".//*[@id='cb_lightbox']")).isEmpty()) {
				System.out.println("The LIGHTBOX IS FOUND!");
				driver.findElement(By.xpath(".//*[@id='cb_lightbox']/div[1]/div")).click();
			}
			Thread.sleep(3000);

			List<WebElement> xpathURL = driver.findElements(By.className("hp-amod-dot"));

			System.out.println("The size of the class is" + xpathURL.size());

			//checking if the A mod link is accessible

			String Amod1 = ".//*[@id='amodCarousel']/div[1]/div/a[1]";
			driver.findElement(By.xpath(Amod1)).click();
			Thread.sleep(6000);

			if(!driver.findElements(By.xpath(".//*[@id='amodCarousel']/div[1]")).isEmpty()) {
				System.out.println("The link is not clickable");
			} else {
				System.out.print("We went to the other link");
			}

		}
}



