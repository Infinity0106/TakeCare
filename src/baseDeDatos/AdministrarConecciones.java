package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministrarConecciones {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL ="jdbc:derby:/Users/infinity0106/eclipse-workspace/takeCareDB;create=true";
	
	private Connection con;
	
	public Connection openConection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(JDBC_URL);
		
		return this.con;
	}
	
	public void closeConnection() throws SQLException {
		this.con.close();
	}

}
