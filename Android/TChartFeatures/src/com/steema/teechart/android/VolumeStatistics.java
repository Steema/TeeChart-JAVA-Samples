package com.steema.teechart.android;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.steema.teechart.TChart;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.DragListener;
import com.steema.teechart.events.SeriesPaintListener;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.DragPoint;
import com.steema.teechart.tools.DragPointStyle;

public class VolumeStatistics extends SherlockActivity implements OnClickListener, DragListener, SeriesPaintListener {

	private AudioManager audio;
	public Device device;
	public TChart chart;
	private int alarmVolume, musicVolume, notificationVolume, ringVolume, systemVolume, voiceCallVolume;
	private Bar maxVolume, volume;
	private boolean alarmVolumeChanged = false, musicVolumeChanged = false, notificationVolumeChanged = false, ringVolumeChanged = false,
			systemVolumeChanged = false, voiceCallVolumeChanged = false, dialogEnabled = false;
	Dialog dialog;
	TextView description;
	Button okButton;

	@SuppressWarnings("static-access")
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.soundstatistics);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		Button saveChanges = (Button) findViewById(R.id.buttonsave);
		saveChanges.setOnClickListener(this);
		chart = new TChart(this);
		group.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(false);
		alarmVolume = audio.getStreamVolume(audio.STREAM_ALARM);
		musicVolume = audio.getStreamVolume(audio.STREAM_MUSIC);
		notificationVolume = audio.getStreamVolume(audio.STREAM_NOTIFICATION);
		ringVolume = audio.getStreamVolume(audio.STREAM_RING);
		systemVolume = audio.getStreamVolume(audio.STREAM_SYSTEM);
		voiceCallVolume = audio.getStreamVolume(audio.STREAM_VOICE_CALL);

		ThemesList.applyTheme(chart.getChart(), 1);

		maxVolume = new Bar(chart.getChart());
		maxVolume.add(new int[] { audio.getStreamMaxVolume(audio.STREAM_ALARM), audio.getStreamMaxVolume(audio.STREAM_MUSIC),
				audio.getStreamMaxVolume(audio.STREAM_NOTIFICATION), audio.getStreamMaxVolume(audio.STREAM_RING),
				audio.getStreamMaxVolume(audio.STREAM_SYSTEM), audio.getStreamMaxVolume(audio.STREAM_VOICE_CALL) });
		volume = new Bar(chart.getChart());
		volume.add(new int[] { audio.getStreamVolume(audio.STREAM_ALARM), audio.getStreamVolume(audio.STREAM_MUSIC),
				audio.getStreamVolume(audio.STREAM_NOTIFICATION), audio.getStreamVolume(audio.STREAM_RING),
				audio.getStreamVolume(audio.STREAM_SYSTEM), audio.getStreamVolume(audio.STREAM_VOICE_CALL) });

		maxVolume.getLabels().setString(0, "Alarm");
		maxVolume.getLabels().setString(1, "Music");
		maxVolume.getLabels().setString(2, "Notif.");
		maxVolume.getLabels().setString(3, "Ring");
		maxVolume.getLabels().setString(4, "System");
		maxVolume.getLabels().setString(5, "VoiceCall");
		maxVolume.getMarks().setVisible(false);
		chart.getLegend().setVisible(false);
		DragPoint toolDragPoint;
		toolDragPoint = new DragPoint(volume);
		toolDragPoint.setStyle(DragPointStyle.Y);
		toolDragPoint.addDragListener(this);
		maxVolume.setMultiBar(MultiBars.NONE);
		volume.setMultiBar(MultiBars.NONE);
		volume.setStackGroup(0);
		maxVolume.setStackGroup(0);
		chart.setText("Volume statistics tool, drag bars to change selected volume");
		volume.addSeriesPaintListener(this);

		// We hide the Tabs and enable Home button
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@SuppressWarnings("static-access")
	public void onClick(View v) {
		if (alarmVolume != (int) volume.getMarkValue(0)) {
			audio.setStreamVolume(audio.STREAM_ALARM, (int) volume.getMarkValue(0), AudioManager.FLAG_VIBRATE);
			alarmVolumeChanged = true;
		}
		if (musicVolume != (int) volume.getMarkValue(1)) {
			audio.setStreamVolume(audio.STREAM_MUSIC, (int) volume.getMarkValue(1), AudioManager.FLAG_VIBRATE);

			musicVolumeChanged = true;
		}
		if (notificationVolume != (int) volume.getMarkValue(2)) {
			audio.setStreamVolume(audio.STREAM_NOTIFICATION, (int) volume.getMarkValue(2), AudioManager.FLAG_VIBRATE);

			notificationVolumeChanged = true;
		}
		if (ringVolume != (int) volume.getMarkValue(3)) {
			audio.setStreamVolume(audio.STREAM_RING, (int) volume.getMarkValue(3), AudioManager.FLAG_VIBRATE);

			ringVolumeChanged = true;
		}
		if (systemVolume != (int) volume.getMarkValue(4)) {
			audio.setStreamVolume(audio.STREAM_SYSTEM, (int) volume.getMarkValue(4), AudioManager.FLAG_VIBRATE);

			systemVolumeChanged = true;
		}
		if (voiceCallVolume != (int) volume.getMarkValue(5)) {
			audio.setStreamVolume(audio.STREAM_VOICE_CALL, (int) volume.getMarkValue(5), AudioManager.FLAG_VIBRATE);

			voiceCallVolumeChanged = true;
		}
		String dialogText = new String("");
		if (alarmVolumeChanged) {
			dialogText = dialogText.concat("\n Alarm volume changed. Then: " + alarmVolume + " Now: " + (int) volume.getMarkValue(0) + "\n");
			alarmVolume = (int) volume.getMarkValue(0);
			alarmVolumeChanged = false;
			dialogEnabled = true;
		}
		if (musicVolumeChanged) {
			dialogText = dialogText.concat("\n Music volume changed. Then: " + musicVolume + " Now: " + (int) volume.getMarkValue(1) + "\n");
			musicVolume = (int) volume.getMarkValue(1);
			musicVolumeChanged = false;
			dialogEnabled = true;
		}
		if (notificationVolumeChanged) {
			dialogText =
					dialogText.concat("\n Notification volume changed. Then: " + notificationVolume + " Now: " + (int) volume.getMarkValue(2) + "\n");
			notificationVolume = (int) volume.getMarkValue(2);
			notificationVolumeChanged = false;
			dialogEnabled = true;
		}
		if (ringVolumeChanged) {
			dialogText = dialogText.concat("\n Ring volume changed. Then: " + ringVolume + " Now: " + (int) volume.getMarkValue(3) + "\n");
			ringVolume = (int) volume.getMarkValue(3);
			ringVolumeChanged = false;
			dialogEnabled = true;

		}
		if (systemVolumeChanged) {
			dialogText = dialogText.concat("\n System volume changed. Then: " + systemVolume + " Now: " + (int) volume.getMarkValue(4) + "\n");
			systemVolume = (int) volume.getMarkValue(4);
			systemVolumeChanged = false;
			dialogEnabled = true;
		}
		if (voiceCallVolumeChanged) {
			dialogText = dialogText.concat("\n VoiceCall volume changed. Then: " + voiceCallVolume + " Now: " + (int) volume.getMarkValue(5) + "\n");
			voiceCallVolume = (int) volume.getMarkValue(5);
			voiceCallVolumeChanged = false;
			dialogEnabled = true;
		}
		if (dialogEnabled) {
			dialog = new Dialog(VolumeStatistics.this);
			dialog.setContentView(R.layout.description);
			dialog.show();
			dialog.setTitle("Map Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(dialogText);
			description.setTextColor(0xFFFFFFFF);
			okButton = (Button) dialog.findViewById(R.id.okButton);
			okButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					dialog.cancel();
					dialogEnabled = false;

				}

			});
		}
	}

	public void dragFinished(ChangeEvent e) {
	}

	public void dragMoving(ChangeEvent e) {
	}

	@SuppressWarnings("static-access")
	public void seriesPainting(ChartDrawEvent e) {
		if (volume.getMarkValue(0) > audio.getStreamMaxVolume(audio.STREAM_ALARM)) {
			volume.getYValues().setValue(0, audio.getStreamMaxVolume(audio.STREAM_ALARM));
		} else if (volume.getMarkValue(0) < 0) {
			volume.getYValues().setValue(0, 0);
		} else {
			if ((volume.getMarkValue(0) - (int) volume.getMarkValue(0)) >= 0.5) {
				volume.getYValues().setValue(0, (int) ((volume.getMarkValue(0)) + 1));
			} else {
				volume.getYValues().setValue(0, (int) volume.getMarkValue(0));
			}
		}
		if (volume.getMarkValue(1) > audio.getStreamMaxVolume(audio.STREAM_MUSIC)) {
			volume.getYValues().setValue(1, audio.getStreamMaxVolume(audio.STREAM_MUSIC));
		} else if (volume.getMarkValue(1) < 0) {
			volume.getYValues().setValue(1, 0);
		} else {
			if ((volume.getMarkValue(1) - (int) volume.getMarkValue(1)) >= 0.5) {
				volume.getYValues().setValue(1, (int) ((volume.getMarkValue(1)) + 1));
			} else {
				volume.getYValues().setValue(1, (int) volume.getMarkValue(1));
			}
		}
		if (volume.getMarkValue(2) > audio.getStreamMaxVolume(audio.STREAM_NOTIFICATION)) {
			volume.getYValues().setValue(2, audio.getStreamMaxVolume(audio.STREAM_NOTIFICATION));
		} else if (volume.getMarkValue(2) < 0) {
			volume.getYValues().setValue(2, 0);
		} else {
			if ((volume.getMarkValue(2) - (int) volume.getMarkValue(2)) >= 0.5) {
				volume.getYValues().setValue(2, (int) ((volume.getMarkValue(2)) + 1));
			} else {
				volume.getYValues().setValue(2, (int) volume.getMarkValue(2));
			}
		}
		if (volume.getMarkValue(3) > audio.getStreamMaxVolume(audio.STREAM_RING)) {
			volume.getYValues().setValue(3, audio.getStreamMaxVolume(audio.STREAM_RING));
		} else if (volume.getMarkValue(3) < 0) {
			volume.getYValues().setValue(3, 0);
		} else {
			if ((volume.getMarkValue(3) - (int) volume.getMarkValue(3)) >= 0.5) {
				volume.getYValues().setValue(3, (int) ((volume.getMarkValue(3)) + 1));
			} else {
				volume.getYValues().setValue(3, (int) volume.getMarkValue(3));
			}
		}
		if (volume.getMarkValue(4) > audio.getStreamMaxVolume(audio.STREAM_SYSTEM)) {
			volume.getYValues().setValue(4, audio.getStreamMaxVolume(audio.STREAM_SYSTEM));
		} else if (volume.getMarkValue(4) < 0) {
			volume.getYValues().setValue(4, 0);
		} else {
			if ((volume.getMarkValue(4) - (int) volume.getMarkValue(4)) >= 0.5) {
				volume.getYValues().setValue(4, (int) ((volume.getMarkValue(4)) + 1));
			} else {
				volume.getYValues().setValue(4, (int) volume.getMarkValue(4));
			}
		}
		if (volume.getMarkValue(5) > audio.getStreamMaxVolume(audio.STREAM_VOICE_CALL)) {
			volume.getYValues().setValue(5, audio.getStreamMaxVolume(audio.STREAM_VOICE_CALL));
		} else if (volume.getMarkValue(5) < 0) {
			volume.getYValues().setValue(5, 0);
		} else {
			if ((volume.getMarkValue(5) - (int) volume.getMarkValue(5)) >= 0.5) {
				volume.getYValues().setValue(5, (int) ((volume.getMarkValue(5)) + 1));
			} else {
				volume.getYValues().setValue(5, (int) volume.getMarkValue(5));
			}
		}
	}

	public void seriesPainted(ChartDrawEvent e) {
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent parentActivityIntent = new Intent(this, ListItemPhone.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}