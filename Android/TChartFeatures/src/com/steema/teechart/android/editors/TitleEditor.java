package com.steema.teechart.android.editors;

import com.steema.teechart.TextShape;
import com.steema.teechart.android.ChartView.InputClickListener;
import com.steema.teechart.languages.Language;

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

public class TitleEditor extends Dialog {

	public TitleEditor(final Context context, final String title, 
			final TextShape shape,
			final InputClickListener onOk) {

		super(context);
		setTitle(title);

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final EditText input = new EditText(context);
		input.setText(shape.getText());
		input.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				shape.setText(input.getText().toString());
				return false;
			}
		});
		layout.addView(input);

		final CheckBox transp = new CheckBox(context);
		transp.setText(Language.getString("Transparent"));
		transp.setChecked(shape.getTransparent());
		transp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				shape.setTransparent(isChecked);
			}
		});
		layout.addView(transp);
		
		final Button format = new Button(context);
		format.setText(Language.getString("Format"));
		format.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ShapeEditor edit = new ShapeEditor(context, shape, shape.getFont());
				edit.show();
			}
			
		});
		layout.addView(format);
		
		setContentView(layout);
	}
}
