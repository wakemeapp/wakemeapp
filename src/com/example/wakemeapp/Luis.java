package com.example.wakemeapp;

import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
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
		
		locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		LocationListener locListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				if(location == null) {
					System.out.println("Latitud: (sin datos)");
					System.out.println("Longitud: (sin datos)");
					System.out.println("Precisión: (sin datos)");
					System.out.println("Altura: (sin datos)");
				} else {
					System.out.println("Latitud: " + location.getLatitude());
					System.out.println("Longitud: " + location.getLongitude());
					System.out.println("Precisión: " + location.getAccuracy());
					System.out.println("Altura: " + location.getAltitude());
				}
				
			}
			public void onProviderDisabled(String provider){
				
			}
			public void onProviderEnabled(String provider){
				
			}
			public void onStatusChanged(String provider, int status, Bundle extras){
			
			}
			};
			
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}