package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.test.config.ConfigReader;
import com.test.pages.LoginPage;
import com.test.utils.XLUtility;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "LoginData")
    public void loginTest(String user, String pass) throws FileNotFoundException, IOException{
        LoginPage login = new LoginPage();
        Assert.assertEquals(login.getPageTitle(),"Swag Labs","The login title page should be of ");
        login.loginWithCredentials(user, pass);
        Assert.assertEquals(login.getPageTitle(),"Swag Labs","The login title page should be of ");
    }

    @DataProvider(name = "LoginData")// remember the DataProvider will always choose to give only two dimensional data only 
    public String[][] getData() throws FileNotFoundException, IOException{
        String path = ConfigReader.getFilePath();
        XLUtility excelFile = new XLUtility(path);
        int rows = excelFile.getRowsCount("sheet2");
        System.out.println("Row counts = " + rows);
        int cols = excelFile.getColCount("sheet2");
        System.out.println("col counts = " + cols);
        String[][] data = new String[rows-1][cols];
        for(int i = 1 ;i < rows;i++){
            for(int j = 0; j< cols;j++){
                data[i-1][j] = excelFile.getCellData("sheet2", i, j);
            }
        }
        return data;
    }

    // public void setData(String msg, int rowIndex, int colIndex, String sheetName)throws FileNotFoundException, IOException{
    //     String path = ConfigReader.getFilePath();
    //     XLUtility excelFile = new XLUtility(path);
    //     XSSFWorkbook book =  excelFile.getWorkbook();
    //     excelFile.setCellData(book, rowIndex, colIndex, sheetName, msg);
    //     book.close();
    // }

    // public int getRowIndex(String user)throws FileNotFoundException, IOException{
    //     String path = ConfigReader.getFilePath();
    //     XLUtility excelFile = new XLUtility(path);
    //     int rows = excelFile.getRowsCount("sheet2");

    //     for(int i = 1; i< rows; i++){
    //         if(excelFile.getCellData("sheet2", i,0).equals(user)){
    //             return i;
    //         }
    //     }
    //     return -1;
    // }
    
}
