package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/Seguranca", "root", "ThorAlburqs8819");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
