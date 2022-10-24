package databaseConnectionToTC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import generalUtilitys.Baseclass;

public class DataDrivenFromDatabaseTC extends Baseclass {
	String rowname = "tam";
	String name = "name";
	String loc = "location";

	@Test
	public void name() throws SQLException {
		DatabaseConnection dc = new DatabaseConnection();
		ResultSet result = dc.dataDrivenFromDatabase(rowname);
		driver.findElement(By.id("userEmail")).sendKeys(result.getString(name));
		driver.findElement(By.id("userPassword")).sendKeys(result.getString(loc));

	}

}
