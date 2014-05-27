package com.example.wakemeapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Principal extends Activity {

	@Override
	public void onBackPressed() {		
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			if(bundle.getBoolean("Publi", false)) banner();
		}
		
		Button btnnuevaalarma = (Button)findViewById(R.id.button1);
        btnnuevaalarma.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, NuevaAlarma.class);
                  startActivity(intent);
             }
        });
        
        Button btnVerActivas = (Button)findViewById(R.id.button2);
        btnVerActivas.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, AlarmasActivas.class);
                  startActivity(intent);
             }
        });
        
        Button btnFavoritos = (Button)findViewById(R.id.button3);
        btnFavoritos.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, Favoritos.class);
                  startActivity(intent);
             }
        });
        
        Button btnAcercaDe = (Button)findViewById(R.id.button4);
        btnAcercaDe.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, AcercaDe.class);
                  startActivity(intent);
             }
        });

        /*
        Button btnluis = (Button)findViewById(R.id.btnluis);
        btnluis.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, Luis.class);
                  startActivity(intent);
             }
        });        
        
        Button btnmanuel = (Button)findViewById(R.id.btnmanuel);
        btnmanuel.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, Manuel.class);
                  startActivity(intent);
             }
        });
        
        Button btnvictor = (Button)findViewById(R.id.btnvictor);
        btnvictor.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, Victor.class);
                  startActivity(intent);
             }
        });
        */
        
        ImageButton btnconfig = (ImageButton)findViewById(R.id.btnconfig);
        btnconfig.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Principal.this, ConfiguracionDefecto.class);
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
	
	private void banner(){
		AlertDialog.Builder builder=new AlertDialog.Builder(Principal.this);
	    builder.setCancelable(true);
	    builder.setIcon(R.drawable.nosotros);
	    builder.setTitle("Incoming Call");
	    builder.setInverseBackgroundForced(true);
	    builder.setPositiveButton("Accept",new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int which) 
	        {
	            dialog.dismiss();
	        }
	    });	    
	    AlertDialog alert=builder.create();
	    alert.show();
	
	}
}
