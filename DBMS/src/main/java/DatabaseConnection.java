import java.sql.*;

// This class can be used to initialize the database connection
public class DatabaseConnection {
	protected static Connection initializeDatabase()
		throws SQLException, ClassNotFoundException
	{
		// Initialize all the information regarding
		// Database Connection
		String dbDriver = "com.mysql.jdbc.Driver";

		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","sameer");
		return con;
	}
}
