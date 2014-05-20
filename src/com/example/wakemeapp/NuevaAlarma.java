package com.example.wakemeapp;



import clases.Alarma;
import Persistencia.BDOperaciones;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class NuevaAlarma extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevaalarma);
		
   	 	Button btnAtras = (Button)findViewById(R.id.btnAtras);
		btnAtras.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(NuevaAlarma.this, Principal.class);
                  startActivity(intent);
             }
        });
		
		SeekBar skbDistancia = (SeekBar)findViewById(R.id.skbDistancia);
		skbDistancia.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				TextView lblNumero = (TextView)findViewById(R.id.lblNumero);
				lblNumero.setText(Integer.toString(progress));
			}
		});
		
		
		Button btnCrear = (Button)findViewById(R.id.btnCrear);
		btnCrear.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
            	 
            	 Alarma alarma;
            	 
            	 int id = 0; //OJO -->HE DADO ID 0 A TODOS, esto habrá que cambiarlo
            	 String nombre;
            	 String destino;
            	 int distancia;
            	 boolean favorito = false;
            	 boolean activa = true;
            	 
            	 TextView txtNombre = (TextView)findViewById(R.id.txtNombre);
            	 nombre = txtNombre.getText().toString(); 
            	 
            	 TextView txtDestino = (TextView)findViewById(R.id.txtDestino);
            	 destino = txtDestino.getText().toString(); 
            	 
            	 SeekBar skbDistancia = (SeekBar)findViewById(R.id.skbDistancia);
            	 distancia = skbDistancia.getProgress();

            	 ToggleButton tbnFavoritos = (ToggleButton)findViewById(R.id.tbnFavoritos);
            	 favorito = tbnFavoritos.isChecked();
            	 
            	 
            	 ToggleButton tbnActivar = (ToggleButton)findViewById(R.id.tbnActivar);
            	 activa = tbnActivar.isChecked();
            	             	 
            	 alarma = new Alarma(id, nombre, destino, distancia, favorito, activa);
            	 
            	 BDOperaciones bd = new BDOperaciones();
            	 bd.insertarAlarma(NuevaAlarma.this.getApplicationContext(), alarma);
            	 
            	 //System.out.println("Mi alarma es: " + alarma.getId() + " " + alarma.getNombre() + " " + alarma.getDestino() + " " + alarma.getDistancia() + " " + alarma.isFavorito() + " " + alarma.isActiva());
                 
            	 Notificaciones notif=new Notificaciones();
            	 
            	 Notification.Builder builder = new Notification.Builder(NuevaAlarma.this.getApplicationContext())
					.setSmallIcon(R.drawable.ic_launcher)
					.setWhen(System.currentTimeMillis())
					.setContentInfo("no. 10" )
					.setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
				//	.setLargeIcon(mRandomizer.getRandomImage());
            	 
            	 builder.setContentTitle("Maximun priority notification");
					//.setPriority(Notification.PRIORITY_MAX);
            	 	Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            	 	builder.setSound(alarmSound);
            	 
            	 notif.sendNotification(builder.getNotification());
            	 	
            	 Intent intent = new Intent(NuevaAlarma.this, Principal.class);

            	 startActivity(intent);
             }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
