package com.steema.teechart.android.editors;

import com.steema.teechart.drawing.ChartBrush;
import com.steema.teechart.languages.Language;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class ButtonBrush extends Button {

	public ButtonBrush(final Context context, final ChartBrush brush) {
		super(context);
		setText(Language.getString("Brush"));
		setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				BrushEditor edit = new BrushEditor(context, brush);
				edit.show();
			}
		});
	}
}
