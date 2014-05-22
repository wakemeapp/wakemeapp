package com.example.wakemeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
}
