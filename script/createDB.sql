create table Residente (
	residenteID integer not null generated always as identity (start with 1, increment by 1) primary key,
	nombre varchar(30),
	fechaNacimiento date,
	estatus varchar(20),
	servicioEmergancias varchar(50),
	foto varchar(100),
	lugar varchar(10),
	cama varchar(10)
)

select t.tablename  as name
     from sys.systables t, sys.sysschemas s  
     where t.schemaid = s.schemaid 
          and t.tabletype = 'T' 
     order by s.schemaname, t.tablename;
--insert into RESIDENTE(nombre, fechaNacimiento, estatus, servicioEmergancias, foto, lugar, cama) values ('bernardo orozco',CURRENT_DATE, 'residente','123','/user/','01','27');

create table Enfermedades(
	id integer not null generated always as identity (start with 1, increment by 1) primary key,
	resideteID int foreign key references Residente(residenteID),
	nombre varchar(30),
	sintomas varchar(200),
	tratamiento varchar(200),
)

create table Operaciones(
	id integer not null generated always as identity (start with 1, increment by 1) primary key,
	resideteID int foreign key references Residente(residenteID),
	fecha date,
	tipo varchar(50)
)

create table Alergias(
	id integer not null generated always as identity (start with 1, increment by 1) primary key,
	resideteID int foreign key references Residente(residenteID),
	nombre varchar(30),
	sintomas varchar(200),
	tratamiento varchar(200),
)

create table Familiar(
	id integer not null generated always as identity (start with 1, increment by 1) primary key,
	resideteID int foreign key references Residente(residenteID),
	nombre varchar(50),
	correo varchar(60),
	telefono varchar(20),
	relacion varchar(20),
	domicilio varchar(50)
)

create table Medicamento(
	id integer not null generated always as identity (start with 1, increment by 1) primary key,
	resideteID int foreign key references Residente(residenteID),
	nombre varchar (50),
	cantidad int,
	dosis varchar(30),
	vecesDia varchar(30),
	tipo varchar(30)
)