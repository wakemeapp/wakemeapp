package com.example.wakemeapp;

import java.util.List;

import clases.Alarma;
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
    private List<Alarma> items;
 
    public Adaptadoritemlista(Activity context, List<Alarma> items) {
    	this.context = context;
    	this.items = items;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.itemlista, null);
        final Alarma al = items.get(position);
        
        TextView lblnombre = (TextView)item.findViewById(R.id.lblnombre);
        lblnombre.setText(al.getNombre());
 
        TextView lblcancion = (TextView)item.findViewById(R.id.lblcancion);
        lblcancion.setText(al.getCancion());
 
        TextView lbldistancia = (TextView)item.findViewById(R.id.lbldistancia);        
        lbldistancia.setText(Integer.toString(al.getDistancia()));
        
        ToggleButton tbnactiva = (ToggleButton) item.findViewById(R.id.btnactiva);
        tbnactiva.setChecked(al.isActiva());
        
        tbnactiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        al.setActiva(isChecked);
		        
		        
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
