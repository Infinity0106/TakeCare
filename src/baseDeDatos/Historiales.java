package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Historial;;

public class Historiales extends AdministrarConecciones {
	public Historial hospitalInsert(Historial tmp, int idRes) {
		try {
			String query="insert into APP.HISTORIAL(residenteID) values\n" + 
					"	("+idRes+")";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select historialID from APP.HISTORIAL\n" + 
						"	where residenteID="+idRes;
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						tmp.IDHistorial=(Integer) new Integer(resul.getString("historialID").toString());
					}
				}
			}else {
				tmp.IDHistorial=-1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			tmp.IDHistorial=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			tmp.IDHistorial=-1;
		}
		return tmp;
	}
}
