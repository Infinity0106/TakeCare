package baseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modulos.Evento;

public class Eventos extends AdministrarConecciones {
	public ArrayList<Evento> EventosSelectNombre() throws SQLException, ClassNotFoundException{
		ResultSet result = openConection().createStatement().executeQuery(""
				+ "select eventoID, residenteID, enfermera, fecha, descripcion "
				+ "from app.evento "
				+ "order by fecha");
		ArrayList<Evento> returnValue = new ArrayList<Evento>();
		if(result!=null) {
			while(result.next()) {
				Evento tmp = new Evento();
				tmp.eventoID = result.getInt("eventoID");
				tmp.residenteID = result.getInt("residenteID");
				tmp.enfermera = result.getString("enfermera");
				tmp.fecha = result.getString("fecha");
				tmp.descripcion = result.getString("descripcion");
				returnValue.add(tmp);
			}
		}
		closeConnection();
		return returnValue;
	}
	
	public Evento EventosInsertNombre(Evento eventoIn) throws SQLException, ClassNotFoundException {
		ResultSet result = openConection().createStatement().executeQuery(
				String.format("INSERT INTO app.evento (residenteID, enfermera, fecha, descripcion) "
				+ "VALUES ({0}, '{1}', '{2}', '{3}')", eventoIn.residenteID, eventoIn.enfermera, eventoIn.fecha, eventoIn.descripcion));
		if(result != null) {
			while(result.next()) {
				eventoIn.eventoID = result.getInt("eventoID");
			}
		}
		closeConnection();
		return eventoIn;
	}
}
