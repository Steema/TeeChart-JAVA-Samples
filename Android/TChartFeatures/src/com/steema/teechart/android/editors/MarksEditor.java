package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.SeriesMarks;

public class MarksEditor extends Dialog {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MarksEditor(final Context context, final SeriesMarks marks) {

		super(context);
		
		setTitle(Language.getString("Marks"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(marks.getVisible());
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				marks.setVisible(isChecked);
			}
		});
		layout.addView(visible);

		final String[] styles = { Language.getString("Value"),
				Language.getString("Percent"),
				Language.getString("Label"),
				Language.getString("Label Percent"),
				Language.getString("Label Value"),
				Language.getString("Legend"),
				Language.getString("Percent Total"),
				Language.getString("Label Percent Total"),
				Language.getString("X Value"),
				Language.getString("XY"),
				Language.getString("Series Title"),
				Language.getString("Point Index"),
				Language.getString("Percent Relative")
				
				};

		final Spinner style = new Spinner(context);
		style.setAdapter(new ArrayAdapter(context,android.R.layout.simple_spinner_item, styles));
		style.setSelection(marks.getStyle().getValue());
		style.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				marks.setStyle(MarksStyle.fromValue(style.getSelectedItemPosition()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(style);

		final Button format = new Button(context);
		format.setText(Language.getString("Format"));
		format.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ShapeEditor shape = new ShapeEditor(context, marks, marks.getFont());
				shape.show();
			}
		});
		
		layout.addView(format);

		final CheckBox visibleSym = new CheckBox(context);
		visibleSym.setText(Language.getString("Visible"));
		visibleSym.setChecked(marks.getSymbol().getVisible());
		visibleSym.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				marks.getSymbol().setVisible(isChecked);
			}
		});
		layout.addView(visibleSym);

		final Button symbols = new Button(context);
		symbols.setText(Language.getString("Symbols"));
		symbols.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				ShapeEditor edit = new ShapeEditor(context, marks.getSymbol(), null);
				edit.show();
			}
		});
		layout.addView(symbols);
		
		
		
		setContentView(layout);
	}

}
