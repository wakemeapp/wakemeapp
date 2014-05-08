package Persistencia;

import java.util.ArrayList;
import java.util.List;

import clases.Alarma;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BDOperaciones {
	
	//Devuelve true si existe el ID pasado como parámetro en la tabla Alarmas
	//false en caso contrario
	public boolean estaIDAlarmas(Context c, int id)
	{	
		boolean resultado=false;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Buscamos el ID
				Cursor cursor = db.rawQuery(" SELECT id FROM Alarmas WHERE id=" + id, null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					resultado=true;
				}
				else
				{
					resultado=false;
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return resultado;
		
	}
	
	//Devuelve true si existe el ID pasado como parámetro en la tabla Alarmas Predeterminadas (Config)
	//false en caso contrario 
	public boolean estaIDPredeterminadas(Context c, int id)
	{	
		boolean resultado=false;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Buscamos el ID
				Cursor cursor = db.rawQuery(" SELECT id FROM Config WHERE id=" + id, null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					resultado=true;
				}
				else
				{
					resultado=false;
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return resultado;
		
	}
	
	//Devuelve si la tabla Alarmas está vacia (true) o no (false)
	public boolean isAlarmasVacia(Context c)
	{	
		boolean resultado=false;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Contamos el número de alarmas (filas) que existen
				Cursor cursor = db.rawQuery(" SELECT COUNT(*) FROM Alarmas", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					
					if(cursor.getInt(0) > 0)
					{
						resultado=false;
					}
					else
					{
						resultado=true;
					}
										
				}
				else
				{
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return resultado;
		
	}	
	
	//Devuelve el número de alarmas que hay en la tabla Alarmas
	public int getNumeroAlarmas(Context c)
	{	
		int numFilas=-1;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Contamos el número de alarmas (filas) que existen
				Cursor cursor = db.rawQuery(" SELECT COUNT(*) FROM Alarmas", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					
					numFilas = cursor.getInt(0);
				}
				else
				{
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return numFilas;
		
	}
	
	//Devuelve el número de alarmas que hay en la tabla Alarmas
	public int getNumeroPredeterminadas(Context c)
	{	
		int numFilas=-1;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Contamos el número de alarmas (filas) que existen
				Cursor cursor = db.rawQuery(" SELECT COUNT(*) FROM Config", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					
					numFilas = cursor.getInt(0);
				}
				else
				{
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return numFilas;
		
	}
	
	//Devuelve el último ID de la última fila de la tabla alarmas
	public int getIdUltimaAlarma(Context c)
	{	
		int id=-1;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Contamos el número de alarmas (filas) que existen
				Cursor cursor = db.rawQuery("SELECT MAX(id) FROM Alarmas", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					id = cursor.getInt(0);
				}
				else
				{
					id=0;
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return id;
		
	}
	
	//Introduce la alarma pasada como parámetro en la tabla Alarmas
	public boolean insertarAlarma(Context c, Alarma alarma)
	{
		boolean resultado=true;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
	
				// Generamos los datos
				

				// Si no te pasan nada en el campo ID (por defecto valdría 0), creo un ID yo
				// sino intento introducir el ID que me pasan
				// si el ID no se puede introducir, saltará una excepción, la cual está controlada
				// en esta misma función
//				if(alarma.getId() == 0 || alarma.getId() == -1)
//				{
//					//Si no hay registros en la tabla poner 0 y si los
//					//hay coger el último y añadirle +1 a su ID para poner el siguiente ID.
//					if(getIdUltimaAlarma(c)==0 || getIdUltimaAlarma(c)==-1)
//					{
//						id=0;
//					}
//					else
//					{
//						id=getIdUltimaAlarma(c) + 1;
//					}
//				}
//				else
//				{
//					id=alarma.getId();
//				}
				
				
			    int id = getIdUltimaAlarma(c) + 1;
				String nombre = alarma.getNombre();
				String destino = alarma.getDestino();
				int distancia = alarma.getDistancia();
				int favorito = (alarma.isFavorito()) ? 1 : 0;
				int activa = (alarma.isActiva()) ? 1 : 0;
				String direccion= alarma.getDireccion();
				float latitud = alarma.getLatitud();
				float longitud = alarma.getLongitud();
				
				try{
				    // Insertamos los datos en la tabla Alarmas
					db.execSQL("INSERT INTO Alarmas " +
							"(id, nombre, destino, distancia, favorito, activa, direccion, latitud, longitud) " + 
							"VALUES (" + id + ", '" + nombre + "','" + destino + "'" +
									"," + distancia +","+ favorito + "," + activa + ",'"+direccion+"',"+latitud+","+ longitud +")");
					
				}catch(SQLException e)
				{
					System.out.println("Error al insertar, puede que el ID ya exista en la Base de Datos");
					resultado=false;
				}
				
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return resultado;
	}
	
	//Introduce la alarma pasada como parámetro en la tabla Config (alarmas predeterminadas)
	public boolean insertarPredeterminada(Context c, Alarma alarma)
	{
		boolean resultado=true;
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
	
				// Generamos los datos
				int id = alarma.getId();
				String nombre = alarma.getNombre();
				String destino = alarma.getDestino();
				int distancia = alarma.getDistancia();
				int favorito = (alarma.isFavorito()) ? 1 : 0;
				int activa = (alarma.isActiva()) ? 1 : 0;
				String direccion= alarma.getDireccion();
				float latitud = alarma.getLatitud();
				float longitud = alarma.getLongitud();
				
				try{
				    // Insertamos los datos en la tabla Alarmas
					db.execSQL("INSERT INTO Config " +
							"(id, nombre, destino, distancia, favorito, activa, direccion, latitud, longitud) " + 
							"VALUES (" + id + ", '" + nombre + "','" + destino + "'" +
									"," + distancia +","+ favorito + "," + activa + ",'"+direccion+"',"+latitud+","+ longitud +")");
					
				}catch(SQLException e)
				{
					System.out.println("Error al insertar, puede que el ID ya exista en la Base de Datos");
					resultado=false;
				}
				
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return resultado;
	}
	
	//Elimina la alarma (o alarmas, aunque no debe darse el caso ya que ID es clave primaria)
	//que coincidan con el ID pasado como parámetro
	//de la tabla de alarmas 
	public void eliminarAlarma(Context c, int id)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
						
				// Borramos los datos de la tabla Alarmas
				db.execSQL("DELETE FROM Alarmas WHERE id=" + id);
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
	}
	
	//Elimina la alarma (o alarmas, aunque no debe darse el caso ya que ID es clave primaria)
	//que coincidan con el ID pasado como parámetro
	//de la tabla de alarmas predeterminadas (Config)
	public void eliminarPredeterminada(Context c, int id)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
						
				// Borramos los datos de la tabla Alarmas
				db.execSQL("DELETE FROM Config WHERE id=" + id);
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
	}
	
	//Elimina todas las alarmas de la tabla Alarmas
	public void eliminarTodasAlarmas(Context c)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
						
				// Borramos todos los datos de la tabla Alarmas
				db.execSQL("DELETE FROM Alarmas");
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
	}
	
	//Elimina todas las alarmas de la tabla Config
	public void eliminarTodasPredeterminadas(Context c)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
						
				// Borramos todos los datos de la tabla Alarmas
				db.execSQL("DELETE FROM Config");
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
	}
	
	//Modifica la alarma de la tabla Alarmas que coincida con 
	//el ID pasado como parámetro asignándole todos los valores del objeto de tipo Alarma pasado 
	//también como parámetro
	public void modificarAlarma(Context c, int idparametro, Alarma alarma)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
			// Generamos los datos
			int id = alarma.getId();
			String nombre = alarma.getNombre();
			String destino = alarma.getDestino();
			int distancia = alarma.getDistancia();
			int favorito = (alarma.isFavorito()) ? 1 : 0;
			int activa = (alarma.isActiva()) ? 1 : 0;
			String direccion= alarma.getDireccion();
			float latitud = alarma.getLatitud();
			float longitud = alarma.getLongitud();
			
		
			// Modificamos los datos con el id que te pasan por los nuevos datos
			// contenidos en la clase Alarma que te pasan
			db.execSQL("UPDATE Alarmas " +
					   "SET id=" + id + ", nombre='" + nombre + "', destino='" + destino + "'" +
							", distancia=" + distancia +", favorito="+ favorito + ", activa=" + activa +
							", direccion='" + direccion +"', latitud= "+latitud+", longitud="+ longitud +
					   " WHERE id=" + idparametro );
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
	}
	
	//Modifica la alarma de la tabla Config de alarmas predeterminadas que coincida con 
	//el ID pasado como parámetro asignándole todos los valores del objeto de tipo Alarma pasado 
	//también como parámetro
	public void modificarPredeterminada(Context c, int idparametro, Alarma alarma)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
			// Generamos los datos
			int id = alarma.getId();
			String nombre = alarma.getNombre();
			String destino = alarma.getDestino();
			int distancia = alarma.getDistancia();
			int favorito = (alarma.isFavorito()) ? 1 : 0;
			int activa = (alarma.isActiva()) ? 1 : 0;
			String direccion= alarma.getDireccion();
			float latitud = alarma.getLatitud();
			float longitud = alarma.getLongitud();
			
		
			// Modificamos los datos con el id que te pasan por los nuevos datos
			// contenidos en la clase Alarma que te pasan
			db.execSQL("UPDATE Config " +
					   "SET id=" + id + ", nombre='" + nombre + "', destino='" + destino + "'" +
							", distancia=" + distancia +", favorito="+ favorito + ", activa=" + activa +
							", direccion='" + direccion +"', latitud= "+latitud+", longitud="+ longitud +
					   " WHERE id=" + idparametro );
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
	}
	
	//Modifica el estado de la alarma que coincida con el ID pasado como parámetro
	//asignándole al campo "Favorito" el valor pasado como parámetro (fav)
	public void modificarFavorito(Context c, int idparametro, boolean fav)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
			// Generamos los datos
			int favorito = (fav==true) ? 1 : 0;
					
			// Modificamos el id que te pasan para que cambia su estado favorito
			db.execSQL("UPDATE Alarmas " +
					   "SET favorito="+ favorito +
					   " WHERE id=" + idparametro );
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}		
	}
	
	//Modifica el estado de la alarma que coincida con el ID pasado como parámetro
	//asignándole al campo "Activa" el valor pasado como parámetro (act)
	public void modificarActiva(Context c, int idparametro, boolean act)
	{
		// Abrimos la base de datos ‘BDAlarmas’ en modo escritura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
			// Generamos los datos
			int activa = (act==true) ? 1 : 0;
					
			// Modificamos el id que te pasan para que cambia su estado favorito
			db.execSQL("UPDATE Alarmas " +
					   "SET activa="+ activa +
					   " WHERE id=" + idparametro );
			
			// Cerramos la base de datos
			db.close();
		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}			
	}
	
	//Devuelve todas las alarmas que esten activas de la tabla Alarmas
	public List<Alarma> getAlarmasActivas(Context c)
	{
		//Creo la lista de alarmas activas que voy a devolver
		List<Alarma> listaActivas = new ArrayList<Alarma>();
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Consultamos las alarmas activas de la tabla Alarmas
				Cursor cursor = db.rawQuery(" SELECT * FROM Alarmas WHERE activa=1", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					
					int id;
					String nombre; 
					String destino; 
					int distancia; 
					boolean favorito; 
					boolean activa;
					String direccion;
					float latitud; 
					float longitud;
					
					//Recorremos el cursor hasta que no haya más registros
					do {
						id = cursor.getInt(0);
						nombre = cursor.getString(1);
						destino = cursor.getString(2);
						distancia = cursor.getInt(3);
						favorito = (cursor.getInt(4)==1) ? true : false;
						activa = (cursor.getInt(5)==1) ? true : false;
						direccion= cursor.getString(6);
						latitud = cursor.getFloat(7);
						longitud = cursor.getFloat(8);
						
						//Creo una alarma con los datos obtenidos de la consulta
						Alarma alarma = new Alarma(id, nombre, destino, distancia, favorito, activa, direccion, latitud, longitud);
						
						//Añado la alarma creada a la lista
						listaActivas.add(alarma);	
						
					} while(cursor.moveToNext());
				}
				else
				{
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return listaActivas;
		
	}
	
	//Devuelve todas las alarmas que sean favoritas de la tabla Alarmas
	public List<Alarma> getAlarmasFavoritas(Context c)
	{
		//Creo la lista de alarmas activas que voy a devolver
		List<Alarma> listaFavoritas = new ArrayList<Alarma>();
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Consultamos las alarmas favoritas de la tabla Alarmas
				Cursor cursor = db.rawQuery(" SELECT * FROM Alarmas WHERE favorito=1", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					
					int id;
					String nombre; 
					String destino; 
					int distancia; 
					boolean favorito; 
					boolean activa;
					String direccion;
					float latitud; 
					float longitud;
					
					//Recorremos el cursor hasta que no haya más registros
					do {
						id = cursor.getInt(0);
						nombre = cursor.getString(1);
						destino = cursor.getString(2);
						distancia = cursor.getInt(3);
						favorito = (cursor.getInt(4)==1) ? true : false;
						activa = (cursor.getInt(5)==1) ? true : false;
						direccion= cursor.getString(6);
						latitud = cursor.getFloat(7);
						longitud = cursor.getFloat(8);
						
						//Creo una alarma con los datos obtenidos de la consulta
						Alarma alarma = new Alarma(id, nombre, destino, distancia, favorito, activa, direccion, latitud, longitud);
						
						//Añado la alarma creada a la lista
						listaFavoritas.add(alarma);	
						
					} while(cursor.moveToNext());
				}
				else
				{
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return listaFavoritas;
		
	}
	
	//Devuelve todas las Alarmas predeterminadas (las de la tabla Config)
	public List<Alarma> getAlarmasPredeterminadas(Context c)
	{
		//Creo la lista de alarmas activas que voy a devolver
		List<Alarma> listaPredeterminadas = new ArrayList<Alarma>();
		
		// Abrimos la base de datos ‘BDAlarmas’ en modo lectura
		BaseDatos usdbh = new BaseDatos(c, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getReadableDatabase();

		// Si hemos abierto correctamente la base de datos
		if (db != null) {
				
				// Consultamos las alarmas favoritas de la tabla Alarmas
				Cursor cursor = db.rawQuery(" SELECT * FROM Config", null);
				
				//Nos aseguramos de que existe al menos un registro
				if (cursor.moveToFirst()) {
					
					int id;
					String nombre; 
					String destino; 
					int distancia; 
					boolean favorito; 
					boolean activa;
					String direccion;
					float latitud; 
					float longitud;
					
					//Recorremos el cursor hasta que no haya más registros
					do {
						id = cursor.getInt(0);
						nombre = cursor.getString(1);
						destino = cursor.getString(2);
						distancia = cursor.getInt(3);
						favorito = (cursor.getInt(4)==1) ? true : false;
						activa = (cursor.getInt(5)==1) ? true : false;
						direccion= cursor.getString(6);
						latitud = cursor.getFloat(7);
						longitud = cursor.getFloat(8);
						
						//Creo una alarma con los datos obtenidos de la consulta
						Alarma alarma = new Alarma(id, nombre, destino, distancia, favorito, activa, direccion, latitud, longitud);
						
						//Añado la alarma creada a la lista
						listaPredeterminadas.add(alarma);	
						
					} while(cursor.moveToNext());
				}
				else
				{
					System.out.println("La consulta no ha devuelto registros");
				}
				
			// Cerramos la base de datos
			db.close();

		}
		else
		{
			System.out.println("Error al abrir la base de datos");
		}
		
		return listaPredeterminadas;
		
	}
	
	
}