package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.steema.teechart.Chart;
import com.steema.teechart.android.SeriesDataDialog.OnImportData;
import com.steema.teechart.languages.Language;

public class ChartEditor extends Dialog {

	public ChartEditor(final Context context, final Chart chart, 
			final OnImportData onImportData) {
		
		super(context);

		setTitle("Chart");

		LinearLayout main = new LinearLayout(context);
		main.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout layout1 = new LinearLayout(context);
		layout1.setOrientation(LinearLayout.VERTICAL);

		final Button aspect = new Button(context);
		aspect.setText(Language.getString("Aspect"));
		aspect.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				AspectEditor a = new AspectEditor(context, chart.getAspect());
				a.show();
			}
		});

		layout1.addView(aspect);

		if ((chart.getSeriesCount() == 0) || (chart.getSeries(0).useAxis)) {
			final Button axes = new Button(context);
			axes.setText(Language.getString("Axes"));
			axes.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					AxesEditor axes = new AxesEditor(context, chart.getAxes());
					axes.show();
				}
			});

			layout1.addView(axes);

		}

		final Button legend = new Button(context);
		legend.setText(Language.getString("Legend"));
		legend.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				LegendEditor edit = new LegendEditor(context, chart.getLegend());
				edit.show();
			}
		});

		layout1.addView(legend);

		final Button series = new Button(context);
		series.setText(Language.getString("Series"));
		series.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SeriesListEditor edit = new SeriesListEditor(context, chart, onImportData);
				edit.show();
			}
		});

		layout1.addView(series);
		
		final Button zoomStyle = new Button(context);
		zoomStyle.setText(Language.getString("ZoomStyle"));
		zoomStyle.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ZoomStyleEditor edit = new ZoomStyleEditor(context, chart.getZoom());
				edit.show();
			}
		});
		layout1.addView(zoomStyle);
		
		main.addView(layout1);
		
		LinearLayout layout2 = new LinearLayout(context);
		layout2.setOrientation(LinearLayout.VERTICAL);

		final Button titles = new Button(context);
		titles.setText(Language.getString("Titles"));
		titles.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				TitlesEditor edit = new TitlesEditor(context, chart);
				edit.show();
			}
		});

		layout2.addView(titles);

		if ((chart.getSeriesCount() == 0) || (chart.getSeries(0).useAxis)) {
			final Button walls = new Button(context);
			walls.setText(Language.getString("Walls"));
			walls.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					WallsEditor edit = new WallsEditor(context, chart
							.getWalls());
					edit.show();
				}
			});

			layout2.addView(walls);
		}

		final Button panel = new Button(context);
		panel.setText(Language.getString("Panel"));
		panel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				PanelEditor edit = new PanelEditor(context, chart
						.getPanel());
				edit.show();
			}
		});

		layout2.addView(panel);

		final Button page = new Button(context);
		page.setText(Language.getString("Paging"));
		page.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				PagingEditor edit = new PagingEditor(context, chart
						.getPage());
				edit.show();
			}
		});

		layout2.addView(page);

		main.addView(layout2);
		
		setContentView(main);
	}
}
