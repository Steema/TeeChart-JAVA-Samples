package com.steema.teechart.android;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.steema.teechart.TChart;
import com.steema.teechart.styles.CircularGauge;
import com.steema.teechart.themes.ThemesList;

public class BatteryStatistics extends SherlockActivity {

	public Device device;
	public TChart chart;
	Dialog dialog;
	TextView description;
	Button okButton;
	private CircularGauge cGauge;
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			int level = intent.getIntExtra("level", 0);
			cGauge = new CircularGauge(chart.getChart());
			cGauge.setRedLineStartValue(0.0);
			cGauge.setRedLineEndValue(20.0);
			cGauge.setGreenLineStartValue(20.0);
			cGauge.setGreenLineEndValue(100.0);
			cGauge.getNumericGauge().setValue(level);
			cGauge.setValue(level);

		}
	};

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.batterystatistics);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		chart = new TChart(this);
		group.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(false);

		ThemesList.applyTheme(chart.getChart(), 1);
		this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		chart.setText("Battery charge tool\n you can see battery percent\n in  circular gauge with embedded numeric gauge");

		// We hide the Tabs and enable Home button
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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