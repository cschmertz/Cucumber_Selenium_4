package dataProvider;

import utilities.ExcelUtil;
import java.util.List;
import java.util.Map;

public class TestData {
    private ExcelUtil excelUtil;

    public TestData(String excelPath, String sheetName) {
        this.excelUtil = new ExcelUtil(excelPath, sheetName);
    }

    public List<Map<String, String>> getTestData() {
        return excelUtil.getAllData(excelUtil.getSheetName()):
    }

    // Add more methods for different data sources (JSON, Database, etc.)
}
