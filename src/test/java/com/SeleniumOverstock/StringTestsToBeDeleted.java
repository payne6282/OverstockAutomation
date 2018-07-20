package com.SeleniumOverstock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class StringTestsToBeDeleted {


	public static void main(String args[]) throws IOException {
	ArrayList<String> BUrls = new ArrayList<String>();
	String excelFilePath = "UrlsToCheck.xlsx";
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
                    System.out.print(cell.getStringCellValue());
                    BUrls.add(cell.getStringCellValue());
                    break;
               /* case Cell.CELL_TYPE_BOOLEAN:
                    System.out.print(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.print(cell.getNumericCellValue());
                    break;*/
            }

            //System.out.print(" - ");
        }
        System.out.println();
    }

    workbook.close();
    inputStream.close();

    for (int i = 0; i < BUrls.size(); i++) {
    System.out.println("The values in arraylist are" +BUrls.get(i));
    }


	String URL = "https://www.overstock.com/91869,/vendorid,/results.html?sort=On%20Sale&products=11871441&TID=HP:09:SmMwtsL1:Bathroom:COOP-91869-HPTEST-20160930-4";
	String subStr = URL.substring(URL.lastIndexOf(":") + 1);
	String firStr = URL.substring(0, URL.lastIndexOf("&"));
	System.out.println(subStr);
	System.out.println(firStr);

	if(subStr.equals(BUrls.get(1))) {
		System.out.println("True");
	}
	else
	{
		System.out.println("False");
	}

}

}
