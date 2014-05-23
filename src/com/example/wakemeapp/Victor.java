package com.example.wakemeapp;

import clases.Alarma;
import Persistencia.BDOperaciones;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Victor extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.victor);		
		
		BDOperaciones bd = new BDOperaciones();
		
		//FUNCIONA
		//bd.insertarAlarma(this, alarma); 
		
		//FUNCIONA
		//bd.eliminarAlarma(this, 5);
		
		/*//FUNCIONA
		Alarma alarma = new Alarma();
		
		float aux= 3;
		alarma.setId(32);
		alarma.setNombre("Paco");
		alarma.setDistancia(3200);
		alarma.setDestino("PATAGONIA");
		alarma.setFavorito(true);
		alarma.setActiva(true);
		alarma.setDireccion("PERICO LOS PALOTES");
		alarma.setLatitud(aux);
		alarma.setLongitud(aux);
	
		bd.modificarAlarma(this, 4, alarma);*/
		
		//FUNCIONA
		//bd.getAlarmasActivas(this);
		
		//FUNCIONA
		//bd.eliminarTodasAlarmas(this);
		
		//FUNCIONA
		//bd.isAlarmasVacia(this);
		//System.out.println("Esta alarmas vacia?: " + bd.isAlarmasVacia(this));
		
		//FUNCIONA
		//bd.modificarActiva(this, 1, false);
		
		//FUNCIONA
		//bd.modificarFavorito(this, 32, false);
		
		//FUNCIONA
		//System.out.println("Esta ID 3 ?: " + bd.estaID(this, 3));
		//System.out.println("Esta ID 32 ?: " + bd.estaID(this, 32));
		//System.out.println("Esta ID 1 ?: " + bd.estaID(this, 1));
		
		
		Alarma alarma = new Alarma();
		
		float aux= 3;
		//alarma.setId(22);
		alarma.setNombre("Nueva Alarma");
		alarma.setDistancia(3200);
		alarma.setCancion("Mi Canción");
		alarma.setFavorito(true);
		alarma.setActiva(true);
		alarma.setRepetir(1);
		alarma.setLatitud(aux);
		alarma.setLongitud(aux);
		
		bd.insertarAlarma(this, alarma); 
		
		
		//ConfiguracionDefecto cd = new ConfiguracionDefecto();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
