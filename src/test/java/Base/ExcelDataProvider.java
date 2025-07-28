package Base;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProvider {

    @DataProvider(name = "excelUsers")
    public static Object[][] getUsersFromExcel() throws IOException {
        ExcelReader excelReader = new ExcelReader("C:\\Users\\vmvma\\Downloads\\Users.xlsx");

        int rowCount = excelReader.getLastRow("Sheet2");
        Object[][] data = new Object[rowCount][2];

        for (int i = 1; i <= rowCount; i++) {
            data[i - 1][0] = excelReader.getStringData("Sheet2", i, 0); // username
            data[i - 1][1] = excelReader.getStringData("Sheet2", i, 1); // password
        }

        return data;
    }
}

