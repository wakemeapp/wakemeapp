package Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//VAS POR PAG 168 Manual Programación Android SGOLIVER
//...Vale, ¿y ahora qué? ¿dónde está la base de datos que acabamos de crear?...

public class Sqlite extends SQLiteOpenHelper {
	
	public Sqlite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	//Sentencia SQL para crear la tabla de Usuarios
	String sqlCreate = "CREATE TABLE Alarmas (id INTEGER, nombre TEXT, destino TEXT, distancia INTEGER, " +
			", favorito INTEGER, activa INTEGER, direccion TEXT, latitud REAL, longitud REAL)";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
		
		//Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Alarmas");
		
		//Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
	}

}
