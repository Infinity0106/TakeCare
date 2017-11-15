package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Hospital;

public class Hospitales extends AdministrarConecciones {
	public Hospital hospitalesInsert(Hospital hos,int idRes) {
		try {
			String query = "insert into app.serviciohospitalario(residenteID, domicilioID, nombre, telefono) values\n" + 
					"	("+idRes+","+hos.Direccion.IDDomicilio+",'"+hos.Nombre+"','"+hos.Telefono+"')";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select serviciohospid from app.serviciohospitalario\n" + 
						"	where nombre='"+hos.Nombre+"' and telefono='"+hos.Telefono+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						hos.IDHospital=(Integer) new Integer(resul.getString("serviciohospid").toString());
					}
				}
			}else {
				hos.IDHospital=-1;
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
			hos.IDHospital=-1;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			hos.IDHospital=-1;
		}
		return hos;
	}
	
	public Boolean hospitalesUpdate(Hospital hosp) {
		Boolean respu=false;
		try {
			String query = "update APP.SERVICIOHOSPITALARIO set\n" + 
					"	nombre='"+hosp.Nombre+"',telefono='"+hosp.Telefono+"'\n" + 
					"	where servicioHospID="+hosp.IDHospital;
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(!(preS.executeUpdate()>0)) {
				respu=false;
			}else {
				respu=true;
			}
			if(!new Domicilios().domiciliosUpdate(hosp.Direccion)) {
				respu=false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			respu=false;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			respu=false;
		}
		
		return respu;
	}
	
	public Hospital hospitalesSelect(int id) {
		Hospital tmp = new Hospital();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.SERVICIOHOSPITALARIO where residenteid="+id);
			if(res!=null) {
				while(res.next()) {
					tmp.Nombre=res.getString("NOMBRE").toString();
					tmp.Telefono=res.getString("TELEFONO").toString();
					tmp.IDHospital=(Integer) new Integer(res.getString("SERVICIOHOSPID").toString());
					tmp.Direccion = new Domicilios().domiciliosSelect((Integer) new Integer(res.getString("DOMICILIOID").toString()));
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
