package Database;

import java.sql.*;

public class ConnessioneDatabase {

	
	private static ConnessioneDatabase instance;
	private Connection connection = null;
	private String nome ;
	private String password ;
	private String url ;
	private String driver ;

	
	public ConnessioneDatabase() throws SQLException {
		nome="postgres";
		password= "postgres";
		url="jdbc:postgresql://80.211.141.136:5432/E-Learning Platform DB";
		driver="org.postgresql.Driver";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nome, password);

		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Fallita: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public Connection getConnection() {
			return connection;
	}
	
	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessioneDatabase();
		} else if (instance.getConnection().isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}
}