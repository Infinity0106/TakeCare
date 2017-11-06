package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class createDB {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL ="jdbc:derby:/Users/infinity0106/eclipse-workspace/takeCareDB;create=true";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean residente=true,enfermedades=true,opreaciones=true,alergias=true,familiar=true,medicamento=true;
		
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(JDBC_URL);
		
//		checar si ya exsiten las tablas
		ResultSet res;
		res = con.createStatement().executeQuery("select t.tablename  as name\n" + 
				"     from sys.systables t, sys.sysschemas s  \n" + 
				"     where t.schemaid = s.schemaid \n" + 
				"          and t.tabletype = 'T' \n" + 
				"     order by s.schemaname, t.tablename");
		
		while(res.next()) {
			if(res.getString("NAME").equals("ALERGIAS")) alergias=false;
			if(res.getString("NAME").equals("ENFERMEDADES")) enfermedades=false;
			if(res.getString("NAME").equals("FAMILIAR")) familiar=false;
			if(res.getString("NAME").equals("MEDICAMENTO")) medicamento=false;
			if(res.getString("NAME").equals("OPERACIONES")) opreaciones=false;
			if(res.getString("NAME").equals("RESIDENTE")) residente=false;
		}
		
//		if(residente) {
//		con.createStatement().execute("create table Residente (\n" + 
//				"	residenteID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
//				"	nombre varchar(30),\n" + 
//				"	fechaNacimiento date,\n" + 
//				"	estatus varchar(20),\n" + 
//				"	servicioEmergancias varchar(50),\n" + 
//				"	foto varchar(100),\n" + 
//				"	lugar varchar(10),\n" + 
//				"	cama varchar(10)\n" + 
//				")");
//		}
//		
//		if(enfermedades) {
//		con.createStatement().execute("create table Enfermedades ("
//				+ "id integer not null generated always as identity (start with 1, increment by 1) primary key,"
//				+ "residenteID integer references Residente(residenteID),"
//				+ "nombre varchar(30),"
//				+ "sintomas varchar(200),"
//				+ "tratamiento varchar(200)"
//				+ ")");
//		}
//		
//		if(opreaciones) {
//		con.createStatement().execute("create table Operaciones (\n" + 
//				"	id integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
//				"	resideteID integer references Residente(residenteID),\n" + 
//				"	fecha date,\n" + 
//				"	tipo varchar(50)\n" + 
//				")");
//		}
//		
//		if(alergias) {
//		con.createStatement().execute("create table Alergias ("
//				+ "id integer not null generated always as identity(start with 1, increment by 1) primary key,"
//				+ "residenteID integer references Residente(residenteID),"
//				+ "nombre varchar(30),"
//				+ "sintomas varchar(200),"
//				+ "tratamiento varchar(200)"
//				+ ")");
//		}
//		
//		if(familiar) {
//		con.createStatement().execute("create table Familiar (\n" + 
//				"	id integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
//				"	resideteID integer references Residente(residenteID),\n" + 
//				"	nombre varchar(50),\n" + 
//				"	correo varchar(60),\n" + 
//				"	telefono varchar(20),\n" + 
//				"	relacion varchar(20),\n" + 
//				"	domicilio varchar(50)\n" + 
//				")");
//		}
//		
//		if(medicamento) {
//		con.createStatement().execute("create table Medicamento (\n" + 
//				"	id integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
//				"	resideteID integer references Residente(residenteID),\n" + 
//				"	nombre varchar (50),\n" + 
//				"	cantidad int,\n" + 
//				"	dosis varchar(30),\n" + 
//				"	vecesDia varchar(30),\n" + 
//				"	tipo varchar(30)\n" + 
//				")");
//		}
		
		
		
		System.out.println("si se genero la base de datos...");
		con.close();
	}
}
