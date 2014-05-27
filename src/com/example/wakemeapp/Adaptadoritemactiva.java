package com.example.wakemeapp;

import java.util.List;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import clases.Alarma;
import Persistencia.BDOperaciones;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Adaptadoritemactiva extends BaseAdapter {	
	 
    private Activity context;
    private List<Alarma> items;
 
    public Adaptadoritemactiva(Activity context, List<Alarma> items) {
    	this.context = context;
    	this.items = items;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View item = inflater.inflate(R.layout.itemactiva, null);
        final Alarma al = items.get(position);
        
        TextView lblnombre = (TextView)item.findViewById(R.id.lblnombre);
        lblnombre.setText(al.getNombre());
       
        Uri rutaCancion = Uri.parse(al.getCancion());
        Ringtone ring = RingtoneManager.getRingtone(Adaptadoritemactiva.this.context, rutaCancion);
        TextView lblcancion = (TextView)item.findViewById(R.id.lblcancion);
        lblcancion.setText(ring.getTitle(Adaptadoritemactiva.this.context));
 
        TextView lbldistancia = (TextView)item.findViewById(R.id.lbldistancia);        
        lbldistancia.setText(Integer.toString(al.getDistancia()));
        
        ToggleButton tbnactiva = (ToggleButton) item.findViewById(R.id.btnactiva);
        tbnactiva.setChecked(al.isActiva());
        
        tbnactiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        al.setActiva(isChecked);
		        BDOperaciones bdo = new BDOperaciones();
		        if(al.isFavorito())
		        {
		        	bdo.modificarActiva(item.getContext(), al.getId(), isChecked);
		        }
		        else
		        {
		        	bdo.eliminarAlarma(item.getContext(), al.getId());
		        }
		    	
		    	Intent intent = new Intent(item.getContext(), AlarmasActivas.class);
                item.getContext().startActivity(intent);
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
