package com.creditlink.manager.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * 共分为六部完成根据模板导出excel操作：<br/>
 * 第一步、设置excel模板路径（setSrcPath）<br/>
 * 第二步、设置要生成excel文件路径（setDesPath）<br/>
 * 第三步、设置模板中哪个Sheet列（setSheetName）<br/>
 * 第四步、获取所读取excel模板的对象（getSheet）<br/>
 * 第五步、设置数据（分为6种类型数据：setCellStrValue、setCellDateValue、setCellDoubleValue、
 * setCellBoolValue、setCellCalendarValue、setCellRichTextStrValue）<br/>
 * 第六步、完成导出 （exportToNewFile）<br/>
 * 
 * @author Administrator
 * 
 */
public class ExcelUtilCp {
	private String srcXlsPath = "";// // excel模板路径
	private String desXlsPath = "";
	private String sheetName = "";
	POIFSFileSystem fs = null;
	HSSFWorkbook wb = null;
	HSSFSheet sheet = null;

	/**
	 * 第一步、设置excel模板路径
	 * 
	 * @param srcXlsPath
	 */
	public void setSrcPath(String srcXlsPath) {
		this.srcXlsPath = srcXlsPath;
	}

	/**
	 * 第二步、设置要生成excel文件路径
	 * 
	 * @param desXlsPath
	 */
	public void setDesPath(String desXlsPath) {
		this.desXlsPath = desXlsPath;
	}

	/**
	 * 第三步、设置模板中哪个Sheet列
	 * 
	 * @param sheetName
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 第四步、获取所读取excel模板的对象
	 */
	public void getSheet(FileInputStream fis) {
		try {
			wb = new HSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 第五步、设置字符串类型的数据
	 * 
	 * @param rowIndex
	 *            --行值
	 * @param cellnum
	 *            --列值
	 * @param value
	 *            --字符串类型的数据
	 */
	public void setCellStrValue(int rowIndex, int cellnum, String value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		if (null == cell) {
			cell = sheet.getRow(rowIndex).createCell(cellnum);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		CellStyle style=wb.createCellStyle();
		if ("认证通过".equals(value)) {
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setAlignment(CellStyle.ALIGN_CENTER);//左右居中    
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//上下居中    
			cell.setCellValue(value);
		}else {
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setWrapText(true);
			HSSFRichTextString rts=new HSSFRichTextString(value);
			cell.setCellValue(rts);
		}
		
		cell.setCellStyle(style);
	}
	public void setCellStrValue2(int rowIndex, int cellnum, String value) {
		//sheet.setColumnWidth(cellnum, 20*2*256);
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		CellStyle style=wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);//左右居中    
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//上下居中    
		if (null == cell) {
			cell = sheet.getRow(rowIndex).createCell(cellnum);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	public void setCellStrValue3(int rowIndex, int cellnum, String value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		// if (StringUtils.isNotBlank(value)) {
		// System.out.println("----rowIndex:"+rowIndex);
		// System.out.println("----cellnum:"+cellnum);
		// System.out.println("----"+value);
		// System.out.println("----++"+cell);
		if (null == cell) {
			cell = sheet.getRow(rowIndex).createCell(cellnum);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		cell.setCellValue(value);
		// }
	}

	/**
	 * 第五步、设置富文本字符串类型的数据。可以为同一个单元格内的字符串的不同部分设置不同的字体、颜色、下划线
	 * 
	 * @param rowIndex
	 *            --行值
	 * @param cellnum
	 *            --列值
	 * @param value
	 *            --富文本字符串类型的数据
	 */
	public void setCellRichTextStrValue(int rowIndex, int cellnum,
			RichTextString value) {
		HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
		cell.setCellValue(value);
	}

	/**
	 * 第六步、完成导出
	 */
	public void exportToNewFile(OutputStream out) {
		// FileOutputStream out;
		try {
			// out = new FileOutputStream(desXlsPath);
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 第六步、完成导出
	 */
	public void exportToNewFile2() {
		FileOutputStream out;
		try {
			out = new FileOutputStream("C:\\work\\test.xls");
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 第六步、完成导出
	 */
	public void exportToNewFile3(String path) {
		FileOutputStream out;
		try {
			out = new FileOutputStream(path);
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

}