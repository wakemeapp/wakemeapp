package com.example.wakemeapp;

import java.util.List;

import clases.Alarma;

import Persistencia.BDOperaciones;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Favoritos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favoritos);
		
		BDOperaciones bd = new BDOperaciones();
		List<Alarma> lstalarma = bd.getAlarmasFavoritas(this);
		
		Adaptadoritemfavoritos adapterM = new Adaptadoritemfavoritos(this, lstalarma);
        ListView favoritos = (ListView)findViewById(R.id.listView1);	            
        favoritos.setAdapter(adapterM);
		
		Button btnAtras = (Button)findViewById(R.id.button1);
		btnAtras.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Favoritos.this, Principal.class);
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