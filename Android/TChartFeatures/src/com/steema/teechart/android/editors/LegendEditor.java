package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import com.steema.teechart.languages.Language;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.legend.LegendTextStyle;

public class LegendEditor extends Dialog {

	public LegendEditor(final Context context, final Legend legend) {
		
		super(context);

		setTitle(Language.getString("Legend"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(legend.getVisible());
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				legend.setVisible(isChecked);
			}
		});
		layout.addView(visible);
		
		final String[] styles = { Language.getString("Auto"),
				Language.getString("Series"),
				Language.getString("Values"),
				Language.getString("Last Values") };

		final StringChooser style = new StringChooser(context, styles, 
				legend.getLegendStyle().getValue());
		
		style.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				legend.setLegendStyle(LegendStyle.fromValue(style.getSelectedItemPosition()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(style);

		final String[] textStyles = { Language.getString("Plain"),
				Language.getString("Left Value"),
				Language.getString("Right Value"),
				Language.getString("Left Percent"), 
				Language.getString("Right Percent"), 
				Language.getString("X Value"), 
				Language.getString("Value"), 
				Language.getString("Percent"), 
				Language.getString("X and Value"), 
				Language.getString("X and Percent") 
				};

		final StringChooser textStyle = new StringChooser(context, textStyles,
				legend.getTextStyle().getValue());
		
		textStyle.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				legend.setTextStyle(LegendTextStyle.fromValue(textStyle.getSelectedItemPosition()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(textStyle);

		final String[] positions = { 
				Language.getString("Left"),
				Language.getString("Right"),
				Language.getString("Top"),
				Language.getString("Bottom")
				 };
		
		final StringChooser position = new StringChooser(context, positions,
				legend.getAlignment().getValue());
		
		position.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				legend.setAlignment(LegendAlignment.fromValue(position.getSelectedItemPosition()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(position);

		final Button format = new Button(context);
		format.setText(Language.getString("Format"));
		format.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ShapeEditor shape = new ShapeEditor(context, legend, legend.getFont());
				shape.show();
			}
			
		});
		layout.addView(format);
		
		final CheckBox boxes = new CheckBox(context);
		boxes.setText(Language.getString("Check Boxes"));
		boxes.setChecked(legend.getCheckBoxes());
		boxes.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				legend.setCheckBoxes(isChecked);
			}
		});
		layout.addView(boxes);
		
		setContentView(layout);
	}
}
