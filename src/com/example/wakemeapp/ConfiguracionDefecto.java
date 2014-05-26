package com.example.wakemeapp;

import clases.Alarma;
import Persistencia.BDOperaciones;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ConfiguracionDefecto extends Activity{
	
	private Alarma alarma = new Alarma();
	private TextView txtCancion;
	private SeekBar skbDistancia;
	private ToggleButton tbnFavoritos;
	private TextView lblNumero;
	private Spinner spRepetirCada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuraciondefecto);

		BDOperaciones bd = new BDOperaciones();
		alarma = bd.getAlarmasPredeterminadas(getApplicationContext()).get(0);
		
		txtCancion = (TextView)findViewById(R.id.txtCancion);
		txtCancion.setText(alarma.getCancion());
		
		skbDistancia = (SeekBar)findViewById(R.id.skbDistancia);
		skbDistancia.setProgress(alarma.getDistancia());
		
		spRepetirCada = (Spinner)findViewById(R.id.spRepetirCada);
		spRepetirCada.setSelection(0);
		
		lblNumero = (TextView)findViewById(R.id.lblNumero);
  	 	lblNumero.setText(String.valueOf(alarma.getDistancia()) + " metros");
  	 	
		tbnFavoritos = (ToggleButton)findViewById(R.id.tbnFavoritos);
	   	tbnFavoritos.setChecked(alarma.isFavorito());   
	   	
	   	skbDistancia.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				TextView lblNumero = (TextView)findViewById(R.id.lblNumero);
				lblNumero.setText(Integer.toString(progress) + " metros");
				//alarma.setDistancia(progress);
			}
		});

		Button btnCrear = (Button)findViewById(R.id.btnCrear);
		btnCrear.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {    
            	 getparametros();
            	 
            	 BDOperaciones bd = new BDOperaciones();
            	 bd.modificarPredeterminada(ConfiguracionDefecto.this.getApplicationContext(), 0, alarma);
            	 
            	 //System.out.println("Mi alarma es: " + alarma.getId() + " " + alarma.getNombre() + " " + alarma.getCancion() + " " + alarma.getDistancia() + " " + alarma.isFavorito() + " " + alarma.isActiva());
                 
            	 Intent intent = new Intent(ConfiguracionDefecto.this, Principal.class);
                 startActivity(intent);
             }
        });
		
		Button btnAtras = (Button)findViewById(R.id.btnAtras);
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
	
	private void getparametros(){		
	   	alarma.setCancion(txtCancion.getText().toString()); 
	   	alarma.setDistancia(skbDistancia.getProgress());
	   	alarma.setFavorito(tbnFavoritos.isChecked());
	   	
	   	//AlertDialog alertDialog;
	   	//alertDialog = new AlertDialog.Builder(this).create();
	   	//alertDialog.setTitle("Packing List");
	   	//alertDialog.setMessage("SELECTEDITEM = " + spRepetirCada.getSelectedItem());
	   	//alertDialog.show();
	   	
	   	String auxSeleccionado = spRepetirCada.getSelectedItem().toString();
	   	
	   	//Lo he hecho con todo IFs porque si lo haces con un Switch e intentas
	   	//comaparar textos necesitas la versión Java 1.7 y la única que de momento
	   	//usa java 1.7 en Android es la versión KitKat 4.4 y no hay necesidad de requerir
	   	//java 4.4 para nada más que hacer esto
	   	int auxRepetirCada;
	   	if(auxSeleccionado.compareTo("Cada Minuto")==0)
	   	{
	   		auxRepetirCada = 1;
	   	}
	   	else
	   	{
	   		if(auxSeleccionado.compareTo("Cada 3 Minutos")==0)
	   		{
	   			auxRepetirCada = 3;
	   		}
	   		else
	   		{
	   			if(auxSeleccionado.compareTo("Cada 5 Minutos")==0)
	   			{
	   				auxRepetirCada = 5;
	   			}
	   			else
	   			{
	   				if(auxSeleccionado.compareTo("Cada 10 Minutos")==0)
	   				{
	   					auxRepetirCada = 10;
	   				}
	   				else
	   				{
	   					if(auxSeleccionado.compareTo("Cada 15 Minutos")==0)
	   					{
	   						auxRepetirCada = 15;
	   					}
	   					else
	   					{
	   						//En otros casos = 1
	   						auxRepetirCada = 1;
	   					}
	   				}
	   			}
	   		}
	   	}
	   	
	   	alarma.setRepetir(auxRepetirCada);
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
