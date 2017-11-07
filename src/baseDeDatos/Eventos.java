package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Evento;

public class Eventos extends AdministrarConecciones{
	public Evento eventoInsert(Evento tmp, int idRes) {
		try {
			String query ="insert into APP.Evento(residenteID, enfermera, fecha, descripcion) values\n" + 
					"	("+idRes+",'"+tmp.enfermera+"','"+tmp.fecha+"','"+tmp.descripcion+"')";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select eventoID from APP.EVENTO\n" + 
						"	where residenteID="+idRes+" and enfermera='"+tmp.enfermera+"' and fecha='"+tmp.fecha+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						tmp.IDEvento=(Integer) new Integer(resul.getString("eventoID").toString());
					}
				}
			}else {
				tmp.IDEvento=-1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			tmp.IDEvento=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			tmp.IDEvento=-1;
		}
		return tmp;
	}
}
