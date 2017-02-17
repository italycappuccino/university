package com.stone.university.imooc.excel;

import java.io.File;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JXL {

	static final String fileName = "/Users/Peter/Documents/data/aaa.xls";

	public static void main(String[] args) {
		writeJXL();
		readJXL();
	}

	private static void readJXL() {
		try {
			Workbook workBook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workBook.getSheet(0);

			for (int i = 0; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					System.out.print(cell.getContents() + "\t");
				}
				System.out.println();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void writeJXL() {
		try {

			String[] title = { "id", "name", "gender" };
			File file = new File(fileName);

			file.createNewFile();
			WritableWorkbook workBook = Workbook.createWorkbook(file);
			WritableSheet sheet = workBook.createSheet("sheet1", 0);

			Label label = null;
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 0, title[i]);
				sheet.addCell(label);
			}

			for (int i = 1; i < 10; i++) {
				label = new Label(0, i, UUID.randomUUID().toString());
				sheet.addCell(label);
				label = new Label(1, i, "name" + i);
				sheet.addCell(label);
				label = new Label(2, i, "F");
				sheet.addCell(label);
			}

			workBook.write();
			workBook.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
