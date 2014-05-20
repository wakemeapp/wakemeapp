package com.example.wakemeapp;

import java.util.Random;

import com.example.wakemeapp.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;

@SuppressLint("NewApi")
public class TypeFragment extends Fragment {

	private Button mDefault, mBigText, mInbox, mBigPicture, mRandom, mOld;

	private Notificaciones mContext;

	//private Randomizer mRandomizer;

	private Switch mButtonsEnabled;

	private RadioGroup mButtonsGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_notificaciones, container, false);

		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Notification notif = null;
				Notification.Builder builder = new Notification.Builder(mContext);

				// If random, add random buttons and take a random type
				if (v.getId() == R.id.btnCrear) {
					setRandomButtons(builder);
					
						// default
						notif = getDefaultNotification(builder);
						
					
				} 
				
				
				mContext.sendNotification(notif);
			}

		};

		// Version independent
		mDefault = (Button) v.findViewById(R.id.btnCrear);
		
		mDefault.setOnClickListener(listener);

		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = (Notificaciones) getActivity();

		//mRandomizer = new Randomizer(mContext);

	}

	@SuppressWarnings("deprecation")
	private Notification getDefaultNotification(Notification.Builder builder) {
		builder
				.setSmallIcon(R.drawable.ic_launcher)
				.setWhen(System.currentTimeMillis())
				.setContentTitle("Default notification")
				.setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
				.setContentInfo("Info");
				//.setLargeIcon(mRandomizer.getRandomImage());

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			// Yummy jelly beans
			return builder.build();
		} else {
			return builder.getNotification();
		}

	}

	private Notification getBigTextStyle(Notification.Builder builder) {
		builder
				.setContentTitle("Reduced BigText title")
				.setContentText("Reduced content")
				.setContentInfo("Info")
				.setSmallIcon(R.drawable.ic_launcher);
				//.setLargeIcon(mRandomizer.getRandomImage());

		return new Notification.BigTextStyle(builder)
				.bigText(getResources().getString(R.string.title_section1))
				.setBigContentTitle("Expanded BigText title")
				.setSummaryText("Summary text")
				.build();
	}
/*
	private Notification getBigPictureStyle(Notification.Builder builder) {
		// In this case the icon in reduced mode will be the same as the picture
		// when expanded.
		// And when expanded, the icon will be another one.
		Bitmap large = mRandomizer.getRandomImage();
	/Bitmap notSoLarge = mRandomizer.getRandomImage();
		builder
				.setContentTitle("Reduced BigPicture title")
				.setContentText("Reduced content")
				.setContentInfo("Info")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(large);

		return new Notification.BigPictureStyle(builder)
				.bigPicture(large)
				.bigLargeIcon(notSoLarge)
				.setBigContentTitle("Expanded BigPicture title")
				.setSummaryText("Summary text")
				.build();
	}
*/
	private Notification getInboxStyle(Notification.Builder builder) {
		builder
				.setContentTitle("Reduced Inbox title")
				.setContentText("Reduced content")
				.setContentInfo("Info")
				.setSmallIcon(R.drawable.ic_launcher);
				//.setLargeIcon(mRandomizer.getRandomImage());

		Notification.InboxStyle n = new Notification.InboxStyle(builder)
				.setBigContentTitle("Expanded Inbox title")
				.setSummaryText("Summary text");

		// Add 10 lines
		for (int i = 0; i < 10; i++) {
			n.addLine("This is the line n� " + (i + 1));
		}

		return n.build();
	}

	private Notification getOldNotification() {
		Notification notif = new Notification(R.drawable.ic_launcher, null, System.currentTimeMillis());
		notif.setLatestEventInfo(mContext, "Old title", "Old notification content text", PendingIntent.getActivity(mContext, 0, new Intent(), 0));
		return notif;
	}

	private void setRandomButtons(Notification.Builder builder) {
		//setButtons(builder, new Random().nextInt(4));
	}

	/*private void setButtons(Notification.Builder builder, Integer buttons) {
		// Buttons only in Jelly Bean
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			// Get number of buttons
			if (buttons == null) {
				// If not specified, check the input
				buttons = 0;
				if (mButtonsEnabled.isChecked()) {
					switch (mButtonsGroup.getCheckedRadioButtonId()) {
					case R.id.radio0:
						buttons = 1;
						break;
					case R.id.radio1:
						buttons = 2;
						break;
					case R.id.radio2:
						buttons = 3;
						break;
					}
				}
			}
			// Add as many buttons as you have to
			PendingIntent intent = PendingIntent.getActivity(mContext, 0, new Intent(), 0);
			for (int i = 0; i < buttons; i++) {
				builder.addAction(mRandomizer.getRandomIconId(), "Action " + (i + 1), intent);
			}
		}
	} */

}
