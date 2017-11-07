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
}
