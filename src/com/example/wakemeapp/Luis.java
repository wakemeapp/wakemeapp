package com.example.wakemeapp;

import java.util.List;

import android.app.Activity;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;

public class Luis extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.luis);		
		
		
		LocationManager locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		List<String> listaProviders = locManager.getAllProviders();
		
		LocationProvider provider0 = locManager.getProvider(listaProviders.get(0));
		LocationProvider provider1 = locManager.getProvider(listaProviders.get(1));
		
		System.out.println(provider0.getName() + ": " + provider0.getAccuracy() + " " + provider0.supportsAltitude() + " " + provider0.getPowerRequirement());
		System.out.println(provider1.getName() + ": " + provider1.getAccuracy() + " " + provider1.supportsAltitude() + " " + provider1.getPowerRequirement());
		
		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			System.out.println("El servicio de GPS está desactivado, desea activarlo ahora?");
		} else {
			System.out.println("Su servicio está habilitado, enhorabuena!");
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}