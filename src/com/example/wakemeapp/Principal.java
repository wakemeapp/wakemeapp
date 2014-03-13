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
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
<<<<<<< HEAD

=======
	
>>>>>>> 63c959b66febad17fec62742e05040a2541f1328
	public void bucle(){
		for(int i=0; i<100; i++)
			System.out.println("Hola");
	
	}

<<<<<<< HEAD
=======

>>>>>>> 63c959b66febad17fec62742e05040a2541f1328
	public void nuevafuncion (String uno){
		int trescomacatorce =3;
		float casicuatro= 3;
	}	

	public boolean esPeritosPronto (Date fecha) {
		return (true);
	}
<<<<<<< HEAD

=======
>>>>>>> 63c959b66febad17fec62742e05040a2541f1328

	public void mostrarFavoritos(){
		NuevaAlarma a;
		a=new NuevaAlarma();
	}
}
