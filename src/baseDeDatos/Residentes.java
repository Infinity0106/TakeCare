package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDatos.AdministrarConecciones;
import modulos.Medicamento;
import modulos.Residente;

public class Residentes extends AdministrarConecciones {
	
	public Residente residenteSelect(int id) {
		Residente tmp = new Residente();
		try {
			ResultSet res = openConection().createStatement().executeQuery("select * from app.residente where residenteid="+id);
			if(res!=null) {
				//informacion basica
				while(res.next()) {
					tmp.Nombre=res.getString("NOMBRE").toString();
					tmp.IDResidente=(Integer) new Integer(res.getString("RESIDENTEID").toString());
					tmp.FechaNacimiento=res.getString("FECHANACIMIENTO").toString();
					tmp.Sexo=res.getString("SEXO").toString().charAt(0);
					tmp.Cama=(Integer) new Integer(res.getString("CAMA").toString());
					tmp.Lugar=(Integer) new Integer(res.getString("LUGAR").toString());
					tmp.Estatus=res.getString("ESTATUS").toString();
					tmp.FotoUrl=res.getString("FOTOURL").toString();
				}
				//servicio emergencia
				tmp.EmergenciaMedica= new Emergencias().emergenciasSelect(tmp.IDResidente);
				//servicio hospitalario
				tmp.ServicioHospital = new Hospitales().hospitalesSelect(tmp.IDResidente);
				//familiares
				tmp.Familiares = new Familiares().familiaresSelect(tmp.IDResidente);
				//enf y operaciones
				tmp.HistorialEstadia = new Historiales().historialesSelect(tmp.IDResidente);
				tmp.HistorialEstadia.Operaciones = new Operaciones().operacionesSelect(tmp.HistorialEstadia.IDHistorial);
				tmp.HistorialEstadia.Enfermedades = new Enfermedades().enfermedadesSelect(tmp.HistorialEstadia.IDHistorial);
				//eventos
				tmp.Eventos = new Eventos().eventosSelect(tmp.IDResidente);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmp;
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
	
	public ArrayList<Residente> residentesRecordatorio(String fecha, String dosis) {
		ArrayList<Residente> tmp = new ArrayList<Residente>();
		//TODO
		return tmp;
	}
	
	public ArrayList<Residente> residenteInventario() throws SQLException, ClassNotFoundException{
		int index=0;
		Boolean once=true;
		ResultSet res = openConection().createStatement().executeQuery("select APP.RESIDENTE.nombre, APP.MEDICAMENTO.nombre as nombremed, APP.MEDICAMENTO.vecesDia, APP.MEDICAMENTO.posologia\n" + 
				"	from APP.RESIDENTE join APP.MEDICAMENTO on APP.RESIDENTE.residenteID = APP.MEDICAMENTO.residenteID\n" + 
				"	order by APP.RESIDENTE.nombre");
		ArrayList<Residente> value = new ArrayList<Residente>();
		if(res!=null) {
			while(res.next()) {
				String nombre = res.getString("NOMBRE").toString();
				if(once) {
					System.out.println("entro");
					value.add(new Residente());
					value.get(index).Nombre=nombre;
					value.get(index).Medicamentos=new ArrayList<Medicamento>();
					once=false;
				}
				
				Medicamento tmp = new Medicamento();
				tmp.Nombre=res.getString("NOMBREMED").toString();
				tmp.VecesDia=res.getString("VECESDIA").toString();
				tmp.Posologia=(Integer) new Integer(res.getString("POSOLOGIA").toString());
				value.get(index).Medicamentos.add(tmp);
				
				
				if(value.get(index).Nombre.compareTo(res.getString("NOMBRE").toString())!=0) {
					System.out.println("entro diferne");
					index++;
					value.add(new Residente());
					value.get(index).Nombre=res.getString("NOMBRE").toString();
					value.get(index).Medicamentos=new ArrayList<Medicamento>();
				}
				System.out.println(index);
				
			}
				
		}
		return value;
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
