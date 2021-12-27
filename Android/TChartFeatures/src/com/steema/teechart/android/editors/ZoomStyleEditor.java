package com.steema.teechart.android.editors;

import com.steema.teechart.Zoom;
import com.steema.teechart.Zoom.ZoomStyle;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ZoomStyleEditor extends Dialog {

	private Spinner style;
	private Zoom zoom;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ZoomStyleEditor(final Context context, final Zoom zoom) {
		super(context);
		
		this.zoom = zoom;
		
		setTitle(Language.getString("Zoom Style"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final String[] zoomStyleList = { 
				Language.getString("InChart"), 
				Language.getString("FullChart")
				};
		
		style = new Spinner(context);
		style.setAdapter(new ArrayAdapter(context,android.R.layout.simple_spinner_item, zoomStyleList));
		style.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				setZoomStyle(currentZoomStyle());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(style);

		setZoomStyle(zoom.getZoomStyle());
		
		setContentView(layout);
	}

	protected ZoomStyle currentZoomStyle() {
		switch (style.getSelectedItemPosition()) {
			case 0: return ZoomStyle.INCHART;
			//case 1: return ZoomStyle.FULLCHART;
			default: return ZoomStyle.FULLCHART;
		}
	}
	
	private void setZoomStyle(ZoomStyle zoomStyle) {
		this.zoom.setZoomStyle(zoomStyle);
	}
}
