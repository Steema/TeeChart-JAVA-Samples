package com.steema.teechart.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class TeeChart extends SherlockActivity implements OnClickListener {

	Button buttonTeechart, buttonAbout, buttonExit, buttonStatistics, buttonSeries, buttonTools, buttonThemes;
	public ListView listview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		buttonTeechart = (Button) findViewById(R.id.buttonTeechart);
		buttonTeechart.setOnClickListener(this);
		buttonAbout = (Button) findViewById(R.id.buttonAbout);
		buttonAbout.setOnClickListener(this);
		buttonStatistics = (Button) findViewById(R.id.buttonStatistics);
		buttonStatistics.setOnClickListener(this);
		buttonExit = (Button) findViewById(R.id.buttonExit);
		buttonExit.setOnClickListener(this);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.buttonTeechart) {
			Intent myIntent = new Intent(v.getContext(), ListItem.class);
			startActivityForResult(myIntent, 0);

		} else if (v.getId() == R.id.buttonAbout) {
			Intent myIntent = new Intent(v.getContext(), About.class);
			startActivityForResult(myIntent, 0);

		} else if (v.getId() == R.id.buttonStatistics) {
			Intent myIntent = new Intent(v.getContext(), ListItemPhone.class);
			startActivityForResult(myIntent, 0);

		} else if (v.getId() == R.id.buttonExit) {
			finish();
		}
	}
}