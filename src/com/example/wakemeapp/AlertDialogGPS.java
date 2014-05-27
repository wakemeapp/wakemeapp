package com.example.wakemeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

public class AlertDialogGPS extends Activity{

	final Context context = this;
	LocationManager locManager;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
	    alertDialog.setTitle("Servicio GPS");
	    alertDialog.setMessage("El GPS no está habilitado. ¿Desea ir al menú de configuración para activarlo?");
	    alertDialog.setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog,int which) {
	        	System.out.println("OK");
	            Intent returnIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);	
	            startActivityForResult(returnIntent,2);	            
	        }
	    });
	    alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {	        	
	        	finish();	        	
	        }
	    });	    
	    alertDialog.create().show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2) {  
			locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
	 		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
	 			System.runFinalizersOnExit(true);
	        	System.exit(0);	 			
	 		} else {
	 			Intent intent = new Intent(AlertDialogGPS.this, Principal.class);
	 	        startActivity(intent);
	 		}
		}  
	}	
}
