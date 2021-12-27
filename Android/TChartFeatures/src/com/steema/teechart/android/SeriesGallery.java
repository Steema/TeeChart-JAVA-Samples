package com.steema.teechart.android;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.steema.teechart.languages.Language;

public class SeriesGallery extends Dialog {

	private LayoutInflater inflater;
	private ListView list;
	private Context context;
	
	public SeriesGallery(Context context, LayoutInflater inflater) {
		super(context);
		
		this.context = context;
		this.inflater = inflater;
		
		setTitle(Language.getString("Series"));
		
		list = new ListView(context);
		setContentView(list);
	}

	public void chooseSeries(@SuppressWarnings("rawtypes") final Class[] styles, final OnItemSelectedListener onItemSelectedListener) {

		list.setAdapter(new IconTextAdapter(context, R.layout.icontextrow, styles, inflater));
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				onItemSelectedListener.onItemSelected(arg0, arg1, arg2, arg3);
				dismiss();
			}
		});

		show();
	}
}
