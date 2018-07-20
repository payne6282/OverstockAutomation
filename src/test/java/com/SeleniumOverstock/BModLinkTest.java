package com.SeleniumOverstock;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.Test;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BModLinkTest {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 60);
	ArrayList<String> BUrls = new ArrayList<String>();
	ArrayList<String> Bmod = new ArrayList<String>();

@Test
public void FirefoxTest() throws InterruptedException, IOException {

	System.setProperty("webdriver.chrome.driver", "/home/sachinkayath/workspaceLuna/Selenium/chromedriver");
	driver.manage().window().maximize();
	driver.get("https://www.overstock.com/sitetests");
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[3]/div/table/tbody/tr/td[2]/form/table/tbody/tr[16]/td[1]/input"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[3]/div/table/tbody/tr/td[2]/form/input"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='secureLogo']/a/img"))).click();
	Thread.sleep(3000);

	if(!driver.findElements(By.xpath(".//*[@id='oboxInitial']")).isEmpty()) {
		driver.findElement(By.xpath(".//*[@id='cboxClose']")).click();
	}
		Thread.sleep(3000);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//WebElement element = driver.findElement(By.xpath(".//*[@id='bd']/div/div[6]/div/a[1]/div/div/div[1]"));
		//je.executeScript("arguments[0].scrollIntoView(true);",element);
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		je.executeScript("window.scrollBy(0,250)", "");
		/*WebElement element = driver.findElement(By.xpath(".//*[@id='bd']/div/div[6]/div/a[1]/div/div/div[1]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);*/

		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div/div[6]/div/a[1]"))).getAttribute("href"));
		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div/div[6]/div/a[2]"))).getAttribute("href"));
		Bmod.add(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div/div[6]/div/a[3]"))).getAttribute("href"));

		BModLinkTest BModTest = new BModLinkTest();
		for(int counter=0; counter<Bmod.size(); counter++) {
			BModTest.openBMod(Bmod.get(counter), counter);
		}

		BModTest.checkUrlsExcel(Bmod);
		//AMod1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bd']/div/div[5]/div/a[1]"))).getAttribute("href");
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
public void checkUrlsExcel(ArrayList<String> BmodPassed) throws IOException {
	int sizer;
	String excelFilePath = "UrlsToCheckDefault.xlsx";
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
				System.out.print("The cell values are" +cell.getStringCellValue());
				BUrls.add(cell.getStringCellValue());
				System.out.println();
				break;
			}

		}
	}

	workbook.close();
	inputStream.close();

	ArrayList<String> originalUrl = new ArrayList<String>();
	ArrayList<String> coOp = new ArrayList<String>();

	for(sizer = 0; sizer < BmodPassed.size(); sizer++) {


		if (BmodPassed.get(sizer).contains("?TID")) {
			originalUrl.add((BmodPassed.get(sizer)).substring(0, BmodPassed.get(sizer).lastIndexOf("?")));
			coOp.add((BmodPassed.get(sizer)).substring(BmodPassed.get(sizer).lastIndexOf(":") + 1));
		} else if
			(BmodPassed.get(sizer).contains("&TID")) {
			originalUrl.add((BmodPassed.get(sizer)).substring(0, BmodPassed.get(sizer).lastIndexOf("&")));
			coOp.add((BmodPassed.get(sizer)).substring(BmodPassed.get(sizer).lastIndexOf(":") + 1));
		}
		System.out.println("Bmod links are" +originalUrl.get(sizer));
		System.out.println("Coop links are" +coOp.get(sizer));
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


