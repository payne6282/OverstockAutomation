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


public class TestingStuff {

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

		for(int  counter = 0; counter < xpathURL.size(); counter++)
		{
			String xpath1 = ".//*[@id='amodCarousel']/div[" + (count);
			String xpath2 = "]/div/a";

			/*if (count > 1) {
				xpath2 = "]/a";
			} else
			{
				xpath2 = "]/div/a";
			}*/

			String wholeURL = xpath1 + xpath2;
			count = count + 1;
			System.out.println("The whole url" + wholeURL);
			Thread.sleep(8000);
			Amod.add(driver.findElement(By.xpath(wholeURL)).getAttribute("href"));
			System.out.println("Amod is " +Amod);
		}

		System.out.println("Amod size" +Amod.size());
		NewHomePageBModUS AmodStuff = new NewHomePageBModUS();

		for(int counter = 0; counter < Amod.size(); counter++) {
			AmodStuff.openBMod(Amod.get(counter), counter);
		}

		AmodStuff.checkUrlsExcel(Amod, excelFilePath);
	}


}