

package com.example.wakemeapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NuevaAlarma extends Activity {
//hola
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevaalarma);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		//cambio
	}

}
