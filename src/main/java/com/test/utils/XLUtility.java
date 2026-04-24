package com.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
// import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

/*
author: korivi ramanadh
date : 27-03-2026
Building an excel utility code
*/

/*
 The idea is to create a excel uitily class that is a central excel codes that are reusable in every other codes 
 so the is to create function that can help us in the reading writing formatting updating the following i am thinking as

 first would be file accessing 
 i.e, opening and closing a workbook that is often needed to be done so that no leakage of the system resourse

 second read operations
 these are the primary operations across all like getiing the cell and row counts and then the data in the cell like those things

 write operations
 mostly required in some of the cases so that the cells are updated some values for example if the result of the test case in case it is passed it must be updated right 

 formatting operations
 adding the colours and all others things for now we are keeping these things aside we will add it in the future just not now

*/

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

    // private FileOutputStream openFileOutput()throws FileNotFoundException{
    //     FileOutputStream file = new FileOutputStream(filePath);
    //     return file;
    // }

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

    //Writing operations
    public void setCellData(XSSFWorkbook book, int rowIndex, int colIndex, String sheetName, String msg) throws FileNotFoundException, IOException{
        XSSFSheet sheet = book.getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowIndex);
        XSSFCell cell = row.getCell(colIndex);

        if(msg.equals("passed")){
            cell.setCellValue(msg);
           try(FileOutputStream fout = new FileOutputStream(filePath)){
             book.write(fout);
           }
        }else{
            cell.setCellValue(msg);
            try(FileOutputStream fout = new FileOutputStream(filePath)){
             book.write(fout);
           }
        }
    }
  
}
