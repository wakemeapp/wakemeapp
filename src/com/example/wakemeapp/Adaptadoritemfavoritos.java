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
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;





public class Adaptadoritemfavoritos extends BaseAdapter {	
	 
    private Activity context;
    private List<Alarma> items;
 
    public Adaptadoritemfavoritos(Activity context, List<Alarma> items) {
    	this.context = context;
    	this.items = items;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View item = inflater.inflate(R.layout.itemfavoritos, null);
        final Alarma al = items.get(position);
        
        TextView lblnombre = (TextView)item.findViewById(R.id.lblnombre);
        lblnombre.setText(al.getNombre());
        
        Uri rutaCancion = Uri.parse(al.getCancion());
        Ringtone ring = RingtoneManager.getRingtone(Adaptadoritemfavoritos.this.context, rutaCancion);
        TextView lblcancion = (TextView)item.findViewById(R.id.lblcancion);
        lblcancion.setText(ring.getTitle(Adaptadoritemfavoritos.this.context));
 
        TextView lbldistancia = (TextView)item.findViewById(R.id.lbldistancia);        
        lbldistancia.setText(Integer.toString(al.getDistancia()));
        
        ToggleButton tbnactiva = (ToggleButton) item.findViewById(R.id.btnactiva);
        tbnactiva.setChecked(al.isActiva());
        
        tbnactiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        al.setActiva(isChecked);
		        BDOperaciones bdo = new BDOperaciones();
		    	bdo.modificarActiva(item.getContext(), al.getId(), isChecked);
		        
		    }
		});
        
        ImageButton btnbasura = (ImageButton)item.findViewById(R.id.btnbasura);
        btnbasura.setOnClickListener(new OnClickListener() {
             public void onClick(View v) {
            	 BDOperaciones bdo = new BDOperaciones();
            	 
            	 if(al.isActiva())
            	 {
            		 bdo.modificarFavorito(item.getContext(), al.getId(), false);
            	 }
            	 else
            	 {
            		 bdo.eliminarAlarma(item.getContext(), al.getId());
            	 }
 		    	 
                 Intent intent = new Intent(item.getContext(), Favoritos.class);
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
