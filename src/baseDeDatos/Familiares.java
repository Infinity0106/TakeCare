package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modulos.Familiar;

public class Familiares extends AdministrarConecciones {
	public Familiar familiarInsert(Familiar fam, Integer idRes) {
		try {
			String query ="insert into APP.FAMILIAR (residenteID, nombre, correo, telefono, relacion, domicilioID) values\n" + 
					"	("+idRes+",'"+fam.Nombre+"','"+fam.Correo+"','"+fam.Telefono+"', '"+fam.Relacion+"', "+fam.Residencia.IDDomicilio+")";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select familiarid from app.familiar\n" + 
						"	where nombre='"+fam.Nombre+"' and correo='"+fam.Correo+"' and telefono='"+fam.Telefono+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						fam.IDFamiliar=(Integer) new Integer(resul.getString("familiarid").toString());
					}
				}
			}else {
				fam.IDFamiliar=-1;
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
	
	public ArrayList<Familiar> familiaresSelect(int id){
		ArrayList<Familiar> tmp = new ArrayList<Familiar>();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.FAMILIAR where residenteid="+id);
			if(res!=null) {
				while(res.next()) {
					Familiar tmpf = new Familiar();
					tmpf.IDFamiliar=(Integer) new Integer(res.getString("FAMILIARID").toString());
					tmpf.Nombre=res.getString("NOMBRE").toString();
					tmpf.Correo=res.getString("CORREO").toString();
					tmpf.Telefono=res.getString("TELEFONO").toString();
//					tmpf.Relacion=res.getString("relacion").toString();
					tmpf.Residencia=new Domicilios().domiciliosSelect((Integer) new Integer(res.getString("domicilioid").toString()));
					tmp.add(tmpf);
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
