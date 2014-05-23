	package clases;
	
	public class Alarma {
	
		private int id=0;
		private String nombre;
		private int distancia;
		private boolean activa;
		private int repetir;
		private float latitud;
		private float longitud;
		private boolean favorito;
		private String cancion;
		
		public Alarma(){
			
		}
		
		public Alarma(String nombre, int distancia){
			this.nombre = nombre;
			this.distancia = distancia;		
		}
		
		public Alarma(int id, String nombre, String cancion, int distancia, boolean favorito, boolean activa)
		{
			this.id = id;
			this.nombre = nombre;
			this.cancion = cancion;
			this.distancia = distancia;
			this.favorito = favorito;
			this.activa = activa; 
		}
	
		public Alarma(int id, String nombre, String cancion, int distancia, boolean favorito, boolean activa, int repetir, float latitud, float longitud)
		{
			this.id = id;
			this.nombre = nombre;
			this.cancion = cancion;
			this.distancia = distancia;
			this.favorito = favorito;
			this.activa = activa;
			this.repetir = repetir;
			this.latitud = latitud;
			this.longitud = longitud;
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
	
		public int getRepetir() {
			return repetir;
		}
	
		public void setRepetir(int repetir) {
			this.repetir = repetir;
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
	
		public String getCancion() {
			return cancion;
		}
	
		public void setCancion(String cancion) {
			this.cancion = cancion;
		}
	}