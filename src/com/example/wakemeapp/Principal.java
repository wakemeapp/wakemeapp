package com.example.wakemeapp;

import java.sql.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Principal extends Activity {

	//cambio realizado por Luis
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		
		Button btnnuevaalarma = (Button)findViewById(R.id.button1);
        btnnuevaalarma.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, NuevaAlarma.class);
                  startActivity(intent);
             }
             //ma�ana mejor
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		//cambio mariano 2
	}
	
	public boolean esPeritosPronto (Date fecha) {
		return (true);
	}

	public void mostrarFavoritos(){
		NuevaAlarma a;
		a=new NuevaAlarma();
	}
}
