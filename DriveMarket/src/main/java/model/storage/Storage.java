package model.storage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Storage  {

//	private static List<Connection> freeDbConnections;
	private static Connection connection;

//	static {
//		freeDbConnections = new LinkedList<Connection>();
//		
//	}
	
	public static synchronized Connection getConnection() throws SQLException {
		if(connection == null) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("DB driver not found:"+ e.getMessage());
			} 
			
			String ip = "localhost";
			String port = "3306";
			String db = "TSW";
			String username = "antonio";
			String password = "antonio";

			connection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			connection.setAutoCommit(false);
		}
		
		return connection;
	}


//	public static synchronized Connection getConnection() throws SQLException {
//		Connection connection;
//
//		if (!freeDbConnections.isEmpty()) {
//			connection = (Connection) freeDbConnections.get(0);
//			freeDbConnections.remove(0);
//
//			try {
//				if (connection.isClosed())
//					connection = getConnection();
//			} catch (SQLException e) {
//				connection.close();
//				connection = getConnection();
//			}
//		} else {
//			connection = createDBConnection();		
//		}
//
//		return connection;
//	}
//
//	public static synchronized void releaseConnection(Connection connection) throws SQLException {
//		if(connection != null) freeDbConnections.add(connection);
//	}
}