package clases;

import java.util.ArrayList;
import java.util.List;

public class Persistencia {

	private static Persistencia pers;
	private List<Alarma> listAlarmas;
	
	private Persistencia() {
		Alarma alarma1 = new Alarma("Casa", 2);
		Alarma alarma2 = new Alarma("Trabajo", 2);
		Alarma alarma3 = new Alarma("Excursión 1", 4);
		Alarma alarma4 = new Alarma("Universidad", 3);
		
		listAlarmas = new ArrayList<Alarma>();
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
	
	public List<Alarma> getAlarmasBD() {
		return listAlarmas;
	}
}
