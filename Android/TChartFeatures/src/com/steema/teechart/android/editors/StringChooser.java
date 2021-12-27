package com.steema.teechart.android.editors;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StringChooser extends Spinner {

	@SuppressWarnings("rawtypes")
	public StringChooser(Context context, final String[] items, int selected) {
		super(context);

		@SuppressWarnings("unchecked")
		ArrayAdapter adapter = new ArrayAdapter(context,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		setAdapter(adapter);

		setSelection(selected);
	}

}
