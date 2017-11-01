package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddResidenteDB {
//	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL ="jdbc:derby:/Users/infinity0106/eclipse-workspace/takeCareDB;create=true";
	private Connection con;
	private Statement sta;
	public AddResidenteDB() throws ClassNotFoundException, SQLException {
		con = DriverManager.getConnection(JDBC_URL);
		sta = con.createStatement();
	}
	
	public boolean insertPerson(String nombre, String dia, String estatus, String emergencia, String foto, String piso, String cama) {
		try {
			sta.executeUpdate("insert into RESIDENTE"
					+ "(nombre, fechaNacimiento, estatus, servicioEmergancias, foto, lugar, cama) "
					+ "values "
					+ "('"+nombre+"',"+dia+", '"+estatus+"','"+emergencia+"','/user/','01','27')");
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void killConnection() throws SQLException {
		con.close();
	}
}
