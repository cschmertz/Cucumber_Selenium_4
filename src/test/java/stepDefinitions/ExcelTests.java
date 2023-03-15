package stepDefinitions;

import org.junit.Test;
import utilities.ExcelUtil;

import java.io.IOException;
import java.util.Arrays;

public class ExcelTests extends ExcelUtil {

    @Test
    public void excelTest() throws IOException {

     //Method will read the file path directly from Config.properties file and print out the result
          readDataFromExcel();
    }

    @Test
    public void excelTest2() throws IOException {
        //Method takes two String parameters below and stores the data into a 2 dimensional array
        // We then have to assign it to an Array of Object like below and iterate through it print the result
        String filePath = "/Users/riazahmed/Desktop/testNGFrameworkBook.xlsx";
        String sheetName = "Sheet1";
        Object[][] data = readExcelFile(filePath, sheetName);

        for(int i = 0; i < data.length; i++){

            for (int j = 0; j < data[i].length; j++ ){
                System.out.print(data[i][j] + "");
            }
            System.out.println();
        }
    }
}
