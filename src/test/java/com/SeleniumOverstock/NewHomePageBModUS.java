package com.SeleniumOverstock;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class NewHomePageBModUS {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 60);
	ArrayList<String> BUrls = new ArrayList<String>();
	ArrayList<String> Bmod = new ArrayList<String>();
	String excelFilePath = "UrlsToCheck.xlsx";
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

		if(!driver.findElements(By.xpath(".//*[@id='home-body']/div[9]/div[2]/div[1]")).isEmpty()) {
			driver.findElement(By.xpath(".//*[@id='home-body']/div[9]/div[2]/div[1]/div[1]")).click();
		}

		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(".//*[@id='bd']/div[8]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);

		/*List<WebElement> hpmods = new ArrayList<WebElement>();
		hpmods = driver.findElements(By.id("hp-bmods"));
		for (int bmodcheck = 0; bmodcheck < hpmods.size(); bmodcheck ++) {
			Bmod.add(hpmods.get(bmodcheck).getAttribute("href"));
		}*/

		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div[10]/div/div[2]/a"))).getAttribute("href"));
		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div[10]/div/div[3]/a"))).getAttribute("href"));
		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div[10]/div/div[4]/a"))).getAttribute("href"));
		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div[10]/div/div[5]/a"))).getAttribute("href"));


		//Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.className("slim-padding-column col-sm-3 col-xs-6"))).getAttribute("href"));

		NewHomePageBModUS BModTest = new NewHomePageBModUS();
		for(int counter=0; counter<Bmod.size(); counter++) {
			BModTest.openBMod(Bmod.get(counter), counter);
		}

		BModTest.checkUrlsExcel(Bmod, excelFilePath);

		//driver.quit();
	}

	public void openBMod(String BMod, int counter) {

		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.manage().window().maximize();
		driver.switchTo().window(tabs.get(counter+1));
		driver.get(BMod);
		driver.switchTo().window(tabs.get(0));
	}

	public void checkUrlsExcel(ArrayList<String> BmodPassed, String excelFilePath) throws IOException {
		int sizer;

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();


		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print("The cell values are-" +cell.getStringCellValue());
					BUrls.add(cell.getStringCellValue());
					System.out.println();
					break;
				}

			}
		}

		workbook.close();
		inputStream.close();

		//Checking the urls against the sheet
		ArrayList<String> originalUrl = new ArrayList<String>();
		ArrayList<String> coOp = new ArrayList<String>();

		for(sizer = 0; sizer < BmodPassed.size(); sizer++) {


			if (BmodPassed.get(sizer).contains("?tid")) {
				originalUrl.add((BmodPassed.get(sizer)).substring(0, BmodPassed.get(sizer).lastIndexOf("?")));
				coOp.add((BmodPassed.get(sizer)).substring(BmodPassed.get(sizer).lastIndexOf(":") + 1));
			} else if
			(BmodPassed.get(sizer).contains("&tid")) {
				originalUrl.add((BmodPassed.get(sizer)).substring(0, BmodPassed.get(sizer).lastIndexOf("&")));
				coOp.add((BmodPassed.get(sizer)).substring(BmodPassed.get(sizer).lastIndexOf(":") + 1));
			}
			System.out.println("Bmod links are-" +originalUrl.get(sizer));
			System.out.println("Coop links are-" +coOp.get(sizer));
		}

		//checking the actual urls

		for(int checker=0; checker < BmodPassed.size(); checker++) {
			int increment = checker * 2;

			if(originalUrl.get(checker).equals(BUrls.get(increment)) && coOp.get(checker).equals(BUrls.get(increment+1))) {
				System.out.println("True for B" +(checker+1));
			}
			else
			{
				System.out.println("False for B" +(checker+1) );
			}

		}
	}
}
