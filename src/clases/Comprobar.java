package clases;

import java.util.List;

import com.example.wakemeapp.Manuel;

import Persistencia.BDOperaciones;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;

public class Comprobar extends Activity{
	private Handler handler = new Handler();
	private Runnable runnable = new Runnable() 
	{

	    public void run() 
	    {
	    	//codigo que se ejecutara periodicamente
	    	//INICIO
	    	System.out.println("HOLA");

	    	Context c = getApplicationContext();
	    	Manuel m = new Manuel();
	    	m.getApplicationContext();
	    	Context c1 = m.getApplicationContext();
	    	BDOperaciones bdo = new BDOperaciones();
	    	List<Alarma> lalarma = bdo.getAlarmasActivas(c1);
	    	for(Alarma a : lalarma){
	    		System.out.println("---" + a.getNombre());
	    	}
	    	
	    	
	    	
	    	//FIN
	    	
	    	
	    	
	        handler.postDelayed(this, 5000);
	    }
	};
	
	public Comprobar(){		
		runnable.run();
	}
}
