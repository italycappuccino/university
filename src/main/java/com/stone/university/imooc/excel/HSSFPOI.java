package com.stone.university.imooc.excel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFPOI {

	static final String fileName = "/Users/Peter/Documents/data/bbb.xls";

	public static void main(String[] args) {
		writePOI();
		readPOI();

	}

	private static void writePOI() {
		try {
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet();
			HSSFRow row = sheet.createRow(0);

			String[] title = { "id", "name", "gender" };
			for (int i = 0; i < title.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(title[i]);
			}

			for (int i = 1; i <= 10; i++) {
				HSSFRow nextRow = sheet.createRow(i);
				HSSFCell cell = nextRow.createCell(0);
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
			HSSFWorkbook workBook = new HSSFWorkbook(
					FileUtils.openInputStream(file));

			// HSSFSheet sheet = workBook.getSheet("Sheet0");
			HSSFSheet sheet = workBook.getSheetAt(0);

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					HSSFCell cell = row.getCell(j);
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
