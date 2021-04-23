/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtils工具类
 * @version 2017年12月12日下午2:30:01
 * @author wuliu
 */
public class ExcelUtils {
    
    /**
     * 读取Excel文件
     *
     * @param file
     *            要读取的Excel文件
     * @param file
     *            要读取的Excel文件
     * @param ignoreRows
     *            想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException
     */
    public static List<List<String>> readExcel(File file, String fileName, int ignoreRows) throws IOException {
        String suffix = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(suffix)) {
            return read2003Excel(file, ignoreRows);
        } else if ("xlsx".equals(suffix)) {
            return read2007Excel(file, ignoreRows);
        } else {
            throw new IOException("文件类型错误！");
        }
    }
    
    /**
     * 读取2003版本Excel
     *
     * @param file
     *            要读取的Excel文件
     * @param ignoreRows
     *            想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException
     */
    private static List<List<String>> read2003Excel(File file, int ignoreRows) throws IOException {
        // 构造一个Excel对象
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        // 循环读取所有的sheet页面
        List<List<String>> rows = new ArrayList<List<String>>();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            // 获取一个sheet页面
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环读取sheet中所有的行
            for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // 获取一行
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                // 循环读取一行中所有的单元格
                List<String> cells = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                    // 获取一个单元格
                    HSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    String value;
                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = "";
                            }
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                        } else {
                            value = cell.getNumericCellValue() + "";
                        }
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                        break;
                    default:
                        value = "";
                        break;
                    }
                    cells.add(value.trim());
                }
                rows.add(cells);
            }
        }
        return rows;
    }

    /**
     * 读取2007版本Excel
     *
     * @param file
     *            要读取的Excel文件
     * @param ignoreRows
     *            想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException
     */
    private static List<List<String>> read2007Excel(File file, int ignoreRows) throws IOException {
        // 构造一个Excel对象
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        // 循环读取所有的sheet页面
        List<List<String>> rows = new ArrayList<List<String>>();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            // 获取一个sheet页面
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环读取sheet中所有的行
            for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // 获取一行
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                // 循环读取一行中所有的单元格
                List<String> cells = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                    // 获取一个单元格
                    XSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    String value;
                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = "";
                            }
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                        } else {
                            value = cell.getNumericCellValue() + "";
                        }
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                        break;
                    default:
                        value = "";
                        break;
                    }
                    cells.add(value.trim());
                }
                rows.add(cells);
            }
        }
        return rows;
    }

    /**
     * 导出
     *
     * @param headName
     *            标题名称
     * @param titles
     *            列标题名称
     * @param fieldNames
     *            获取列数据的map的id
     * @param dataList
     *            数据
     * @param columnWidth
     *            列宽度
     * @param styleMap
     *            列格式
     * @return
     * @throws Exception
     */
    public static HSSFWorkbook excelPrint(HSSFWorkbook workbook, String headName, String[] titles, String[] fieldNames, List<Map<String, Object>> dataList, int[] columnWidth,
            Map<String, HSSFWordBookStyle> styleMap) throws Exception {
        if (StringUtils.isBlank(headName)) {
            headName = "无标题";
        }
        String sheetCount = "";
        if (dataList != null && (dataList.size() + 2) > Constants.EXPOT_EXCEL_MAX_PAGE_SIZE) {
            sheetCount = "_1";
        }
        HSSFSheet sheet = workbook.createSheet(headName + sheetCount);// 创建一个Excel的Sheet
        if (titles == null || titles.length == 0) {
            return workbook;
        }

        /**
         * 设置列宽
         */
        if (columnWidth != null && columnWidth.length > 0) {
            for (int i = 0; i < columnWidth.length; i++) {
                sheet.setColumnWidth(i, columnWidth[i]);
            }
        }

        /**
         * 设置普通样式
         */
        HSSFFont columnFont = workbook.createFont();
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        columnFont.setFontName("宋体");
        columnFont.setFontHeightInPoints((short) 10);
        columnStyle.setFont(columnFont);
        columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        try {
            int rowSize = 0;
            /**
             * 创建标题
             */
            createTitle(headName, workbook, sheet, titles);
            rowSize = 2;
            /**
             * 列表内容
             */
            if (dataList != null && dataList.size() > 0 && fieldNames != null && fieldNames.length > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (rowSize == Constants.EXPOT_EXCEL_MAX_PAGE_SIZE) {
                        sheet = getNewSheet(workbook, columnWidth, titles, headName + "_2");
                        createTitle(headName, workbook, sheet, titles);
                        rowSize = 2;
                    }
                    Map<String, Object> data = dataList.get(i);
                    HSSFRow row = sheet.createRow(rowSize);
                    rowSize += 1;
                    row.setHeight((short) 350);
                    for (int j = 0; j < fieldNames.length; j++) {
                        HSSFCell cell = row.createCell(j);
                        String value = data.get(fieldNames[j]) == null ? "" : String.valueOf(data.get(fieldNames[j]));
                        cell.setCellValue(new HSSFRichTextString(value));
                        cell.setCellStyle(columnStyle);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return workbook;

    }

    /**
     * 导出 by wuliu
     *
     * @param workbook
     * @param headName
     *            标题名称
     * @param secondHeadNames
     *            二级标题，[标题名，长度]
     * @param titles
     *            列标题名称
     * @param fileds
     *            获取列数据的map的id
     * @param dataList
     *            数据
     * @param columnWidth
     *            列宽度
     * @param styleMap
     *            列格式
     * @return
     * @throws Exception
     */
    public static HSSFWorkbook excelPrint(HSSFWorkbook workbook, String headName, String[][] secondHeadNames, String[] titles, String[] fileds, List<Map<String, Object>> dataList,
            int[] columnWidth, Map<String, HSSFWordBookStyle> styleMap) throws Exception {

        HSSFSheet sheet = workbook.createSheet(headName);// 创建一个Excel的Sheet
        if (titles == null || titles.length == 0) {
            return workbook;
        }

        /**
         * 设置列宽
         */
        if (columnWidth != null && columnWidth.length > 0) {
            for (int i = 0; i < columnWidth.length; i++) {
                sheet.setColumnWidth(i, columnWidth[i]);
            }
        }

        /**
         * 设置普通样式
         */
        HSSFFont columnFont = workbook.createFont();
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        columnFont.setFontName("宋体");
        columnFont.setFontHeightInPoints((short) 10);
        columnStyle.setFont(columnFont);
        columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        try {
            int rowSize = 0;

            /**
             * 创建标题
             */
            /**
             * 设置标题样式
             */
            HSSFFont columnHeadFont = workbook.createFont();
            columnHeadFont.setFontName("宋体");
            columnHeadFont.setFontHeightInPoints((short) 12);
            columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
            columnHeadStyle.setFont(columnHeadFont);
            columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            columnHeadStyle.setLocked(true);
            columnHeadStyle.setWrapText(true);
            if (StringUtils.isNotEmpty(headName)) {
                /**
                 * 创建第一行标题
                 */
                HSSFRow headRow = sheet.createRow(rowSize);
                rowSize += 1;
                headRow.setHeight((short) 450);
                HSSFCell headCell = headRow.createCell(0);
                headCell.setCellValue(new HSSFRichTextString(headName));
                headCell.setCellStyle(columnHeadStyle);

                /**
                 * 合并单元格 参数： 起始行数，终止行数，起始列数，终止列数
                 */
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titles.length - 1));// 指定合并区域
            }

            HSSFFont titleFont = workbook.createFont();
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            titleFont.setFontName("宋体");
            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleFont.setFontHeightInPoints((short) 11);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

            /**
             * 创建二级列名
             */
            HSSFRow secondHeadRow = sheet.createRow(rowSize);
            secondHeadRow.setHeight((short) 450);
            int cellNumTotal = 0;
            for (int i = 0; secondHeadNames != null && i < secondHeadNames.length; i++) {
                String titleStr = secondHeadNames[i][0];
                int cellNum = Integer.parseInt(secondHeadNames[i][1]);
                HSSFCell cell = secondHeadRow.createCell(cellNumTotal);
                cellNumTotal += cellNum;
                cell.setCellValue(new HSSFRichTextString(titleStr));
                cell.setCellStyle(titleStyle);
            }
            cellNumTotal = 0;
            for (int i = 0; secondHeadNames != null && i < secondHeadNames.length; i++) {
                int cellNum = Integer.parseInt(secondHeadNames[i][1]);
                sheet.addMergedRegion(new CellRangeAddress(rowSize, rowSize, cellNumTotal, cellNumTotal + cellNum - 1));
                cellNumTotal += cellNum;
            }
            rowSize += 1;
            /**
             * 创建列名
             */
            HSSFRow titleRow = sheet.createRow(rowSize);
            rowSize += 1;
            titleRow.setHeight((short) 400);
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(new HSSFRichTextString(titles[i]));
                cell.setCellStyle(titleStyle);
            }

            /**
             * 列表内容
             */
            if (dataList != null && dataList.size() > 0 && fileds != null && fileds.length > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    Map<String, Object> data = dataList.get(i);
                    HSSFRow row = sheet.createRow(rowSize);
                    rowSize += 1;
                    row.setHeight((short) 350);
                    for (int j = 0; j < fileds.length; j++) {
                        HSSFCell cell = row.createCell(j);
                        String value = data.get(fileds[j]) == null ? "" : String.valueOf(data.get(fileds[j]));
                        cell.setCellValue(new HSSFRichTextString(value));
                        cell.setCellStyle(columnStyle);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return workbook;

    }

    private static void createTitle(String headName, HSSFWorkbook workbook, HSSFSheet sheet, String[] titles) {
        int rowSize = 0;
        /**
         * 创建标题
         */
        /**
         * 设置标题样式
         */
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 12);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);

        /**
         * 创建第一行标题
         */
        HSSFRow headRow = sheet.createRow(rowSize);
        rowSize += 1;
        headRow.setHeight((short) 450);
        HSSFCell headCell = headRow.createCell(0);
        headCell.setCellValue(new HSSFRichTextString(headName));
        headCell.setCellStyle(columnHeadStyle);

        /**
         * 合并单元格 参数： 起始行数，终止行数，起始列数，终止列数
         */
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titles.length - 1));// 指定合并区域

        /**
         * 创建列名
         */
        HSSFRow titleRow = sheet.createRow(rowSize);
        rowSize += 1;
        titleRow.setHeight((short) 400);

        HSSFFont columnFont = workbook.createFont();
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        columnFont.setFontName("宋体");
        columnFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        columnFont.setFontHeightInPoints((short) 11);
        columnStyle.setFont(columnFont);
        columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(new HSSFRichTextString(titles[i]));
            cell.setCellStyle(columnStyle);
        }
    }

    private static HSSFSheet getNewSheet(HSSFWorkbook workbook, int[] columnWidth, String[] titles, String headName) {
        HSSFSheet sheet = workbook.createSheet(headName);// 创建一个Excel的Sheet
        /**
         * 设置列宽
         */
        if (columnWidth != null && columnWidth.length > 0) {
            for (int i = 0; i < columnWidth.length; i++) {
                sheet.setColumnWidth(i, columnWidth[i]);
            }
        }
        return sheet;
    }

    /**
     * 根据文件名判断文件是否是Excel
     *
     * @param filename
     * @return
     */
    public static boolean isExcel(String filename) {
        if (filename == null) {
            return false;
        }
        String suffix = filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf(".") + 1);
        if ("xls".equals(suffix) || "xlsx".equals(suffix)) {
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    @SuppressWarnings("rawtypes")
    public static List<Map> readExcel2003Cp(InputStream fis) throws IOException {
        // 返回结果集
        List<Map> valueList = new ArrayList<Map>();
        try {
            HSSFWorkbook wookbook = new HSSFWorkbook(fis); // 创建对Excel工作簿文件的引用
            HSSFSheet sheet = wookbook.getSheetAt(0); // 在Excel文档中，第一张工作表的缺省索引是0
            int rows = sheet.getPhysicalNumberOfRows(); // 获取到Excel文件中的所有行数­
            Map<Integer, String> keys = new HashMap<Integer, String>();
            int cells = 4;
            // 遍历行­（第1行 表头） 准备Map里的key
            HSSFRow firstRow = sheet.getRow(0);
            if (firstRow != null) {
                // 获取到Excel文件中的所有的列
                // 遍历列
                for (int j = 0; j < cells; j++) {
                    // 获取到列的值­
                    try {
                        HSSFCell cell = firstRow.getCell(j);
                        String cellValue = getCellValue(cell);
                        keys.put(j, ChineseToEnglish.getPinYinHeadChar(cellValue));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // 遍历行­（从第3行开始）
            for (int i = 1; i < rows; i++) {
                // 读取左上端单元格(从第二行开始)
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                    // 准备当前行 所储存值的map
                    Map<String, Object> val = new HashMap<String, Object>();
                    boolean isValidRow = false;
                    // 遍历列
                    for (int j = 0; j < cells; j++) {
                        // 获取到列的值­
                        try {
                            HSSFCell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            val.put(keys.get(j), cellValue);
                            if (!isValidRow && cellValue != null
                                    && cellValue.trim().length() > 0) {
                                isValidRow = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    // 第I行所有的列数据读取完毕，放入valuelist
                    if (isValidRow) {
                        valueList.add(val);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
        return valueList;
    }
    
    @SuppressWarnings("rawtypes")
    public static List<Map> readExcel2003Cp(InputStream fis, int cells) throws IOException {
        // 返回结果集
        List<Map> valueList = new ArrayList<Map>();
        try {
            HSSFWorkbook wookbook = new HSSFWorkbook(fis); // 创建对Excel工作簿文件的引用
            HSSFSheet sheet = wookbook.getSheetAt(0); // 在Excel文档中，第一张工作表的缺省索引是0
            int rows = sheet.getPhysicalNumberOfRows(); // 获取到Excel文件中的所有行数­
            for (int i = 1; i < rows; i++) {
                // 读取左上端单元格(从第二行开始)
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                    // 准备当前行 所储存值的map
                    Map<String, Object> val = new HashMap<String, Object>();
                    boolean isValidRow = false;
                    // 遍历列(获取到列的值­)
                    for (int j = 0; j < cells; j++) {
                        try {
                            HSSFCell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            val.put(String.valueOf(j), cellValue);
                            if (!isValidRow && cellValue != null
                                    && cellValue.trim().length() > 0) {
                                isValidRow = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    // 第I行所有的列数据读取完毕，放入valuelist
                    if (isValidRow) {
                        valueList.add(val);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //fis.close();
        }
        return valueList;
    }
    
    private static String getCellValue(HSSFCell cell) {
        DecimalFormat df = new DecimalFormat("#");
        String cellValue = null;
        if (cell == null)
            return null;
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell
                        .getNumericCellValue()));
                break;
            }
            cellValue = df.format(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_STRING:
            cellValue = String.valueOf(cell.getStringCellValue());
            break;
        case HSSFCell.CELL_TYPE_FORMULA:
            cellValue = String.valueOf(cell.getCellFormula());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            cellValue = null;
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            cellValue = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_ERROR:
            cellValue = String.valueOf(cell.getErrorCellValue());
            break;
        }
        if (cellValue != null && cellValue.trim().length() <= 0) {
            cellValue = null;
        }
        return cellValue;
    }
}
