package com.stone.university.imooc.excel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFPOI {

	static final String fileName = "/Users/Peter/Documents/data/ccc.xlsx";

	public static void main(String[] args) {
		writePOI();
		readPOI();

	}

	private static void writePOI() {
		try {
			XSSFWorkbook workBook = new XSSFWorkbook();
			Sheet sheet = workBook.createSheet();
			Row row = sheet.createRow(0);

			String[] title = { "id", "name", "gender" };
			for (int i = 0; i < title.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(title[i]);
			}

			for (int i = 1; i <= 10; i++) {
				Row nextRow = sheet.createRow(i);
				Cell cell = nextRow.createCell(0);
				cell.setCellValue(i + "");
				cell = nextRow.createCell(1);
				cell.setCellValue("name" + i);
				cell = nextRow.createCell(2);
				cell.setCellValue("F");
			}

			File file = new File(fileName);
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workBook.write(stream);

			stream.close();

			workBook.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void readPOI() {
		try {
			File file = new File(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(
					FileUtils.openInputStream(file));

			// Sheet sheet = workBook.getSheet("Sheet0");
			Sheet sheet = workBook.getSheetAt(0);

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					System.out.print(cell.getStringCellValue() + "\t");
				}
				System.out.println();
			}

			workBook.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
