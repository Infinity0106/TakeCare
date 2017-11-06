package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrearBD {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL ="jdbc:derby:/Users/infinity0106/eclipse-workspace/takeCareDB;create=true";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean residente=true,
				enfermedades=true,
				opreaciones=true,
				domicilio=true,
				familiar=true,
				medicamento=true,
				historial=true,
				servEme=true,
				servHosp=true;
		
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
			if(res.getString("NAME").equals("SERVICIOHOSPITALARIO")) servHosp=false; 
			if(res.getString("NAME").equals("DOMICILIO")) domicilio=false;
			if(res.getString("NAME").equals("ENFERMEDAD")) enfermedades=false;
			if(res.getString("NAME").equals("FAMILIAR")) familiar=false;
			if(res.getString("NAME").equals("HISTORIAL")) historial=false;
			if(res.getString("NAME").equals("MEDICAMENTO")) medicamento=false;
			if(res.getString("NAME").equals("OPERACION")) opreaciones=false;
			if(res.getString("NAME").equals("RESIDENTE")) residente=false;
			if(res.getString("NAME").equals("SERVICIOEMERGENCIA")) servEme=false;
		}
		
		if(domicilio)
			con.createStatement().execute("create table Domicilio(\n" + 
					"	domicilioID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	calle varchar(75),\n" + 
					"	colonia varchar(30),\n" + 
					"	estado varchar(30),\n" + 
					"	municipio varchar(20),\n" + 
					"	pais varchar(20)\n" + 
					")");
		
		if(residente)
			con.createStatement().execute("create table Residente(\n" + 
					"	residenteID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	nombre varchar(50),\n" + 
					"	fechaNacimiento date,\n" + 
					"	Sexo char(1),\n" + 
					"	Estatus varchar(20),\n" + 
					"	fotoUrl varchar(100),\n" + 
					"	lugar integer,\n" + 
					"	cama integer\n" + 
					")");
		
		if(historial)
			con.createStatement().execute("create table Historial(\n" + 
					"	historialID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	residenteID integer,\n" + 
					"	foreign key (residenteID) references Residente(residenteID)\n" + 
					")");
		
		if(enfermedades)
			con.createStatement().execute("create table Enfermedad(\n" + 
					"	enfermedadID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	historialID integer,\n" + 
					"	nombre varchar(50),\n" + 
					"	fecha date,\n" + 
					"	sintomas varchar(50),\n" + 
					"	tratamiento varchar(50),\n" + 
					"	foreign key (historialID) references Historial(historialID)\n" + 
					")");
		
		if(familiar)
			con.createStatement().execute("create table Familiar(\n" + 
					"	familiarID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	residenteID integer,\n" + 
					"	nombre varchar(50),\n" + 
					"	correo varchar(50),\n" + 
					"	telefono varchar(20),\n" + 
					"	domicilioID integer,\n" + 
					"	foreign key (residenteID) references Residente(residenteID),\n" + 
					"	foreign key (domicilioID) references Domicilio(domicilioID)\n" + 
					")");
		
		if(medicamento)
			con.createStatement().execute("create table Medicamento(\n" + 
					"	medicamentoID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	residenteID integer,\n" + 
					"	nombre varchar(50),\n" + 
					"	fechaVencimiento date,\n" + 
					"	presentacion varchar(20),\n" + 
					"	cantidad int,\n" + 
					"	posologia varchar(100),\n" + 
					"	vecesDia varchar(50),\n" + 
					"	usoTerapeutico varchar(20),\n" + 
					"	foreign key (residenteID) references Residente(residenteID)\n" + 
					")");
		
		if(opreaciones)
			con.createStatement().execute("create table Operacion( \n" + 
					"	operacionID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	historialID integer,	\n" + 
					"	nombre varchar(50),\n" + 
					"	fecha date,\n" + 
					"	tipo varchar(20),\n" + 
					"	foreign key (historialID) references Historial(historialID)\n" + 
					")");
		
		if(servEme)
			con.createStatement().execute("create table ServicioEmergencia(\n" + 
					"	servicioEmeID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	residenteID integer,\n" + 
					"	clave varchar(50),\n" + 
					"	telefono varchar(20),\n" + 
					"	foreign key (residenteID) references Residente(residenteID)\n" + 
					")");
		
		if(servHosp)
			con.createStatement().execute("create table ServicioHospitalario(\n" + 
					"	servicioHospID integer not null generated always as identity (start with 1, increment by 1) primary key,\n" + 
					"	residenteID integer,\n" + 
					"	domicilioID integer,\n" + 
					"	nombre varchar(50),\n" + 
					"	telefono varchar(50),\n" + 
					"	foreign key (residenteID) references Residente(residenteID),\n" + 
					"	foreign key (domicilioID) references Domicilio(domicilioID)\n" + 
					")");
		
		
		System.out.println("si se genero la base de datos...");
		con.close();
	}
}
