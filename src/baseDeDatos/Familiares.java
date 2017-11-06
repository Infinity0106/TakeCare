package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Familiar;

public class Familiares extends AdministrarConecciones {
	public Familiar familiarInsert(Familiar fam, Integer idRes) {
		try {
			String query ="insert into APP.FAMILIAR (residenteID, nombre, correo, telefono, domicilioID) values\n" + 
					"	("+idRes+",'"+fam.Nombre+"','"+fam.Correo+"','"+fam.Telefono+"', "+fam.Residencia.IDDomicilio+")";
			
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select familiarid from app.familiar\n" + 
						"	where nombre='"+fam.Nombre+"' and corre='"+fam.Correo+"' and telefono='"+fam.Telefono+"'";
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						fam.IDFamiliar=(Integer) new Integer(resul.getString("familiarid").toString());
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			fam.IDFamiliar=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fam.IDFamiliar=-1;
		}
		
		return fam;
	}
}
