package com.stone.university.imooc.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Template {

	public static void main(String[] args) {

		createTemplate();
	}

	private static void createTemplate() {
		try {

			String fileName = System.getProperty("user.dir")
					+ "/src/main/java/com/stone/university/imooc/excel/student.xml";

			File file = new File(fileName);

			SAXBuilder builder = new SAXBuilder();
			Document parse = builder.build(file);

			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet = wb.createSheet("Sheet0");

			Element root = parse.getRootElement();

			String templateName = root.getAttribute("name").getValue();

			// 设置列宽
			Element colgroup = root.getChild("colgroup");
			List<Element> cols = colgroup.getChildren("col");
			for (int i = 0; i < cols.size(); i++) {
				Element col = cols.get(i);
				Attribute width = col.getAttribute("width");
				String unit = width.getValue().replaceAll("[0-9,\\.]", "");
				String value = width.getValue().replaceAll(unit, "");
				int v = 0;
				if (StringUtils.isBlank(unit) || "px".endsWith(unit)) {
					v = Math.round(Float.parseFloat(value) * 37F);
				} else if ("em".endsWith(unit)) {
					v = Math.round(Float.parseFloat(value) * 267.5F);
				}
				sheet.setColumnWidth(i, v);
			}

			int rownum = 0;
			int colnum = 0;

			// 设置标题
			Element title = root.getChild("title");
			List<Element> trs = title.getChildren("tr");
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				List<Element> tds = tr.getChildren("td");
				HSSFRow row = sheet.createRow(rownum);

				HSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				for (colnum = 0; colnum < tds.size(); colnum++) {
					Element td = tds.get(colnum);
					HSSFCell cell = row.createCell(colnum);
					Attribute rowSpan = td.getAttribute("rowspan");
					Attribute colSpan = td.getAttribute("colspan");
					Attribute value = td.getAttribute("value");
					if (value != null) {
						cell.setCellValue(value.getValue());
						int rspan = rowSpan.getIntValue() - 1;
						int cspan = colSpan.getIntValue() - 1;

						HSSFFont font = wb.createFont();
						font.setFontName("仿宋_GB2312");
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						// font.setFontHeight((short) 12);
						font.setFontHeightInPoints((short) 12);
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);

						sheet.addMergedRegion(new CellRangeAddress(rspan,
								rspan, 0, cspan));
					}
				}
				rownum++;
			}
			Element thead = root.getChild("thead");
			trs = thead.getChildren("tr");
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				HSSFRow row = sheet.createRow(rownum);
				List<Element> ths = tr.getChildren("th");
				for (colnum = 0; colnum < ths.size(); colnum++) {
					Element td = ths.get(colnum);
					Attribute valueAttr = td.getAttribute("value");
					HSSFCell cell = row.createCell(colnum);
					if (valueAttr != null) {
						cell.setCellValue(valueAttr.getValue());
					}
				}
				rownum++;
			}

			Element tbody = root.getChild("tbody");
			Element tr = tbody.getChild("tr");
			int repeat = tr.getAttribute("repeat").getIntValue();
			List<Element> tds = tr.getChildren("td");
			for (int i = 0; i < repeat; i++) {
				HSSFRow row = sheet.createRow(rownum);
				for (colnum = 0; colnum < tds.size(); colnum++) {
					Element td = tds.get(colnum);
					HSSFCell cell = row.createCell(colnum);
					Attribute typeAttr = td.getAttribute("type");
					String type = typeAttr.getValue();
					HSSFDataFormat format = wb.createDataFormat();
					HSSFCellStyle cellStyle = wb.createCellStyle();
					if ("NUMERIC".equalsIgnoreCase(type)) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						Attribute formatAttr = td.getAttribute("format");
						String formatValue = formatAttr.getValue();
						formatValue = StringUtils.isNotBlank(formatValue) ? formatValue
								: "#,##0.00";
						cellStyle.setDataFormat(format.getFormat(formatValue));

					} else if ("STRING".equalsIgnoreCase(type)) {
						cell.setCellValue("");
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cellStyle.setDataFormat(format.getFormat("@"));
					} else if ("DATE".equalsIgnoreCase(type)) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cellStyle.setDataFormat(format.getFormat("yyyy-m-d"));
					} else if ("ENUM".equalsIgnoreCase(type)) {
						// @SuppressWarnings("deprecation")
						// CellRangeAddressList regions = new
						// CellRangeAddressList(
						// cell.getRowIndex(), cell.getRowIndex(),
						// cell.getColumnIndex(), cell.getColumnIndex());
						CellRangeAddressList regions = new CellRangeAddressList(
								cell.getRowIndex(), cell.getRowIndex(),
								cell.getColumnIndex(), cell.getColumnIndex());

						Attribute enumAttr = td.getAttribute("format");
						String enumValue = enumAttr.getValue();
						DVConstraint contraint = DVConstraint
								.createExplicitListConstraint(enumValue
										.split(","));
						HSSFDataValidation dataValidation = new HSSFDataValidation(
								regions, contraint);
						wb.getSheetAt(0).addValidationData(dataValidation);

					}
					cell.setCellStyle(cellStyle);

				}
				rownum++;
			}

			File templateFile = new File("/Users/Peter/Documents/data/"
					+ templateName + ".xls");
			templateFile.delete();
			templateFile.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(templateFile);
			wb.write(stream);
			stream.close();
			wb.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
