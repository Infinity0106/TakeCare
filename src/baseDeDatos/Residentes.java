package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDatos.AdministrarConecciones;
import modulos.Residente;

public class Residentes extends AdministrarConecciones {
	
	public void residenteSelect() {
		
	}
	
	public ArrayList<Residente> residenteSelectNombre() throws SQLException, ClassNotFoundException {
		ResultSet res = openConection().createStatement().executeQuery("select residenteID, nombre, fotoUrl from app.residente order by nombre");
		ArrayList<Residente> value = new ArrayList<Residente>();
		if(res!=null) {
			while(res.next()) {
				Residente tmp = new Residente();
				tmp.Nombre=res.getString("nombre").toString();
				tmp.IDResidente=(Integer) new Integer(res.getString("residenteID").toString());
				tmp.FotoUrl=res.getString("fotoUrl").toString();
				value.add(tmp);
			}
		}
		return value;
	}

	public void residenteUpdate() {
		
	}
	
	public Residente residenteInsert(Residente res) {
		try {
			String query = "insert into APP.RESIDENTE (nombre,fechanacimiento,sexo,estatus,fotourl,lugar,cama) values\n" + 
					"     ('"+res.Nombre+"','"+res.FechaNacimiento+"','"+res.Sexo+"','Activo','"+res.FotoUrl+"',"+res.Lugar+","+res.Cama+")";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select residenteID from app.residente\n" + 
						"	where nombre='"+res.Nombre+"' and fotoUrl='"+res.FotoUrl+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						res.IDResidente=(Integer) new Integer(resul.getString("residenteID").toString());
					}
				}
			}else {
				res.IDResidente=-1;
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
			res.IDResidente=-1;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			res.IDResidente=-1;
		}
 
		
		return res;
	}
}
