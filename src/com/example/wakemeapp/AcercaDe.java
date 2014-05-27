package com.example.wakemeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AcercaDe extends Activity{
	
	@Override
	public void onBackPressed() {		
		Intent intent = new Intent(AcercaDe.this, Principal.class);
        startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acercade);
		
		Button btnAtras = (Button)findViewById(R.id.btAtras);
		btnAtras.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(AcercaDe.this, Principal.class);
                  startActivity(intent);
             }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.config:
	        	Intent intent = new Intent(AcercaDe.this, ConfiguracionDefecto.class);
                startActivity(intent);
	            return true;	        
	    }
		return false;
	}
}
