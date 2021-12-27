package com.steema.teechart.android.editors;

import java.lang.reflect.InvocationTargetException;

import com.steema.teechart.Chart;
import com.steema.teechart.android.SeriesDataDialog;
import com.steema.teechart.android.SeriesDataDialog.OnImportData;
import com.steema.teechart.android.SeriesGallery;
import com.steema.teechart.languages.Language;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Series;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class SeriesListEditor extends Dialog {

	private Button edit;
	private Button delete;
	private Button data;
	private CheckBox visible;
	private Spinner seriesList;
	private Chart chart;
	private Button fill;
	private Button importData;
	
	public SeriesListEditor(final Context context, final Chart chart, 
			final OnImportData onImportData) {
		
		super(context);

		this.chart = chart;
		
		setTitle(Language.getString("Series"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		LinearLayout layout2 = new LinearLayout(context);
		layout2.setOrientation(LinearLayout.HORIZONTAL);

		seriesList = new Spinner(context);
		seriesList.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				visible.setEnabled(true);
				visible.setChecked(currentSeries().getVisible());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				visible.setEnabled(false);
			}
		});
		
		final Button add = new Button(context);
		add.setText(Language.getString("Add"));
		add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				SeriesGallery gallery = new SeriesGallery(context, getLayoutInflater());
				gallery.chooseSeries(Utils.seriesTypesOf,
						new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView,
							View childView, int position, long id) {

						try {
							
							final Series s;
							s = Series.createNewSeries(chart, Utils.seriesTypesOf[position], null);
							chart.addSeries(s);
							s.setTitle("Series "+Integer.toString(chart.getSeriesCount()));

							addSeries(context, seriesList, chart, chart.getSeriesCount() - 1);
							
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
		
			}
		});

		layout2.addView(add);
		
		delete = new Button(context);
		delete.setText(Language.getString("Delete"));
		delete.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				com.steema.teechart.editors.Utils.yesNo(context, 
						"Are you sure to remove " + currentSeries().getTitle() + "?",
						new View.OnClickListener() {
							
							public void onClick(View v) {
								final int num = seriesList.getSelectedItemPosition();
								chart.removeSeries(chart.getSeries(num));
								addSeries(context, seriesList, chart, num);
							}
						}, null); 
			}
		});

		delete.setEnabled(chart.getSeriesCount() > 0);
		
		layout2.addView(delete);

		layout2.addView(seriesList);

		layout.addView(layout2);
		
		visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				final int selected = seriesList.getSelectedItemPosition();

				if (selected != Spinner.INVALID_POSITION) {
					Series s = chart.getSeries(selected);
					s.setVisible(isChecked);
				}
			}
		});
		
		visible.setEnabled(chart.getSeriesCount() > 0);
		
		layout.addView(visible);
		
		data = new Button(context);
		data.setText(Language.getString("Data"));
		data.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final int selected = seriesList.getSelectedItemPosition();

				if (selected != Spinner.INVALID_POSITION) {
					Series s = chart.getSeries(selected);
					SeriesDataDialog edit = new SeriesDataDialog(context, s, onImportData);
					edit.show();
				}
			}
		});

		data.setEnabled(chart.getSeriesCount() > 0);
		
		layout.addView(data);

		edit = new Button(context);
		edit.setText(Language.getString("Format"));
		edit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final int selected = seriesList.getSelectedItemPosition();

				if (selected != Spinner.INVALID_POSITION) {
					Series s = chart.getSeries(selected);
					SeriesEditor edit = new SeriesEditor(context, s, onImportData);
					edit.show();
				}
			}
		});

		edit.setEnabled(chart.getSeriesCount() > 0);
		
		layout.addView(edit);
		
		if (onImportData != null) {
		importData = new Button(context);
		importData.setText(Language.getString("Import Data"));
		importData.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final int selected = seriesList.getSelectedItemPosition();

				if (selected != Spinner.INVALID_POSITION) {
					onImportData.execute();
				}
			}
		});

		importData.setEnabled(chart.getSeriesCount() > 0);
		
		layout.addView(importData);
		}
		
		fill = new Button(context);
		fill.setText(Language.getString("Fill Sample Values"));
		fill.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final int selected = seriesList.getSelectedItemPosition();

				if (selected != Spinner.INVALID_POSITION) {
					
					com.steema.teechart.editors.Utils.yesNo(context, 
							"Are you sure? (Current data will be lost)",
							new View.OnClickListener() {
								
								public void onClick(View v) {
									currentSeries().fillSampleValues();
								}
							}, null); 
				}
			}
		});

		fill.setEnabled(chart.getSeriesCount() > 0);
		
		layout.addView(fill);

		addSeries(context, seriesList, chart, chart.getSeriesCount() - 1);

		setContentView(layout);
	}

	protected Series currentSeries() {
		final int selected = seriesList.getSelectedItemPosition();

		if (selected == Spinner.INVALID_POSITION)
			return null;
		else
			return chart.getSeries(selected);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSeries(Context context, Spinner seriesList, Chart chart, int selected) {
		final int num = chart.getSeriesCount();
		final String[] series = new String[num];

		for (int t = 0; t < num; t++) {
			String s = chart.getSeries(t).getTitle();

			if (s.equals(""))
				s = "Series " + Integer.toString(t);

			series[t] = s;
		}

		seriesList.setAdapter(new ArrayAdapter(context,	android.R.layout.simple_spinner_item, series));
		
		if (selected >= num)
			selected--;
		
		if (selected >= 0)
			seriesList.setSelection(selected);
		
		final boolean enable = selected >= 0;
		edit.setEnabled(enable);
		delete.setEnabled(enable);
		data.setEnabled(enable);
		visible.setEnabled(enable);
		fill.setEnabled(enable);
		
		if (importData != null)
			importData.setEnabled(enable);
	}

}
