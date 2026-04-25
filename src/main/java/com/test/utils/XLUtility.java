package com.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
// import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;


public class XLUtility {

    String filePath = "";

    public XLUtility(String filePath){
        this.filePath = filePath;
    }
    
    // File accessing methods
    private FileInputStream openFileInput()throws FileNotFoundException {
        FileInputStream file = new FileInputStream(filePath);
        return file;
    }

    private FileOutputStream openFileOutput()throws FileNotFoundException{
         FileOutputStream file = new FileOutputStream(filePath);
         return file;
    }

    private void closeFile(FileInputStream file , XSSFWorkbook book)throws IOException{
        file.close();
        book.close();
    }

    // private void closeFile(FileOutputStream file , XSSFWorkbook book)throws IOException{
    //     file.close();
    //     book.close();
    // }

    public XSSFWorkbook getWorkbook()throws IOException{
        XSSFWorkbook wb = new XSSFWorkbook(filePath);
        return wb;
    }

    //Reading operations
    public int getRowsCount(String sheetName)throws FileNotFoundException, IOException{
        FileInputStream file = openFileInput();
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        int rows = sheet.getLastRowNum()+1;
        closeFile(file,wb);
        return rows;
    }
    
    public int getColCount(String sheetName)throws FileNotFoundException, IOException{
        FileInputStream file = openFileInput();
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        int col = sheet.getRow(1).getLastCellNum();
        closeFile(file,wb);
        return col;
    }

    public String getCellData(String sheetName, int rowIndex, int cellIndex)throws FileNotFoundException, IOException{
        FileInputStream file = openFileInput();
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowIndex);
        XSSFCell cell = row.getCell(cellIndex);
        String data = "";
        switch(cell.getCellType()){
            case STRING: data = cell.getStringCellValue(); break;
            case NUMERIC: data = String.valueOf(cell.getNumericCellValue()); break;
            case BOOLEAN: data = String.valueOf(cell.getBooleanCellValue()); break;
            default: data = ""; break;
        }
        closeFile(file,wb);
        return data;
    }

    //Writing Operations
    public void  setCellData(String msg, int rowNum , int colNum, String sheetName)throws FileNotFoundException, IOException{
        FileInputStream file = openFileInput();
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowNum);
        if(row == null){
            row = sheet.createRow(rowNum);
        }
        XSSFCell cell = row.getCell(colNum);
        if(cell == null){
            cell = row.createCell(colNum);
        }

        if(msg instanceof String){
            cell.setCellValue((String)msg);
        }
        FileOutputStream fout = openFileOutput();
        wb.write(fout);
        fout.close();
        closeFile(file, wb);
    }
  
}
