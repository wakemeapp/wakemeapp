package com.example.wakemeapp;

import clases.Alarma;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
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
		
		Button btnCrear = (Button)findViewById(R.id.btnCrear);
		btnCrear.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
            	 
            	 Alarma alarma;
            	 
            	 int id = 1; //OJO -->HE DADO ID 1 A TODOS, esto habr� que cambiarlo
            	 String nombre;
            	 String destino;
            	 int distancia;
            	 boolean favorito;
            	 boolean activa = true;
            	 
            	 TextView txtNombre = (TextView)findViewById(R.id.txtNombre);
            	 nombre = txtNombre.getText().toString(); 
            	 
            	 TextView txtDestino = (TextView)findViewById(R.id.txtDestino);
            	 destino = txtDestino.getText().toString(); 
            	 
            	 SeekBar skbDistancia = (SeekBar)findViewById(R.id.skbDistancia);
            	 distancia = skbDistancia.getProgress();

            	 RatingBar ratingBar = (RatingBar)findViewById(R.id.rbFavoritos);
            	 if(ratingBar.getRating()>0)
            	 {
            		 favorito = true;
            	 }
            	 else
            	 {
            		 favorito = false;
            	 }
            	 
            	 //NO COGE ESTE CAMPO BIEN, EL RESTO FUNCIONA, ESTA ES ACTIVA O NO ACTIVA
            	 ToggleButton toggleButton = (ToggleButton)findViewById(R.id.tbnActivar);
            	 if(toggleButton.getTextOn().toString() != null)
            	 {
            		 activa = true;
            	 }
            	 else
            	 {
            		 activa = false;			 
            	 }
            	 
            	 alarma = new Alarma(id, nombre, destino, distancia, favorito, activa);
            	 
            	 System.out.println("Mi alarma es: " + alarma.getId() + " " + alarma.getNombre() + " " + alarma.getDestino() + " " + alarma.getDistancia() + " " + alarma.isFavorito() + " " + alarma.isActiva());
                 
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
