package clases;

import java.util.ArrayList;
import java.util.List;

public class Persistencia {

	private static Persistencia pers;
	private List<alarma> listAlarmas;
	
	private Persistencia() {
		alarma alarma1 = new alarma("Casa", 2);
		alarma alarma2 = new alarma("Trabajo", 2);
		alarma alarma3 = new alarma("Excursión 1", 4);
		alarma alarma4 = new alarma("Universidad", 3);
		
		listAlarmas = new ArrayList<alarma>();
		listAlarmas.add(alarma1);
		listAlarmas.add(alarma2);
		listAlarmas.add(alarma3);
		listAlarmas.add(alarma4);
	}
	
	public static Persistencia getPersistencia() {
		if(pers == null) {
			pers = new Persistencia();
		}
		
		return pers;
	}
	
	public List<alarma> getAlarmasBD() {
		return listAlarmas;
	}
}
