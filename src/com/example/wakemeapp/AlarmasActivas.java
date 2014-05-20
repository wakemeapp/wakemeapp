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

public class AlarmasActivas extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarmasactivas);
		
		BDOperaciones bd = new BDOperaciones();
		List<Alarma> lstalarma = bd.getAlarmasActivas(this);
		
		Adaptadoritemactiva adapterM = new Adaptadoritemactiva(this, lstalarma);
        ListView alarmasactivas = (ListView)findViewById(R.id.listaAlarmasActivas);	            
        alarmasactivas.setAdapter(adapterM);
		
		Button btnnuevaalarma = (Button)findViewById(R.id.btAtras);
        btnnuevaalarma.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(AlarmasActivas.this, Principal.class);
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