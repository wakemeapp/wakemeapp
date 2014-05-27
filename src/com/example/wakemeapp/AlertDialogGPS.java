package com.example.wakemeapp;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

public class AlertDialogExample extends Activity{

	final Context context = this;
	LocationManager locManager;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

	    // Setting Dialog Title
	    alertDialog.setTitle("Servicio GPS");

	    // Setting Dialog Message
	    alertDialog.setMessage("El GPS no está habilitado. ¿Desea ir al menú de configuración para activarlo?");

	    // Setting Icon to Dialog
	    //alertDialog.setIcon(R.drawable.delete);

	    // On pressing Settings button
	    alertDialog.setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog,int which) {
	        	System.out.println("OK");
	            Intent returnIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	
	            startActivityForResult(returnIntent,2);
	            //setResult(RESULT_OK, returnIntent);
	        }
	    });

	    // on pressing cancel button
	    alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	System.out.println("Cancel");
	        	
	        	//salir de la aplicacion
	        	System.runFinalizersOnExit(true);
	        	System.exit(0);
	        	//android.os.Process.killProcess(android.os.Process.myPid());
	 			/*
	        	Intent startMain = new Intent(Intent.ACTION_MAIN);
	 			startMain.addCategory(Intent.CATEGORY_HOME);
	 			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 			startActivity(startMain);
	 			*/
	        }
	    });

	    // Showing Alert Message
	    alertDialog.create().show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//finish();
		
		if (requestCode == 2) {  
			System.out.println("-Q----------------------------------------------------------");
			
			locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
	 		List<String> listaProviders = locManager.getAllProviders();
	 		
	 		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
	 			//salir de la aplicacion
	 			System.runFinalizersOnExit(true);
	        	System.exit(0);
	 			//android.os.Process.killProcess(android.os.Process.myPid());
	 			/*
	 			Intent startMain = new Intent(Intent.ACTION_MAIN);
	 			startMain.addCategory(Intent.CATEGORY_HOME);
	 			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 			startActivity(startMain);
	 			*/
	 		} else {
	 			Intent intent = new Intent(AlertDialogExample.this, Principal.class);
	 	        startActivity(intent);
	 		}
		}  
	}	
}
