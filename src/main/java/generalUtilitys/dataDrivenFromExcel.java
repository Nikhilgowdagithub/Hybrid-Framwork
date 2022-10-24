package generalUtilitys;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDrivenFromExcel {

	public ArrayList<String> getData(String testCaseName) throws IOException {

		ArrayList<String> arraylist = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(
				"./src/test/resources/endToEndFrameworkExelData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();// rows is collection of cells
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int K = 0;
				int column = 0;
				while (cells.hasNext()) {
					Cell data = cells.next();
					if (data.getStringCellValue().equalsIgnoreCase(testCaseName)) {
						column = K;
					}
					K++;
				}
				while (rows.hasNext()) {
					Row ro = rows.next();
					if (ro.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> ce = ro.cellIterator();
						while (ce.hasNext()) {
							Cell data = ce.next();
							if (data.getCellTypeEnum() == CellType.STRING) {
								arraylist.add(data.getStringCellValue());

							} else {
								arraylist.add(NumberToTextConverter.toText((data.getNumericCellValue())));

							}
						}

					}

				}

			}

		}
		return arraylist;

	}

}
