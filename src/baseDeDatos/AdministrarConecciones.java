package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministrarConecciones {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL ="jdbc:derby:C:\\Users\\Joel\\TakeCareDB;create=true";
	
	private static Connection con = null;
	
	public Connection openConection() throws ClassNotFoundException, SQLException {
		if(con==null) {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(JDBC_URL);
		}
		
		return con;
	}
	
	public void closeConnection() throws SQLException {
		if(con!=null) {
			con.close();
			con=null;
		}
	}

}
