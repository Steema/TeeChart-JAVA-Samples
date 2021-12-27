package com.steema.teechart.android;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.ValueListOrder;
import com.steema.teechart.themes.ThemesList;

public class WIFIStatistics extends Activity implements OnClickListener {
	WifiManager wifi;
	public Device device;
	public TChart chart;
	Dialog dialog;
	TextView description;
	Button okButton, scanButton, cancelButton;
	BroadcastReceiver receiver;
	private Bar availableNetworks;


	@SuppressWarnings("static-access")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		while (wifi.getWifiState() == wifi.WIFI_STATE_ENABLING) {

		}
		if (wifi.isWifiEnabled()) {
		
			setContentView(R.layout.wifistatistics);
			scanButton = (Button) findViewById(R.id.buttonScan);
			scanButton.setOnClickListener(this);
			LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
			chart = new TChart(this);
			group.addView(chart);
			chart.getPanel().setBorderRound(7);
			chart.getAspect().setView3D(false);
			ThemesList.applyTheme(chart.getChart(), 1);
			chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
			chart.getAxes().getAxis(0).setMaximum(100);
			chart.getAxes().getAxis(0).setAutomaticMaximum(false);
			chart.getLegend().getTitle().setVisible(true);
			chart.getLegend().getTitle().setText("Intensity level in %");

			// Setup WiFi
			wifi.startScan();
			List<ScanResult> results = wifi.getScanResults();
			String[] networksName = new String[10];
			int[] networksSignal = new int[10];
			for (int i = 0; i < results.size() && i < 10; i++) {
				networksName[i] = results.get(i).SSID;
				if (WifiManager.calculateSignalLevel(results.get(i).level, 10) == 0) {
					networksSignal[i] = 1;
				} else {
					networksSignal[i] = WifiManager.calculateSignalLevel(
							results.get(i).level, 10);

				}
			}
			availableNetworks = new Bar(chart.getChart());
			availableNetworks.getYValues().setOrder(ValueListOrder.DESCENDING);
			for (int i = 0; i < results.size() && i < 10; i++) {
				double value = networksSignal[i] * 10;
				availableNetworks.add(networksSignal[i] * 10, networksName[i],
						setColor(value));
			}

			availableNetworks.getYValues().sort();
			availableNetworks.getXValues().fillSequence();
		} else {
			dialog = new Dialog(WIFIStatistics.this);
			dialog.setContentView(R.layout.acceptcancel);
			dialog.show();
			dialog.setTitle("Wifi not enabled");
			description = (TextView) dialog.findViewById(R.id.descriptionText1);
			description
					.setText("Wifi is not enabled\nWould you like to enable WiFi? OK/CANCEL");
			description.setTextColor(0xFFFFFFFF);
			okButton = (Button) dialog.findViewById(R.id.okButton);
			okButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					wifi.setWifiEnabled(true);
					dialog.cancel();
					Intent myIntent = new Intent(v.getContext(),
							WIFIStatistics.class);
					startActivityForResult(myIntent, 0);
					finish();
				}

			});
			cancelButton = (Button) dialog.findViewById(R.id.buttonCancel);
			cancelButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					dialog.cancel();
					finish();
				}

			});

		}

		if (wifi.isWifiEnabled()) {
			setContentView(R.layout.wifistatistics);
			scanButton = (Button) findViewById(R.id.buttonScan);
			scanButton.setOnClickListener(this);
			LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
			chart = new TChart(this);
			group.addView(chart);
			chart.getPanel().setBorderRound(7);
			chart.getAspect().setView3D(false);
			ThemesList.applyTheme(chart.getChart(), 1);
			chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
			chart.getAxes().getAxis(0).setMaximum(100);
			chart.getAxes().getAxis(0).setAutomaticMaximum(false);
			chart.getLegend().getTitle().setVisible(true);
			chart.getLegend().getTitle().setText("Intensity level in %");

			// Setup WiFi

			wifi.startScan();
			List<ScanResult> results = wifi.getScanResults();
			String[] networksName = new String[10];
			int[] networksSignal = new int[10];
			for (int i = 0; i < results.size() && i < 10; i++) {
				networksName[i] = results.get(i).SSID;
				if (WifiManager.calculateSignalLevel(results.get(i).level, 10) == 0) {
					networksSignal[i] = 1;
				} else {
					networksSignal[i] = WifiManager.calculateSignalLevel(
							results.get(i).level, 10);

				}
			}
			availableNetworks = new Bar(chart.getChart());
			availableNetworks.getYValues().setOrder(ValueListOrder.DESCENDING);
			for (int i = 0; i < results.size() && i < 10; i++) {
				double value = networksSignal[i] * 10;
				availableNetworks.add(networksSignal[i] * 10, networksName[i],
						setColor(value));
			}

			availableNetworks.getYValues().sort();
			availableNetworks.getXValues().fillSequence();
		}
	}

	public void onClick(View v) {

		if (wifi.isWifiEnabled()) {
			wifi.startScan();

			List<ScanResult> results = wifi.getScanResults();
			String[] networksName = new String[10];
			int[] networksSignal = new int[10];
			for (int i = 0; i < results.size() && i < 10; i++) {
				networksName[i] = results.get(i).SSID;
				if (WifiManager.calculateSignalLevel(results.get(i).level, 10) == 0) {
					networksSignal[i] = 1;
				} else {
					networksSignal[i] = WifiManager.calculateSignalLevel(
							results.get(i).level, 10);

				}
			}
			chart.removeAllSeries();
			availableNetworks = new Bar(chart.getChart());
			availableNetworks.getYValues().setOrder(ValueListOrder.DESCENDING);
			for (int i = 0; i < results.size() && i < 10; i++) {
				double value = networksSignal[i] * 10;
				availableNetworks.add(value, networksName[i], setColor(value));
			}

			availableNetworks.getYValues().sort();
			availableNetworks.getXValues().fillSequence();
			chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		}
	}

	public Color setColor(double value) {
		if (value >= 0 && value <= 19) {
			return Color.fromArgb(255, 0, 0);
		} else if (value >= 20 && value <= 39) {
			return Color.fromArgb(255, 64, 0);
		} else if (value >= 40 && value <= 59) {
			return Color.fromArgb(255, 128, 0);
		} else if (value >= 60 && value <= 79) {
			return Color.fromArgb(255, 255, 0);
		} else if (value >= 80 && value <= 100) {
			return Color.fromArgb(0, 255, 0);
		} else
			return Color.fromArgb(0, 255, 0);
	}
}