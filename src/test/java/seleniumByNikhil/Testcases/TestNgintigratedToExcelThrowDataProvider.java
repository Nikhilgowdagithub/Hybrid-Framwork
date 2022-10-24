package seleniumByNikhil.Testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNgintigratedToExcelThrowDataProvider {
	DataFormatter formater = new DataFormatter();

	@Test(dataProvider = "datapass")
	public void GetExcelDat(String Greeting,String communication, String id) {
		System.out.println(Greeting+communication+id);

	}

	@DataProvider(name = "datapass")
	public Object[][] GetDataFromExcel() throws IOException {

		FileInputStream fis = new FileInputStream(
				"./src/test/resources/TestNGDataProviderIntigratedTestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow firstRow = sheet.getRow(0);

		short columnCount = firstRow.getLastCellNum();

		Object[][] data = new Object[rowCount - 1][columnCount];

		for (int i = 0; i < rowCount-1; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < columnCount; j++) {

				XSSFCell cell = row.getCell(j);

				data[i][j] = formater.formatCellValue(cell);
			}
		} 
		return data;
	}

}
