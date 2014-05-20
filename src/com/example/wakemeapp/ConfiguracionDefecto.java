package com.example.wakemeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ConfiguracionDefecto extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acercade);
		
		Button btnAtras = (Button)findViewById(R.id.btAtras);
		btnAtras.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(ConfiguracionDefecto.this, Principal.class);
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
	
	public class SpinnerExample extends Activity {
	    private String array_spinner[];
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.configuraciondefecto);
	        
	        array_spinner=new String[5];
	        array_spinner[0]="Cada Minuto";
	        array_spinner[1]="Cada 3 Minutos";
	        array_spinner[2]="Cada 5 Minutos";
	        array_spinner[3]="Cada 10 Minutos";
	        array_spinner[4]="Cada 15 Minutos";
	        
	        Spinner s = (Spinner) findViewById(R.id.spRepetirCada);
	        ArrayAdapter adapter = new ArrayAdapter(this,
	        android.R.layout.simple_spinner_item, array_spinner);
	        s.setAdapter(adapter);
	    }
	}
}
