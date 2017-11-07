package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Emergencia;

public class Emergencias extends AdministrarConecciones{
	public Emergencia emergenciaInsert(Emergencia eme, int idRes) {
		try {
			String query="insert into APP.SERVICIOEMERGENCIA(residenteID, clave, telefono) values\n" + 
					"	("+idRes+",'"+eme.Clave+"','"+eme.Telefono+"')";
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select servicioemeid from APP.SERVICIOEMERGENCIA\n" + 
						"	where clave='"+eme.Clave+"' and telefono='"+eme.Telefono+"'";
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						eme.IDEmergencia=(Integer) new Integer(resul.getString("servicioemeid").toString());
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			eme.IDEmergencia=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			eme.IDEmergencia=-1;
		}
		return eme;
	}

}
