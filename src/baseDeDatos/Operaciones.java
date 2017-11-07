package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
