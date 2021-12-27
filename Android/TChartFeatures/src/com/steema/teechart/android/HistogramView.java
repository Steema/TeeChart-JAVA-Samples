package com.steema.teechart.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.steema.teechart.TChart;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Area;
import com.steema.teechart.themes.ThemesList;

// View from Histogram Feature

public class HistogramView extends Activity {

	public TChart chart;
	private Bundle extras;
	int[] redValues, greenValues, blueValues;
	private Area redLine, greenLine, blueLine;
	int[] redHistogram = new int[256], blueHistogram = new int[256], greenHistogram = new int[256];
	Axis axis1, axis2;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.histogramview);
		LinearLayout layoutR = (LinearLayout) findViewById(R.id.LinearLayoutR);
		chart = new TChart(this);
		layoutR.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(false);
		chart.getLegend().setVisible(false);
		ThemesList.applyTheme(chart.getChart(), 1);
		chart.setText("RGB histogram");

		redLine = new Area(chart.getChart());
		redLine.getMarks().setVisible(false);
		redLine.setTitle("Source");
		redLine.setColor(Color.RED);
		redLine.getLinePen().setColor(Color.RED);
		redLine.getAreaLinesPen().setColor(Utils.darkenColor(Color.RED, 60));

		blueLine = new com.steema.teechart.styles.Area(chart.getChart());
		blueLine.getMarks().setVisible(false);
		blueLine.setColor(Color.BLUE);
		blueLine.getLinePen().setColor(Color.BLUE);
		blueLine.getAreaLinesPen().setColor(Utils.darkenColor(Color.BLUE, 60));

		greenLine = new com.steema.teechart.styles.Area(chart.getChart());
		greenLine.getMarks().setVisible(false);
		greenLine.setColor(Color.GREEN);
		greenLine.getLinePen().setColor(Color.GREEN);
		greenLine.getAreaLinesPen().setColor(Utils.darkenColor(Color.GREEN, 60));

		extras = getIntent().getExtras();
		redValues = extras.getIntArray("redValues");
		greenValues = extras.getIntArray("greenValues");
		blueValues = extras.getIntArray("blueValues");

		for (int i = 0; i < redValues.length; i++) {
			if (redValues[i] == 0) {
				redHistogram[0] = 0;
			} else {
				redHistogram[redValues[i]]++;
			}
			if (greenValues[i] == 0) {
				greenHistogram[0] = 0;
			} else {
				greenHistogram[greenValues[i]]++;
			}
			if (blueValues[i] == 0) {
				blueHistogram[0] = 0;
			} else {
				blueHistogram[blueValues[i]]++;
			}
		}

		redLine.add(redHistogram);
		blueLine.add(blueHistogram);
		greenLine.add(greenHistogram);

		axis1 = new Axis(chart.getChart());
		axis2 = new Axis(chart.getChart());

		axis1.getLabels().getFont().setColor(Color.WHITE);
		axis2.getLabels().getFont().setColor(Color.WHITE);
		chart.getAxes().getLeft().setEndPosition(32);
		axis1.setStartPosition(34);
		axis1.setEndPosition(66);
		axis1.getTitle().setVisible(true);
		axis1.getTitle().setText("Number of repetitions");
		axis1.getTitle().getFont().setColor(Color.WHITE);
		axis1.getTitle().setAngle(90);
		axis2.setStartPosition(68);
		axis2.setEndPosition(100);
		chart.getAxes().getBottom().getTitle().setVisible(true);
		chart.getAxes().getBottom().getTitle().setText("Pixel intensity (1-256)");
		chart.getAxes().getBottom().getTitle().getFont().setColor(Color.WHITE);
		chart.getAxes().getCustom().add(axis1);
		chart.getAxes().getCustom().add(axis2);

		greenLine.setCustomVertAxis(axis1);
		blueLine.setCustomVertAxis(axis2);
	}
}