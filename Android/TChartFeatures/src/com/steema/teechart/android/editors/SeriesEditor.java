package com.steema.teechart.android.editors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.steema.teechart.android.SeriesDataDialog;
import com.steema.teechart.android.SeriesDataDialog.OnImportData;
import com.steema.teechart.android.editors.styles.BaseSeriesEditor;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.Series;

public class SeriesEditor extends Dialog {

	public SeriesEditor(final Context context, final Series series, 
			final OnImportData onImportData) {

		super(context);
		
		setTitle(Language.getString("Series"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final EditText title = new EditText(context);
		String s = series.getTitle();
		
		if (s.equals(""))
			s = "Series " + Integer.toString(series.getChart().getSeries().indexOf(series));
		
		title.setText(s);
		title.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				series.setTitle(title.getText().toString());
				return false;
			}
		});
		layout.addView(title);

		final ButtonColor color = new ButtonColor(context, series.getColor(), 
				new ButtonColor.OnColorChangeListener() {
					
					public void onColorChanged(Color newColor) {
						series.setColor(newColor);
					}
				});

		layout.addView(color);
		
		final CheckBox colorEach = new CheckBox(context);
		colorEach.setText(Language.getString("Color Each"));
		colorEach.setChecked(series.getColorEach());
		colorEach.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				series.setColorEach(isChecked);
			}
		});
		layout.addView(colorEach);
		
		final Button marks = new Button(context);
		marks.setText(Language.getString("Marks"));
		marks.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				MarksEditor marks = new MarksEditor(context, series.getMarks());
				marks.show();
			}
		});
		
		layout.addView(marks);

		int index = com.steema.teechart.misc.Utils.seriesTypesIndex(series);
		
		@SuppressWarnings("rawtypes")
		final Class editClass = com.steema.teechart.android.editors.Utils.seriesEditorsOf[index];
		
		if (editClass != null)
		{
			final Button edit = new Button(context);
			edit.setText(Language.getString("Edit"));
			edit.setOnClickListener(new View.OnClickListener() {
				
				@SuppressWarnings("rawtypes")
				public void onClick(View v) {
					BaseSeriesEditor dialog;
					try {
						Object[] arguments = new Object[] { context, series };
						
						Constructor c = null;
						
						try {
						  Class[] argClass = new Class[] { Context.class, series.getClass() };
						  c = editClass.getConstructor(argClass);
						} catch (NoSuchMethodException e) {
							try {
								Class[] argClass = new Class[] { Context.class, series.getClass().getSuperclass() };
								c = editClass.getConstructor(argClass);
							} catch (NoSuchMethodException e1) {
								e1.printStackTrace();
							}
						}
						
						dialog = (BaseSeriesEditor)c.newInstance(arguments);
						dialog.show();
						
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				
				}
			});
			
			layout.addView(edit);
		}

		final Button data = new Button(context);
		data.setText(Language.getString("Data"));
		data.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SeriesDataDialog dialog = new SeriesDataDialog(context, series, onImportData);
				dialog.show();
			}
		});
		
		layout.addView(data);
		
		layout.addView(new ButtonBrush(context, series.getBrush()));
		
		setContentView(layout);
	}

}
