package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import baseDeDatos.AdministrarConecciones;

public class Medicamentos extends AdministrarConecciones {
	
	public void medicamentosSelect() {
		
	}

	public void medicamentosUpdate() {
		
	}
	
	public Boolean medicamentosInsert(int id, String nom, String fe, String pre, int cant, String pos, String vec) {
		Boolean value;
		try {
			String query ="insert into APP.MEDICAMENTO (RESIDENTEID, NOMBRE, FECHAVENCIMIENTO, PRESENTACION, CANTIDAD, POSOLOGIA, VECESDIA) values"
					+ "("+id+",'"+nom+"','"+fe+"','"+pre+"',"+cant+",'"+pos+"','"+vec+"')";
			System.out.println(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			
			value=(preS.executeUpdate()>0?true:false);
		} catch (SQLException e) {
			e.printStackTrace();
			value= false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			value= false;
		}
		return value;
	}
}
