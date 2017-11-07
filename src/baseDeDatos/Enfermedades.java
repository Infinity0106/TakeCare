package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Enfermedad;

public class Enfermedades extends AdministrarConecciones{
	public Enfermedad enfermedadInsert(Enfermedad enf, int idHis) {
		try {
			String query ="insert into APP.ENFERMEDAD(historialID,nombre,fecha,sintomas,tratamiento) values\n" + 
					"	("+idHis+",'"+enf.Nombre+"','"+enf.Fecha+"','"+enf.Sintomas+"','"+enf.Tratamiento+"')";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select enfermedadid from APP.ENFERMEDAD\n" + 
						"	where nombre='"+enf.Nombre+"' and fecha='"+enf.Fecha+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						enf.IDEnfermedad=(Integer) new Integer(resul.getString("enfermedadid").toString());
					}
				}
			}else {
				enf.IDEnfermedad=-1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			enf.IDEnfermedad=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			enf.IDEnfermedad=-1;
		}
		
		return enf;
	}
}
