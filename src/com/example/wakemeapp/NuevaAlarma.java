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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
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
			alarma = bd.getAlarmasPredeterminadas(getApplicationContext()).get(
					0);
		}

		txtNombre = (TextView) findViewById(R.id.txtUbicacion);
		txtNombre.setText(alarma.getNombre());

		txtCancion = (TextView) findViewById(R.id.txtRepetir);
		txtCancion.setText(alarma.getCancion());

		txtCancion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pickRingtone(v);

			}

			public void pickRingtone(View view) {
				// TODO Auto-generated method. stub

				Intent intent = new Intent(
						RingtoneManager.ACTION_RINGTONE_PICKER);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
						RingtoneManager.TYPE_RINGTONE);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
						"Select Ringtone");

				// for existing ringtone
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

				TextView lblNumero = (TextView) findViewById(R.id.lblNumero);
				lblNumero.setText(Integer.toString(getProgresoConPaso(progress,
						stepSize)) + " metros");
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
				getparametros();

				BDOperaciones bd = new BDOperaciones();
				bd.insertarAlarma(NuevaAlarma.this.getApplicationContext(),
						alarma);

				// System.out.println("Mi alarma es: " + alarma.getId() + " " +
				// alarma.getNombre() + " " + alarma.getCancion() + " " +
				// alarma.getDistancia() + " " + alarma.isFavorito() + " " +
				// alarma.isActiva());

				Intent intent = new Intent(NuevaAlarma.this, Principal.class);
				startActivity(intent);
			}
		});

		Button btnmapa = (Button) findViewById(R.id.btnmapa);
		btnmapa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getparametros();

				Intent intent = new Intent(NuevaAlarma.this, Manuel.class);
				intent.putExtra("Alarma", new Gson().toJson(alarma));
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == 5) {
			if (resultCode == RESULT_OK) {
				// Make sure the request was successful

				Uri uri = data
						.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void getparametros() {
		alarma.setNombre(txtNombre.getText().toString());
		//alarma.setCancion(txtCancion.getText().toString());
		alarma.setDistancia(getProgresoConPaso(skbDistancia.getProgress(),
				stepSize));
		alarma.setFavorito(tbnFavoritos.isChecked());
		alarma.setActiva(tbnActivar.isChecked());
	}

}
