package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modulos.Operacion;

public class Operaciones extends AdministrarConecciones{
	public Operacion operacionInsert(Operacion op, int idHis) {
		try {
			String query ="insert into APP.OPERACION(historialID,nombre,fecha,tipo) values\n" + 
					"	("+idHis+",'"+op.Nombre+"','"+op.Fecha+"','"+op.Tipo+"')";
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(preS.executeUpdate()>0) {
				query = "select operacionID from APP.OPERACION\n" + 
						"	where nombre='"+op.Nombre+"' and fecha='"+op.Fecha+"'";
				System.out.print(query);
				ResultSet resul = openConection().createStatement().executeQuery(query);
				if(resul!=null) {
					while(resul.next()) {
						op.IDOperacion=(Integer) new Integer(resul.getString("operacionID").toString());
					}
				}
			}else {
				op.IDOperacion=-1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			op.IDOperacion=-1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			op.IDOperacion=-1;
		}
		return op;
	}
	
	public ArrayList<Operacion> operacionesSelect(int id){
		ArrayList<Operacion> tmp = new ArrayList<Operacion>();
		ResultSet res;
		try {
			res = openConection().createStatement().executeQuery("select * from APP.OPERACION where historialid="+id);
			if(res!=null) {
				while(res.next()) {
					Operacion tmpo = new Operacion();
					tmpo.Nombre=res.getString("NOMBRE").toString();
					tmpo.Fecha=res.getString("FECHA").toString();
					tmpo.Tipo=res.getString("TIPO").toString();
					tmpo.IDOperacion=(Integer) new Integer(res.getString("OPERACIONID").toString());
					tmp.add(tmpo);
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
