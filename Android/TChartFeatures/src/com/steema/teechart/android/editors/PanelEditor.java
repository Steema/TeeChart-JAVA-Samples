package com.steema.teechart.android.editors;

import com.steema.teechart.Panel;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class PanelEditor extends Dialog {

	public PanelEditor(final Context context, final Panel panel) {
		super(context);

		setTitle(Language.getString("Panel"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final Button format = new Button(context);
		format.setText(Language.getString("Format"));
		format.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ShapeEditor shape = new ShapeEditor(context, panel, null);	
				shape.show();
			}
			
		});
		layout.addView(format);
		
		setContentView(layout);
	}

}
