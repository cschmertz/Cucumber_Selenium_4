package utilities;

import dataProvider.ConfigFileReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtil extends ConfigFileReader{

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    public ExcelUtil(){
        super();
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
            //Assert.assertNotNull(String.valueOf(workSheet), "Sheet: \""+sheetName+"\" does not exist\n");
            Optional<String> sheet = Optional.ofNullable(String.valueOf(workSheet));
            sheet.orElseThrow(() ->new AssertionError("Sheet: " + sheetName + " does not exist"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTableToExcel(Object[][] data, String filePath) throws Exception {
        // create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // write data to cells
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(data[i][j].toString());
            }
        }

        // write workbook to file
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


    public void writeElementsToExcel(List<WebElement> elements, String filePath) throws Exception {
        // create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // write element text to cells
        for (int i = 0; i < elements.size(); i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(elements.get(i).getText());
        }

        // write workbook to file
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
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


    public List<Object> getRowData(int rowNum) {
        List<Object> rowData = new ArrayList<>();
        Row row = workSheet.getRow(rowNum);

        if (row != null) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.add(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        rowData.add(cell.getBooleanCellValue());
                        break;
                    default:
                        rowData.add(null);
                        break;
                }
            }
        }

        return rowData;
    }

    public void printRowData(int rowNum) {
        Row row = workSheet.getRow(rowNum);

        if (row != null) {
            int cellCount = row.getLastCellNum();
            int currentCell = 0;

            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    default:
                        System.out.print("");
                        break;
                }

                currentCell++;
                if (currentCell < cellCount) {
                    System.out.print(",");
                }
            }

            System.out.println(); // print new line after row data
        }

    }

    public String getRowDataWithoutCommas(int rowNum) {
        Row row = workSheet.getRow(rowNum);
        StringBuilder rowData = new StringBuilder();

        if (row != null) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.append(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.append(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        rowData.append(cell.getBooleanCellValue());
                        break;
                    default:
                        rowData.append("");
                        break;
                }
                rowData.append(" "); // add space between cell values
            }
            rowData.deleteCharAt(rowData.length() - 1); // remove last space character
        }

        return rowData.toString();
    }

    public String getRowDataWithCellDataOnNewLine(int rowNum) {
        Row row = workSheet.getRow(rowNum);
        StringBuilder rowData = new StringBuilder();

        if (row != null) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.append(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.append(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        rowData.append(cell.getBooleanCellValue());
                        break;
                    default:
                        rowData.append("");
                        break;
                }
                rowData.append("\n"); // add new line after each cell value
            }
            rowData.deleteCharAt(rowData.length() - 1); // remove last new line character
        }

        return rowData.toString();
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

    //this method will return data table as 2d array,
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

        for (int i = 0; i < rowCount(); i++) {
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


