package jdbc_create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppMain {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		try {
			// Step 1: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully....");
			// STEP 2: Open a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			System.out.println("Connection created successfully....");
			String query = "Create table emp(eId int not null,eName varchar(30) not null,eSal int not null,primary key(eId))";

			statement = connection.createStatement();
			// STEP 3: Execute a query
			int x = statement.executeUpdate(query);
			System.out.println("table created successfully...." + x);
			// STEP 4: we will insert records into table
			String q1 = "insert into emp values(11,'Kamini',25000)";
			String q2 = "insert into emp values(12,'Mayuri',35000)";
			String q3 = "insert into emp values(13,'Kajal',28000)";
			// execute a query to insert records
			statement.executeUpdate(q1);
			statement.executeUpdate(q2);
			statement.executeUpdate(q3);
			System.out.println("records inserted successfully...");
			// STEP 5: Extract data from result set
			String sql = "select * from emp";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("eId");
				String name = rs.getString("eName");
				int salary = rs.getInt("eSal");
				// Display values
				System.out.print("ID: " + id);
				System.out.print(", name: " + name);
				System.out.println(", salary: " + salary);

			}
			System.out.println("records retrieved successfully...");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("connecton closed successfully...");
			}
		}
	}

}
