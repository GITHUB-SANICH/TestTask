package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProviders {
    @DataProvider
    public Object[][] infoForAuthorizationToUsers() throws Exception {
        String path = "src/test/resources/dataForTest.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }

    @DataProvider
    public Object[][] informationAboutPageElements() throws Exception {
        String path = "src/test/resources/dataForTest.xlsx";
        ExcelReader excelReader = new ExcelReader(path, "элементы");
        return excelReader.getCustomSheetForTDD();
    }
}