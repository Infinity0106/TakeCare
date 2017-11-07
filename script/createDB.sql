--create table Residente (
--	residenteID integer not null generated always as identity (start with 1, increment by 1) primary key,
--	nombre varchar(30),
--	fechaNacimiento date,
--	estatus varchar(20),
--	servicioEmergancias varchar(50),
--	foto varchar(100),
--	lugar varchar(10),
--	cama varchar(10)
--)
--
--drop table APP.ALERGIAS;
--drop table APP.ENFERMEDADES;
--drop table APP.FAMILIAR;
--drop table APP.MEDICAMENTO;
--drop table APP.OPERACIONES;
--drop table APP.RESIDENTE;
--
--select t.tablename  as name
--     from sys.systables t, sys.sysschemas s  
--     where t.schemaid = s.schemaid 
--          and t.tabletype = 'T' 
--     order by s.schemaname, t.tablename;
--
--
--create table Enfermedades(
--	id integer not null generated always as identity (start with 1, increment by 1) primary key,
--	resideteID int foreign key references Residente(residenteID),
--	nombre varchar(30),
--	sintomas varchar(200),
--	tratamiento varchar(200),
--)
--
--create table Operaciones(
--	id integer not null generated always as identity (start with 1, increment by 1) primary key,
--	resideteID int foreign key references Residente(residenteID),
--	fecha date,
--	tipo varchar(50)
--)
--
--create table Alergias(
--	id integer not null generated always as identity (start with 1, increment by 1) primary key,
--	resideteID int foreign key references Residente(residenteID),
--	nombre varchar(30),
--	sintomas varchar(200),
--	tratamiento varchar(200),
--)
--
--create table Familiar(
--	id integer not null generated always as identity (start with 1, increment by 1) primary key,
--	resideteID int foreign key references Residente(residenteID),
--	nombre varchar(50),
--	correo varchar(60),
--	telefono varchar(20),
--	relacion varchar(20),
--	domicilio varchar(50)
--)
--
--create table Medicamento(
--	id integer not null generated always as identity (start with 1, increment by 1) primary key,
--	resideteID int foreign key references Residente(residenteID),
--	nombre varchar (50),
--	cantidad int,
--	dosis varchar(30),
--	vecesDia varchar(30),
--	tipo varchar(30)
--)





create table Residente(
	residenteID integer not null generated always as identity (start with 1, increment by 1) primary key,
	nombre varchar(50),
	fechaNacimiento date,
	Sexo char(1),
	Estatus varchar(20),
	fotoUrl varchar(100),
	lugar integer,
	cama integer
);

create table Domicilio(
	domicilioID integer not null generated always as identity (start with 1, increment by 1) primary key,
	calle varchar(75),
	colonia varchar(30),
	estado varchar(30),
	municipio varchar(20),
	pais varchar(20)
);

create table Familiar(
	familiarID integer not null generated always as identity (start with 1, increment by 1) primary key,
	residenteID integer,
	nombre varchar(50),
	correo varchar(50),
	telefono varchar(20),
	domicilioID integer,
	foreign key (residenteID) references Residente(residenteID),
	foreign key (domicilioID) references Domicilio(domicilioID)
);

create table ServicioEmergencia(
	servicioEmeID integer not null generated always as identity (start with 1, increment by 1) primary key,
	residenteID integer,
	clave varchar(50),
	telefono varchar(20),
	foreign key (residenteID) references Residente(residenteID)
);

create table ServicioHospitalario(
	servicioHospID integer not null generated always as identity (start with 1, increment by 1) primary key,
	residenteID integer,
	domicilioID integer,
	nombre varchar(50),
	telefono varchar(50),
	foreign key (residenteID) references Residente(residenteID),
	foreign key (domicilioID) references Domicilio(domicilioID)
);

create table Historial(
	historialID integer not null generated always as identity (start with 1, increment by 1) primary key,
	residenteID integer,
	foreign key (residenteID) references Residente(residenteID)
);

create table Operacion( 
	operacionID integer not null generated always as identity (start with 1, increment by 1) primary key,
	historialID integer,	
	nombre varchar(50),
	fecha date,
	tipo varchar(20),
	foreign key (historialID) references Historial(historialID)
);

create table Enfermedad(
	enfermedadID integer not null generated always as identity (start with 1, increment by 1) primary key,
	historialID integer,
	nombre varchar(50),
	fecha date,
	sintomas varchar(50),
	tratamiento varchar(50),
	foreign key (historialID) references Historial(historialID)
);

create table Medicamento(
	medicamentoID integer not null generated always as identity (start with 1, increment by 1) primary key,
	residenteID integer,
	nombre varchar(50),
	fechaVencimiento date,
	presentacion varchar(20),
	cantidad int,
	posologia varchar(100),
	vecesDia varchar(50),
	foreign key (residenteID) references Residente(residenteID)
);

drop table APP.MEDICAMENTO;
drop table APP.ENFERMEDAD;
drop table APP.OPERACION;
drop table APP.HISTORIAL;
drop table APP.SERVICIOEMERGENCIA;
drop table APP.SERVICIOHOSPITALARIO;
drop table APP.DOMICILIO;
drop table APP.FAMILIAR;
drop table APP.RESIDENTE;
drop table APP.ALERGIAS;
drop table APP.ENFERMEDADES;
drop table APP.OPERACIONES;
drop table APP.RESIDENTE;

drop table ADMIN.MEDICAMENTO;
drop table ADMIN.ENFERMEDAD;
drop table ADMIN.OPERACION;
drop table ADMIN.HISTORIAL;
drop table ADMIN.SERVICIOEMERGENCIA;
drop table ADMIN.SERVICIOHOSPITALARIO;
drop table ADMIN.DOMICILIO;
drop table ADMIN.FAMILIAR;
drop table ADMIN.RESIDENTE;

select s.schemaname || '.' || t.tablename  
     from sys.systables t, sys.sysschemas s  
     where t.schemaid = s.schemaid 
          and t.tabletype = 'T' 
     order by s.schemaname, t.tablename;

select t.tablename  as name
     from sys.systables t, sys.sysschemas s  
     where t.schemaid = s.schemaid 
          and t.tabletype = 'T' 
     order by s.schemaname, t.tablename;








	insert into APP.RESIDENTE (nombre,fechanacimiento,sexo,estatus,fotourl,lugar,cama) values
     ('bernardo orozco','1996-06-01','M','Activo','C:\Users\Joel\Pictures\13923736.jpeg',1,1);    
	select residenteID, nombre, fotoUrl from app.residente;
     
     insert into APP.MEDICAMENTO (RESIDENTEID, NOMBRE, FECHAVENCIMIENTO, PRESENTACION, CANTIDAD, POSOLOGIA, VECESDIA, USOTERAPEUTICO) values
     (1,'parasetamol','2015-08-08','tabletas',20,'posologia','Dia|Tarde|Noche','usoterapeutico');
     select * from APP.MEDICAMENTO;

	
	select residenteID from app.residente
	where nombre='bernardo orozco' and fotoUrl='/Users/infinity0106/Pictures/13923736.jpeg'


	insert into APP.DOMICILIO(calle, colonia, estado, municipio, pais) values
	('Av.dest','Tec','mtu','nl','mxn');
	select * from APP.Domicilio;
	
	select domicilioid from app.domicilio
	where calle='Av.dest' and colonia='Tec';

	insert into APP.FAMILIAR (residenteID, nombre, correo, telefono, domicilioID) values
	(1,'pepe','papa@pepe.com','8661231234', 1)
	select * from app.familiar


