package clases;

public class alarma {

	public int id;
	public String nombre;
	public int distancia;
	public boolean activa;
	public String direccion;
	public float latitud;
	public float longitud;
	public boolean favorito;
	
	public alarma(){
		
	}
	
	public alarma(String nombre, int distancia){
		this.nombre = nombre;
		this.distancia = distancia;		
	}
}
