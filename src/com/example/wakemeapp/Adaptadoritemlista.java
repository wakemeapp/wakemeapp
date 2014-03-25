package com.example.wakemeapp;

import java.util.List;

import clases.alarma;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;





public class Adaptadoritemlista extends BaseAdapter {	
	 
    private Activity context;
    private List<alarma> items;
 
    public Adaptadoritemlista(Activity context, List<alarma> items) {
    	this.context = context;
    	this.items = items;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.itemlista, null);
        final alarma al = items.get(position);
        
        TextView lblnombre = (TextView)item.findViewById(R.id.lblnombre);
        lblnombre.setText(al.nombre);
 
        TextView lbldestino = (TextView)item.findViewById(R.id.lbldestino);
        lbldestino.setText(al.direccion);
 
        TextView lbldistancia = (TextView)item.findViewById(R.id.lbldistancia);        
        lbldistancia.setText(Integer.toString(al.distancia));
        
        ToggleButton tbnactiva = (ToggleButton) item.findViewById(R.id.btnactiva);
        tbnactiva.setChecked(al.activa);
        
        tbnactiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        al.activa = isChecked;		        
		    }
		});
        return(item);
    }

    public int getCount() {
        return items.size();
    }
 
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
 
    public long getItemId(int position) {
        return position;
    }
}
