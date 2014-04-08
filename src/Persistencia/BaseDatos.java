package Persistencia;

import com.example.wakemeapp.R;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class BaseDatos extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.victor);
		
		// Abrimos la base de datos ‘DBUsuarios’ en modo escritura
		Sqlite usdbh = new Sqlite(this, "BDAlarmas", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
			
			// Insertamos 5 usuarios de ejemplo
			for (int i = 1; i <= 5; i++) {
				// Generamos los datos
				int id = i;
				String nombre = "Alarma" + i;
				String destino = "Destino" + i;
				int distancia = i * 100;
				int favorito = 1;
				int activa = 1;
				String direccion= "Direccion" + i;
				float latitud = 30 / (float) i;
				float longitud = 35 / (float) i;
				
				// Insertamos los datos en la tabla Usuarios
				db.execSQL("INSERT INTO Alarmas (id, nombre, destino, distancia, favorito," +
						"activa, direccion, latitud, longitud) " + 
						"VALUES (" + id + ", '" + nombre + "','" + destino + "'" +
								"," + distancia +","+ favorito + "," + activa + ",'"+direccion+"',"+latitud+","+ longitud +")");
			}
			// Cerramos la base de datos
			db.close();
		}
	}
}