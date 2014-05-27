package com.example.wakemeapp;

import java.util.List;

import clases.Alarma;
import clases.Comprobar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import Persistencia.BDOperaciones;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


/**
 * This shows how to draw circles on a map.
 */
public class Mapa extends FragmentActivity 
		implements OnMapClickListener, OnMarkerDragListener {
    private LatLng ubicacion = new LatLng(40.416615, -3.703827);
    private double distancia = 1000;
    
    private GoogleMap mMap;
    //comentario
    private DraggableCircle dc;

    int color = 0;
    int alpha = 127;
    int width = 10;
    
    private int mStrokeColor;
    private int mFillColor;
    
    private Alarma alarma = new Alarma();
    
    @Override
	public void onBackPressed() {		
		Intent intent = new Intent(Mapa.this, NuevaAlarma.class);
        startActivity(intent);
	}

    private class DraggableCircle {
        private final Marker centerMarker;
        private final Circle circle;
        private double radius;
        public DraggableCircle(LatLng center, double radius) {
            this.radius = radius;
            centerMarker = mMap.addMarker(new MarkerOptions()
                    .position(center)
                    .draggable(true));
           circle = mMap.addCircle(new CircleOptions()
                    .center(center)
                    .radius(radius)
                    .strokeWidth(width)
                    .strokeColor(mStrokeColor)
                    .fillColor(mFillColor));
        }
        
        public boolean onMarkerMoved(Marker marker) {
            if (marker.equals(centerMarker)) {
                circle.setCenter(marker.getPosition());
                return true;
            }            
            return false;
        }       
    }   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           String s = extras.getString("Alarma");
           alarma = new Gson().fromJson(s, Alarma.class);
           distancia=alarma.getDistancia();
           ubicacion= new LatLng(alarma.getLatitud(),alarma.getLongitud());
        }
        
        setUpMapIfNeeded();
        
        Button btnAtras = (Button)findViewById(R.id.btnAtras);
		btnAtras.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(Mapa.this, NuevaAlarma.class);
                  intent.putExtra("Alarma", new Gson().toJson(alarma));
                  startActivity(intent);
             }
        });
		
		Button btnAlarma = (Button)findViewById(R.id.btnAlarma);
		btnAlarma.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
            	 alarma.setLatitud((float)dc.centerMarker.getPosition().latitude);
            	 alarma.setLongitud((float)dc.centerMarker.getPosition().longitude);
            	 
            	 Intent intent = new Intent(Mapa.this, NuevaAlarma.class);
                 intent.putExtra("Alarma", new Gson().toJson(alarma));
                 startActivity(intent);
             }
        });
        
        
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
    	mMap.setOnMapClickListener(this);
        mMap.setOnMarkerDragListener(this);       

        mFillColor = Color.HSVToColor(alpha, new float[] {color, 1, 1});
        mStrokeColor = Color.BLACK;

        DraggableCircle circle = new DraggableCircle(ubicacion, distancia);
        dc = circle;

        // Move the map so that it is centered on the initial circle
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 10.0f));
    }

   

    @Override
    public void onMarkerDragStart(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        onMarkerMoved(marker);
    }

    private void onMarkerMoved(Marker marker) {
    	dc.onMarkerMoved(marker);
    }

   
    @Override
	public void onMapClick(LatLng point) {
    	mMap.clear();
    	DraggableCircle circle = new DraggableCircle(point, distancia);
        dc = circle;
		
		Toast toast = Toast.makeText(this, "Posicion: " + point.toString(), Toast.LENGTH_LONG);
	    toast.show();	
		
	}
    /*
    private Handler handler = new Handler();
	private Runnable runnable = new Runnable() 
	{

	    public void run() 
	    {
	    	//codigo que se ejecutara periodicamente
	    	//INICIO
	    	System.out.println("HOLA");

	    	Context c = getApplicationContext();
	    	
	    	
	    	BDOperaciones bdo = new BDOperaciones();
	    	List<Alarma> lalarma = bdo.getAlarmasActivas(c);
	    	for(Alarma a : lalarma){
	    		System.out.println("---" + a.getNombre());
	    	}
	    	
	    	
	    	
	    	//FIN
	    	
	    	
	    	
	        handler.postDelayed(this, 5000);
	    }
	};
	*/
}
