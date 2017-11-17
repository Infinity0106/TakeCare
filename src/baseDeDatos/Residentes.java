package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import baseDeDatos.AdministrarConecciones;
import controladores.CellRecordatorioController;
import modulos.Familiar;
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
	
	public ArrayList<Residente> residentesRecordatorio(String fecha, String dosis){
		ArrayList<Residente> values = new ArrayList<Residente>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaDate = LocalDate.parse(fecha, formatter);
        LocalDate nowDate = LocalDate.now();

        int DiferenciaDeDias = Math.abs(fechaDate.compareTo(nowDate));
		
		try {
			ResultSet res = openConection().createStatement().executeQuery("SELECT * \n" + 
					"		FROM APP.MEDICAMENTO MED JOIN APP.RESIDENTE RES ON MED.RESIDENTEID=RES.RESIDENTEID");
			if(res!=null) {
				while(res.next()) {
					Residente tmpRes = new Residente();
					tmpRes.Medicamentos = new ArrayList<Medicamento>();
					tmpRes.Familiares = new ArrayList<Familiar>();
					int tmpDosis = res.getInt("posologia");
					int cantidadFinal = res.getInt("cantidad");
					for(int i=0;i<DiferenciaDeDias;i++) {						
						if(res.getString("vecesdia").contains("Dia")) {
							cantidadFinal-=tmpDosis;
						}
						if(res.getString("vecesdia").contains("Tarde")) {
							cantidadFinal-=tmpDosis;
						}
						if(res.getString("vecesdia").contains("Noche")) {
							cantidadFinal-=tmpDosis;
						}
					}
					if(cantidadFinal<= new Integer(dosis)) {
						ResultSet res2 = openConection().createStatement().executeQuery("select * from (\n" + 
								"		select ROW_NUMBER() OVER() AS rownum, APP.Familiar.*\n" + 
								"		from APP.Familiar\n" + 
								"		where residenteID="+res.getString("residenteid")+"\n" + 
								"	) tmp\n" + 
								"	where rownum<=1");
						if(res2!=null) {
							while(res2.next()) {
								Familiar tmpfam = new Familiar();
								tmpfam.Nombre=res2.getString("nombre");
								tmpfam.Telefono=res2.getString("telefono");
								tmpRes.Familiares.add(tmpfam);
							}
						}
						Medicamento tmpMed = new Medicamento();
						tmpMed.Nombre = res.getString(3);
						tmpMed.Cantidad = cantidadFinal;
						tmpRes.Medicamentos.add(tmpMed);
						
						tmpRes.Nombre = res.getString(11);
						values.add(tmpRes);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return values;
	}

//	public ArrayList<CellRecordatorioController> residentesRecordatorios(String fecha, String dosis) throws ClassNotFoundException, SQLException {
//		ArrayList<CellRecordatorioController> Elementos = new ArrayList<CellRecordatorioController>();
//		ArrayList<Medicamento> MedicamentosDB = new ArrayList<Medicamento>();
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    LocalDate fechaDate = LocalDate.parse(fecha, formatter);
//	    LocalDate nowDate = LocalDate.now();
//	
//	    int DiferenciaDeDias = Math.abs(fechaDate.compareTo(nowDate));
//        
//		
//        System.out.println("DiferenciaDias: " + DiferenciaDeDias);
//        
//		// Obtener tope de pastillas
//		int TopePastillas = Integer.parseInt(dosis);
//		
//		// Obtener Medicamentos
//		ResultSet res = openConection().createStatement().executeQuery(
//				" 	select nombre, cantidad, posologia, medicamentoID, residenteID" + 
//				"	from medicamento"
//				);
//		System.out.println(res == null);
//		if(res != null) {
//			while(res.next()) {
//				Medicamento MedicamentoAAgregar = new Medicamento();
//				MedicamentoAAgregar.Nombre = res.getString("nombre");
//				MedicamentoAAgregar.Cantidad = res.getInt("cantidad");
//				MedicamentoAAgregar.Posologia = Integer.parseInt(res.getString("posologia"));
//				MedicamentoAAgregar.IDMedicamento = res.getInt("medicamentoID");
//				MedicamentoAAgregar.IDResidente = res.getInt("residenteID");
//				 System.out.println(MedicamentoAAgregar.Nombre);
//				
//				MedicamentosDB.add(MedicamentoAAgregar);
//			}
//		}
//		
//		// Evaluar medicamentos
//		for(Medicamento med : MedicamentosDB) {
//			 System.out.println("- " + MedicamentosDB.size());
//			// Obtener cantidad de medicamentos disponibles para ese medicamento
//			int iCantidadMedicamentosDisponibles = med.Cantidad - (DiferenciaDeDias * med.Posologia); 
//			 System.out.println("- " + "ICantidadMedDisp:" + iCantidadMedicamentosDisponibles);
//			if(iCantidadMedicamentosDisponibles <= TopePastillas) {
//				CellRecordatorioController Elemento = new CellRecordatorioController();
//				
//				// Pbtiene los datos del residente y el familiar
//				ResultSet res2 = openConection().createStatement().executeQuery(
//						" 	SELECT res.nombre, telefono, fam.nombre as nombre_familiar	" + 
//						"	FROM residente res											" +  
//						"	INNER JOIN familiar fam										" + 
//						"	ON res.residenteID = fam.residenteID						" +  
//						"	WHERE res.residenteID = " + med.IDResidente
//						);
//				ResultSet res3 = openConection().createStatement().executeQuery(
//						" 	SELECT COUNT(*)	" + 
//						"	FROM residente res											" 
//						);
//				System.out.println(res3.next());
//				System.out.println(res3.getInt(1));
//
//				res3 = openConection().createStatement().executeQuery(
//						" 	SELECT COUNT(*) " + 
//						"	FROM familiar fam											" 
//						);
//				System.out.println(res3.next());
//				System.out.println(res3.getInt(1));
//				
//				// Evalua los datos para generar un elemento de la lista y agregarlos
//				 System.out.println("a"); 
//				System.out.println("-"  + null);
//				 System.out.println("b");
//				if(res2 != null)
//				{
//					while(res2.next()) {
//						System.out.println("aaaaa");
//						Elemento.setValues(res2.getString(1), med.Nombre, res2.getString(2), res2.getString(3), String.valueOf(iCantidadMedicamentosDisponibles));
//					 	System.out.println("-  " + Elemento.nombre.getText());
//					 	Elementos.add(Elemento);
//					 	}
//				}
//			}
//		}
//		
//		
//		return Elementos;
//	}
	
	public Boolean residenteUpdate(Residente res) {
		Boolean respu=false;
		try {
			String query = "update APP.RESIDENTE set \n" + 
					"	nombre='"+res.Nombre+"', fechanacimiento='"+res.FechaNacimiento+"',sexo='"+res.Sexo+"',estatus='"+res.Estatus+"',fotourl='"+res.FotoUrl+"',lugar="+res.Lugar+",cama="+res.Cama+"\n" + 
					"	where residenteID="+res.IDResidente;
			System.out.print(query);
			PreparedStatement preS = openConection().prepareStatement(query);
			if(!(preS.executeUpdate()>0)) {
				respu=false;
			}else {
				respu=true;
			}
			if(!new Emergencias().emergenciasUpdate(res.EmergenciaMedica)) {
				respu=false;
			}
			if(!new Hospitales().hospitalesUpdate(res.ServicioHospital)) {
				respu=false;
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
			respu=false;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			respu=false;
		}
		return respu;
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
