package databaseConnectionToTC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DatabaseConnectionExplained {
	@Test
	public void dataFromDatabase() throws SQLException {

		String host = "localhost";
		int port = 3306;

		Connection connect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/seleniumDataBase",
				"root", "root");

		Statement sqlQueryExecuter = connect.createStatement();


		ResultSet result = sqlQueryExecuter.executeQuery("select * from employeeTable where name='tam'");


		result.next();


		System.out.println(result.getString("name"));


		System.out.println(result.getString("location"));


		System.out.println(result.getInt("id"));

		
	}

}
