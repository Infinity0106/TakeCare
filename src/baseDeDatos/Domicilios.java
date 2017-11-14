package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modulos.Domicilio;

public class Domicilios extends AdministrarConecciones {
	public Domicilio domicilioInsert(Domicilio dom) {
		try {
			String query ="insert into APP.DOMICILIO(calle, colonia, estado, municipio, pais) values\n" + 
					"	('"+dom.Calle+"','"+dom.Colonia+"','"+dom.Estado+"','"+dom.Municipio+"','"+dom.Pais+"')";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select domicilioid from app.domicilio\n" + 
						"	where calle='"+dom.Calle+"' and colonia='"+dom.Colonia+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						dom.IDDomicilio=(Integer) new Integer(resul.getString("domicilioid").toString());
					}
				}
			}else {
				dom.IDDomicilio=-1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			dom.IDDomicilio=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			dom.IDDomicilio=-1;
		}
		
		return dom;
	}
	
	public Domicilio domiciliosSelect(int id) {
		Domicilio tmp = new Domicilio();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.DOMICILIO where domicilioid="+id);
			if(res!=null) {
				while(res.next()) {
					tmp.IDDomicilio=(Integer) new Integer(res.getString("DOMICILIOID").toString());
					tmp.Calle=res.getString("CALLE").toString();
					tmp.Colonia=res.getString("COLONIA").toString();
					tmp.Estado=res.getString("ESTADO").toString();
					tmp.Municipio=res.getString("MUNICIPIO").toString();
					tmp.Pais=res.getString("PAIS").toString();
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
