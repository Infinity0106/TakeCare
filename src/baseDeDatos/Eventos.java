package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Evento> eventosSelect(int id){
		ArrayList<Evento> tmp = new ArrayList<Evento>();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.EVENTO where residenteid="+id);
			if(res!=null) {
				while(res.next()) {
					Evento tmpe = new Evento();
					tmpe.enfermera=res.getString("enfermera").toString();
					tmpe.fecha=res.getString("FECHA").toString();
					tmpe.descripcion=res.getString("descripcion").toString();
					tmpe.IDEvento=(Integer) new Integer(res.getString("eventoID").toString());
					tmp.add(tmpe);
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
