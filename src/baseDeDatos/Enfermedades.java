package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Enfermedad> enfermedadesSelect(int id){
		ArrayList<Enfermedad> tmp = new ArrayList<Enfermedad>();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.ENFERMEDAD where historialid="+id);
			if(res!=null) {
				while(res.next()) {
					Enfermedad tmpe = new Enfermedad();
					tmpe.Nombre=res.getString("NOMBRE").toString();
					tmpe.Fecha=res.getString("FECHA").toString();
					tmpe.Sintomas=res.getString("SINTOMAS").toString();
					tmpe.Tratamiento=res.getString("TRATAMIENTO").toString();
					tmpe.IDEnfermedad=(Integer) new Integer(res.getString("ENFERMEDADID").toString());
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
