package com.example.wakemeapp;

import java.util.List;

import clases.Alarma;
import Persistencia.BDOperaciones;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Bienvenida extends Activity {

	TextView linea_ayuda;
    ProgressBar progressBar;
    int progreso = 0;
    int paso = 500;
    private Location coordenadas;
    private LocationManager locManager;
	private LocationListener locListener;
	private NotificationManager nm;  
	private static final int ID_NOTIFICACION_CREAR = 1;
	private int flag_notificacion =1;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bienvenida);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        linea_ayuda = (TextView) findViewById(R.id.linea_ayuda);
        
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        inicializaGPS();
        runnable.run();
	}

	@Override
	protected void onResume() {
		super.onResume();
		linea_ayuda.setText("Inicializando aplicación...");
        cuentaAtras(3000);   //3 sec.
	}
	
	
	
	private void cuentaAtras(long milisegundos){
	    
	    CountDownTimer mCountDownTimer;
	    progressBar.setMax((int)milisegundos);
	    progressBar.setProgress(paso);

	    mCountDownTimer=new CountDownTimer(milisegundos, paso) {
	        
	        @Override
	        public void onTick(long millisUntilFinished) {
	            progreso+=paso;
	            progressBar.setProgress(progreso);
	        }

	        @Override
	        public void onFinish() {
	            Toast.makeText(getApplicationContext(), "El sistema está listo", Toast.LENGTH_LONG).show();
	            progreso+= paso;
	            progressBar.setProgress(progreso);
	            progressBar.setVisibility(View.INVISIBLE);
	            Intent intent = new Intent(Bienvenida.this, Principal.class);
                startActivity(intent);}
	        };

	        mCountDownTimer.start();
	    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		finish();
	}	
	
	
	public void pararNotificacion(){
		this.flag_notificacion=0;
	}
	
	public void reiniciarNoticiacion(){
		this.flag_notificacion=1;
	}
	private Handler handler = new Handler();
	private Runnable runnable = new Runnable() 
	{

		public void run() 
		{
			//codigo que se ejecutara periodicamente
			//INICIO
			System.out.println("Ejecutando run");

			Context c = getApplicationContext();
			System.out.println(coordenadas != null);
			if (coordenadas != null) {
				BDOperaciones bdo = new BDOperaciones();
				List<Alarma> lalarma = bdo.getAlarmasActivas(c);
				System.out.println(coordenadas.getLatitude());
				System.out.println(coordenadas.getLongitude());
				for(Alarma a : lalarma){
					System.out.println("---" + a.getNombre());
					double distancia = distancia(a, coordenadas);
					if(distancia < a.getDistancia()) {
						System.out.println("Está llegando a su destino. ¡¡Vaya cogiendo sus cosas!!");
						//Notificar al usuario la proximidad al destino
					if(flag_notificacion==1){
						Notification notificacion = new Notification(
								R.drawable.icono,
								a.getNombre(),
								System.currentTimeMillis() );

						Uri cancion= Uri.parse(a.getCancion());
								//Uri.parse("R.raw.cancion");
						notificacion.sound = cancion;


						Context conte= Bienvenida.this.getApplicationContext();

						Intent i = new Intent(Bienvenida.this, Principal.class);
						PendingIntent pi =PendingIntent.getActivity(Bienvenida.this.getApplicationContext(), 0, i, 0);

						
						RingtoneManager.setActualDefaultRingtoneUri(c,RingtoneManager.TYPE_RINGTONE,cancion);

						notificacion.setLatestEventInfo(Bienvenida.this.getApplicationContext(),"WakeMeApp", a.getDistancia() + " hasta " +a.getNombre(),pi);
						nm.notify(ID_NOTIFICACION_CREAR, notificacion);
						pararNotificacion();
						startActivity(i);
					}

					}
				}
			}

			//FIN

			handler.postDelayed(this, 5000);
		}
	};

	
	private double distancia(Alarma al, Location lo){
		double R     = 6378.137;                          //Radio de la tierra en km
		double dLat  = rad( al.getLatitud() - lo.getLatitude());
		double dLong = rad( al.getLongitud() - lo.getLongitude());
		
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(rad(lo.getLatitude())) * Math.cos(rad(al.getLatitud())) * Math.sin(dLong/2) * Math.sin(dLong/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
		
		return d;
	}
	
	private double rad(double x){
		return (x*Math.PI/180);
	}
	
	private void inicializaGPS() {
		locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
 		List<String> listaProviders = locManager.getAllProviders();
 		
 		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
 			System.out.println("El servicio GPS está desactivado, desea activarlo ahora?");
 			showSettingsAlert(Bienvenida.this);
 		} else {
 			System.out.println("Su servicio está habilitado, enhorabuena!");
 		}
 		
 		locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
 		
 		locListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				coordenadas = location;
			}
			public void onProviderDisabled(String provider){
				//lblEstado.setText("Provider OFF");
			}
			public void onProviderEnabled(String provider){
				//lblEstado.setText("Provider ON");
			}
			public void onStatusChanged(String provider, int status, Bundle extras){
			
			}
 		};
 		
 		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locListener);
 		
	}
	
	public void showSettingsAlert(final Activity act){
	    AlertDialog.Builder alertDialog = new AlertDialog.Builder(act);

	    // Setting Dialog Title
	    alertDialog.setTitle("Servicio GPS");

	    // Setting Dialog Message
	    alertDialog.setMessage("El GPS no está habilitado. ¿Desea ir al menú de configuración para activarlo?");

	    // Setting Icon to Dialog
	    //alertDialog.setIcon(R.drawable.delete);

	    // On pressing Settings button
	    alertDialog.setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog,int which) {
	            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	            act.startActivity(intent);
	        }
	    });

	    // on pressing cancel button
	    alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	dialog.cancel();
	        }
	    });

	    // Showing Alert Message
	    alertDialog.create().show();
	}
	
	
}
