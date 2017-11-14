package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDatos.AdministrarConecciones;
import modulos.Medicamento;

public class Medicamentos extends AdministrarConecciones {
	
	public ArrayList<Medicamento> medicamentosSelect(int idRes) {
		ArrayList<Medicamento> tmp = new ArrayList<Medicamento>();
		
		String query = "select * from APP.MEDICAMENTO where residenteID="+idRes;
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery(query);
			if(res!=null) {
				while(res.next()) {
					Medicamento tmpMed = new Medicamento();
					tmpMed.IDMedicamento=(Integer) new Integer(res.getString("MEDICAMENTOID").toString());
					tmpMed.Nombre = res.getString("NOMBRE").toString();
					tmpMed.FechaVencimiento = res.getString("FECHAVENCIMIENTO").toString();
					tmpMed.Presentacion = res.getString("PRESENTACION").toString();
					tmpMed.Posologia =(Integer) new Integer(res.getString("POSOLOGIA").toString());
					tmpMed.Cantidad = (Integer) new Integer(res.getString("CANTIDAD").toString());
					tmpMed.VecesDia = res.getString("VECESDIA").toString();
					
					tmp.add(tmpMed);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tmp;
	}

	public Boolean medicamentosUpdate(int medID, int resID, char type) {
		Boolean value=false;
		if(type=='+') {
			try {
				String query="update APP.MEDICAMENTO set\n" + 
						"	cantidad=cantidad+1\n" + 
						"	where medicamentoid="+medID+" and residenteID="+resID;
				System.out.println(query);
				PreparedStatement preS = openConection().prepareStatement(query);
				
				value=(preS.executeUpdate()>0?true:false);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				String query="update APP.MEDICAMENTO set\n" + 
						"	cantidad=cantidad-1\n" + 
						"	where medicamentoid="+medID+" and residenteID="+resID;
				System.out.println(query);
				PreparedStatement preS = openConection().prepareStatement(query);
				
				value=(preS.executeUpdate()>0?true:false);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return value;
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
