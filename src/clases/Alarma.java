package clases;

public class Alarma {

	private int id;
	private String nombre;
	private int distancia;
	private boolean activa;
	private String direccion;
	private float latitud;
	private float longitud;
	private boolean favorito;
	private String destino;
	
	public Alarma(){
		
	}
	
	public Alarma(String nombre, int distancia){
		this.nombre = nombre;
		this.distancia = distancia;		
	}
	
	public Alarma(int id, String nombre, String destino, int distancia, boolean favorito, boolean activa)
	{
		this.id = id;
		this.nombre = nombre;
		this.destino = destino;
		this.distancia = distancia;
		this.favorito = favorito;
		this.activa = activa; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}
