package utilities;

import dataProvider.ConfigFileReader;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil extends ConfigFileReader{

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    public ExcelUtil(){
        super();
    }


    public Object[][] readExcelFile(String filePath, String sheetName) throws IOException {
        // Create a FileInputStream object to read the Excel file
        FileInputStream inputStream = new FileInputStream(new File(filePath));

        // Create a Workbook object based on the file input stream
        Workbook workbook = WorkbookFactory.create(inputStream);

        // Get the specified sheet from the workbook
        Sheet sheet = workbook.getSheet(sheetName);

        // Get the number of rows and columns in the sheet
        int numRows = sheet.getLastRowNum() + 1;
        int numCols = sheet.getRow(0).getLastCellNum();

        // Create a 2-dimensional array of objects to store the data
        Object[][] data = new Object[numRows][numCols];

        // Iterate through the rows and columns in the sheet, and populate the data array
        for (int i = 0; i < numRows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < numCols; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getCellType()) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = cell.getNumericCellValue();
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                        break;
                    case FORMULA:
                        data[i][j] = cell.getCellFormula();
                        break;
                    case BLANK:
                        data[i][j] = "";
                        break;
                    default:
                        data[i][j] = "";
                        break;
                }
            }
        }

        // Close the input stream and return the data array
        inputStream.close();
        return data;
    }


        public void readDataFromExcel() throws IOException {
        String path = getProperty("excelfile");

        Workbook workBook = WorkbookFactory.create(new File(path));

        Sheet sheet = workBook.getSheet("Sheet1");
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);

        //System.out.println(cell.getStringCellValue());
        int lastRow = sheet.getLastRowNum();

        for(int i =0;i<=lastRow;i++){

            int lastColumn = sheet.getRow(i).getLastCellNum()-1;
            System.out.print(sheet.getRow(i).getCell(0));
            System.out.print(" "+sheet.getRow(i).getCell(1));
            System.out.print(" "+sheet.getRow(i).getCell(2));
            System.out.print(" "+sheet.getRow(i).getCell(3));
            System.out.print(" "+sheet.getRow(i).getCell(lastColumn));
            System.out.println();
        }


    }

    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(path);
            // Access the required test data sheet
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
            // check if sheet is null or not. null means  sheet name was wrong
            Assert.assertNotNull(String.valueOf(workSheet), "Sheet: \""+sheetName+"\" does not exist\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String[][] getDataArray() {

        String[][] data = new String[rowCount()][columnCount()];

        for (int i = 0; i <rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;

    }

    //this method will return data table as 2d array
    //so we need this format because of data provider.
    public String[][] getDataArrayWithoutFirstRow() {

        String[][] data = new String[rowCount()-1][columnCount()];

        for (int i = 1; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i-1][j] = value;
            }
        }
        return data;

    }

    public List<Map<String, String>> getDataList() {
        // get all columns
        List<String> columns = getColumnsNames();
        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map by value
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }

            data.add(rowMap);
        }

        return data;
    }

    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    public List<String> getAllData(){

        List<String> tableData = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            Row row = workSheet.getRow(i);
            for (Cell cell : row) {
                tableData.add(cell.toString());
            }
        }
        return tableData;
    }

    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;

        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);

            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }

    public int columnCount() {
        return workSheet.getRow(0).getLastCellNum();
    }

    public int rowCount() {
        return workSheet.getLastRowNum()+1;
    }

}


