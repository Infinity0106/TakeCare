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
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select servicioemeid from APP.SERVICIOEMERGENCIA\n" + 
						"	where clave='"+eme.Clave+"' and telefono='"+eme.Telefono+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						eme.IDEmergencia=(Integer) new Integer(resul.getString("servicioemeid").toString());
					}
				}
			}else {
				eme.IDEmergencia=-1;
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

	public Emergencia emergenciasSelect(int id) {
		Emergencia tmp = new Emergencia();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.SERVICIOEMERGENCIA where residenteid="+id);
			if(res!=null) {
				while(res.next()) {
					tmp.IDEmergencia=(Integer) new Integer(res.getString("SERVICIOEMEID").toString());
					tmp.Clave=res.getString("CLAVE").toString();
					tmp.Telefono=res.getString("TELEFONO").toString();
				}
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tmp;
	}
}
