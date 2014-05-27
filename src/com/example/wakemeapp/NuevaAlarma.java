package com.example.wakemeapp;

import com.google.gson.Gson;

import clases.Alarma;
import Persistencia.BDOperaciones;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class NuevaAlarma extends Activity {

	private Alarma alarma = new Alarma();

	private TextView txtNombre;
	private TextView txtCancion;
	private SeekBar skbDistancia;
	private ToggleButton tbnFavoritos;
	private ToggleButton tbnActivar;
	private TextView lblNumero;
	private int stepSize = 5;

	@Override
	public void onBackPressed() {		
		Intent intent = new Intent(NuevaAlarma.this, Principal.class);
        startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevaalarma);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String s = extras.getString("Alarma");
			alarma = new Gson().fromJson(s, Alarma.class);
		} else {
			BDOperaciones bd = new BDOperaciones();
			alarma = bd.getAlarmasPredeterminadas(getApplicationContext()).get(0);
		}

		txtNombre = (TextView) findViewById(R.id.txtUbicacion);
		txtNombre.setText(alarma.getNombre());

		txtCancion = (TextView) findViewById(R.id.txtRepetir);
		
		Ringtone ringtone = RingtoneManager.getRingtone(this, Uri.parse(alarma.getCancion()));
		txtCancion.setText(ringtone.getTitle(this));

		txtCancion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pickRingtone(v);
			}

			public void pickRingtone(View view) {
				Intent intent = new Intent(
						RingtoneManager.ACTION_RINGTONE_PICKER);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
						RingtoneManager.TYPE_RINGTONE);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
						"Seleccione un tono");
				Uri urie = RingtoneManager.getActualDefaultRingtoneUri(
						getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
						urie);

				startActivityForResult(intent, 5);
			}
		});

		skbDistancia = (SeekBar) findViewById(R.id.skbDistancia);
		skbDistancia.setProgress(alarma.getDistancia());

		tbnFavoritos = (ToggleButton) findViewById(R.id.tbnFavoritos);
		tbnFavoritos.setChecked(alarma.isFavorito());

		tbnActivar = (ToggleButton) findViewById(R.id.tbnActivar);
		tbnActivar.setChecked(alarma.isActiva());

		lblNumero = (TextView) findViewById(R.id.lblNumero);
		lblNumero.setText(String.valueOf(alarma.getDistancia()) + " metros");

		skbDistancia.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				TextView lblNumero = (TextView) findViewById(R.id.lblNumero);
				lblNumero.setText(Integer.toString(getProgresoConPaso(progress, stepSize)) + " metros");
				alarma.setDistancia(getProgresoConPaso(progress, stepSize));
			}
		});

		Button btnAtras = (Button) findViewById(R.id.btnAtras);
		btnAtras.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NuevaAlarma.this, Principal.class);
				startActivity(intent);
			}
		});

		Button btnCrear = (Button) findViewById(R.id.btnCrear);
		btnCrear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
				if(txtNombre.getText().toString().compareTo("") == 0 || txtCancion.getText().toString().compareTo("") == 0 || (!tbnActivar.isChecked() && !tbnFavoritos.isChecked())) {
					System.out.println("Error VALIDACION");
					Toast toast = Toast.makeText(getApplicationContext(),"La configuración es incorrecta. Revise los campos.", Toast.LENGTH_LONG);
				    toast.show();
				} else {
					System.out.println("BUENA VALIDACION");
					getparametros();
					BDOperaciones bd = new BDOperaciones();
					bd.insertarAlarma(NuevaAlarma.this.getApplicationContext(),	alarma);

					Intent intent = new Intent(NuevaAlarma.this, Principal.class);
					startActivity(intent);
				}
			}
		});

		Button btnmapa = (Button) findViewById(R.id.btnmapa);
		btnmapa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getparametros();
				Intent intent = new Intent(NuevaAlarma.this, Mapa.class);
				intent.putExtra("Alarma", new Gson().toJson(alarma));
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 5) {
			if (resultCode == RESULT_OK) {
				Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				if (uri != null) {
					String ringTonePath = uri.toString();
					alarma.setCancion(ringTonePath);
					System.out.println("La ruta es:" + ringTonePath);
					Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
					String title = ringtone.getTitle(this);
					txtCancion.setText(title);
				}
			}
		}
	}

	private int getProgresoConPaso(int progreso, int paso) {
		return (progreso / paso) * paso;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.config:
	        	Intent intent = new Intent(NuevaAlarma.this, ConfiguracionDefecto.class);
                startActivity(intent);
	            return true;	        
	    }
		return false;
	}

	private void getparametros() {
		alarma.setNombre(txtNombre.getText().toString());
		alarma.setDistancia(getProgresoConPaso(skbDistancia.getProgress(), stepSize));
		alarma.setFavorito(tbnFavoritos.isChecked());
		alarma.setActiva(tbnActivar.isChecked());
	}

}
