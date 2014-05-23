package com.example.wakemeapp;

import java.util.List;

import clases.Alarma;
import Persistencia.BDOperaciones;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
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
    int progreso=0;
    int paso = 500;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bienvenida);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        linea_ayuda = (TextView) findViewById(R.id.linea_ayuda);
        
        runnable.run();
	}

	@Override
	protected void onResume() {
		super.onResume();
		linea_ayuda.setText("Actualizando la BD..."); //"Inicializando aplicación ..."
        cuentaAtras(2000);   //3 sec.
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
	
	private Handler handler = new Handler();
	private Runnable runnable = new Runnable() 
	{

	    public void run() 
	    {
	    	//codigo que se ejecutara periodicamente
	    	//INICIO
	    	System.out.println("HOLA");

	    	Context c = getApplicationContext();
	    	
	    	
	    	BDOperaciones bdo = new BDOperaciones();
	    	List<Alarma> lalarma = bdo.getAlarmasActivas(c);
	    	for(Alarma a : lalarma){
	    		System.out.println("---" + a.getNombre());
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
	
	
}
