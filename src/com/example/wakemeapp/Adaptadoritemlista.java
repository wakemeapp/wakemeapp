package com.example.wakemeapp;

import java.util.List;

import clases.alarma;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;





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
 
        TextView lblTitulo = (TextView)item.findViewById(R.id.Lbldestino);
        lblTitulo.setText(items.get(position).nombre);
 
        TextView lblSubtitulo = (TextView)item.findViewById(R.id.Lbldistancia);
        
        lblSubtitulo.setText(Integer.toString(items.get(position).distancia));
 
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
