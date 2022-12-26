package seleniumByNikhil.Testcases;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNgintigratedToExcelThrowDataProvider {

	DataFormatter formater = new DataFormatter();

	@Test(dataProvider = "datapass")
	public void GetExcelDat(String Greeting, String communication, String id) {
		System.out.println(Greeting + communication + id);

	}

	@DataProvider(name = "datapass")
	public Object[][] getDataFromExcel() throws IOException, InvalidFormatException {

		// FileInputStream fis = new
		// FileInputStream("./src/test/resources/TestNGDataProviderIntigratedTestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(
				new File("./src/test/resources/TestNGDataProviderIntigratedTestData.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow firstRow = sheet.getRow(0);

		short columnCount = firstRow.getLastCellNum();

		Object[][] data = new Object[rowCount - 1][columnCount];

		for (int i = 0; i < rowCount - 1; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < columnCount; j++) {

				XSSFCell cell = row.getCell(j);

				data[i][j] = formater.formatCellValue(cell);
			}
		}
		return data;
	}

	public Object[][] GetDataFromExcel1(String testcasename) throws InvalidFormatException, IOException {
		Object[][] data = null;
		XSSFWorkbook workbook = new XSSFWorkbook(new File(""));
		int noofsheets = workbook.getNumberOfSheets();
		for (int i = 0; i < noofsheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(testcasename)) {

				XSSFSheet sheet = workbook.getSheetAt(i);

				int rowcount = sheet.getPhysicalNumberOfRows();
				XSSFRow firstRow = sheet.getRow(0);
				short cellcount = firstRow.getLastCellNum();

				data = new Object[rowcount - 1][cellcount];

				for (int j = 0; j < rowcount - 1; j++) {

					XSSFRow row = sheet.getRow(j + 1);

					for (int k = 0; k < cellcount; k++) {

						XSSFCell cell = row.getCell(j);
						data[j][k] = formater.formatCellValue(cell);

					}

				}

			}

		}
		return data;
	}

	public void GetDataFromExcel2(String sheetname)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		File file = new File("");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet(sheetname);

		int rowcount = sheet.getPhysicalNumberOfRows();
		int cellcount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowcount - 1][cellcount];

		for (int row = 0; row < rowcount - 1; row++) {
			Row actRow = sheet.getRow(row);

			for (int cell = 0; cell < actRow.getLastCellNum(); cell++) {

				obj[row][cell] = actRow.getCell(cell).toString();

			}
		}

	}

}
