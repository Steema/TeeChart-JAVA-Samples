package com.steema.teechart.android;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.steema.teechart.TChart;

public class ListItemPhone extends SherlockActivity {

	public ListView listview;
	public String[] listItemPhone;
	public EditText textEdit;
	public final Context context = ListItemPhone.this;
	public CustomAdapter lvAdapter;
	public ArrayList<Device> m_Devices;
	public int toolsUsed;
	public Device device;
	public TChart chart;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		listItemPhone = getResources().getStringArray(R.array.PhoneStatisticsArray);
		setContentView(R.layout.list_item_phone);
		listview = (ListView) findViewById(R.id.listViewPhone);
		listview.setAdapter(null);
		ArrayList<Device> m_Devices = new ArrayList<Device>();
		for (int i = 0; i < listItemPhone.length; i++) {
			device = new Device(listItemPhone[i], i, 3);
			m_Devices.add(device);
		}

		lvAdapter = new CustomAdapter(context, m_Devices);
		listview.setAdapter(lvAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arg2 == 0) {
					Intent myIntent = new Intent(arg1.getContext(), VolumeStatistics.class);
					startActivityForResult(myIntent, 0);
				} else if (arg2 == 1) {
					Intent myIntent = new Intent(arg1.getContext(), BatteryStatistics.class);
					startActivityForResult(myIntent, 0);
				} else if (arg2 == 2) {
					Intent myIntent = new Intent(arg1.getContext(), WIFIStatistics.class);
					startActivityForResult(myIntent, 0);
				} else if (arg2 == 3) {
					Intent myIntent = new Intent(arg1.getContext(), ImageStatistics.class);
					startActivityForResult(myIntent, 0);
				}
			}
		});

		// We hide the Tabs and enable Home button
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent parentActivityIntent = new Intent(this, TeeChart.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
