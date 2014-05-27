package Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDatos extends SQLiteOpenHelper {
	
	public BaseDatos(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	//Sentencia SQL para crear la tabla de Usuarios
	private String sqlCreate = "CREATE TABLE Alarmas (" +
			"id INTEGER NOT NULL PRIMARY KEY, " + //AUTOINCREMENT pruebalo con distintas instancias del objeto Alarma
			"nombre TEXT, " +
			"cancion TEXT, " +
			"distancia INTEGER, " +
			"favorito INTEGER, " +
			"activa INTEGER, " +
			"repetir INTEGER, " +
			"latitud REAL, " +
			"longitud REAL," +
			"notificada INTEGER" +
			");";
	
	private String sqlConfig = "CREATE TABLE Config (" +
			"id INTEGER NOT NULL PRIMARY KEY, " + //AUTOINCREMENT
			"nombre TEXT, " +
			"cancion TEXT, " +
			"distancia INTEGER, " +
			"favorito INTEGER, " +
			"activa INTEGER, " +
			"repetir INTEGER, " +
			"latitud REAL, " +
			"longitud REAL" +
			");";
	
	private String sqlInsertarDefecto = "INSERT INTO Config " +
			"(id, nombre, cancion, distancia, favorito, activa, repetir, latitud, longitud) " + 
			"VALUES (0, '','content://media/internal/audio/media/126', 50 ,0 , 1, 1, 40.416615, -3.703827)";
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(sqlCreate);
		db.execSQL(sqlConfig);
		db.execSQL(sqlInsertarDefecto);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
		
		//Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Alarmas");
		db.execSQL("DROP TABLE IF EXISTS Config");
		
		//Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
		db.execSQL(sqlConfig);
		
	}
	
}
