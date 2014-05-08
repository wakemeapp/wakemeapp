package com.example.wakemeapp;

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

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


/**
 * This shows how to draw circles on a map.
 */
public class Manuel extends FragmentActivity 
		implements OnMapClickListener, OnMarkerDragListener {
    private static final LatLng MADRID = new LatLng(40.416615, -3.703827);
    private static final double DEFAULT_RADIUS = 1000000;
    
    private GoogleMap mMap;

    private DraggableCircle dc;

    int color = 0;
    int alpha = 127;
    int width = 10;
    
    private int mStrokeColor;
    private int mFillColor;

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
        setContentView(R.layout.manuel);
        setUpMapIfNeeded();
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

        DraggableCircle circle = new DraggableCircle(MADRID, DEFAULT_RADIUS);
        dc = circle;

        // Move the map so that it is centered on the initial circle
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MADRID, 4.0f));
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
    	DraggableCircle circle = new DraggableCircle(point, DEFAULT_RADIUS);
        dc = circle;
		
		Toast toast = Toast.makeText(this, "Posicion: " + point.toString(), Toast.LENGTH_LONG);
	    toast.show();	
		
	}
}
