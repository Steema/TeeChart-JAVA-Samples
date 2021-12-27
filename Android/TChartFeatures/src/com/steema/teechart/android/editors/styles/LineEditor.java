package com.steema.teechart.android.editors.styles;

import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.Custom;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class LineEditor extends BaseSeriesEditor {

	public LineEditor(final Context context, final Custom series) {

		super(context, series);

		setTitle(Language.getString("Custom"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		// pending:  stacked
		
		final Button pointer = new Button(context);
		pointer.setText(Language.getString("Pointer"));
		pointer.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				PointerEditor edit = new PointerEditor(context, series.getPointer());
				edit.show();
			}
		});
		layout.addView(pointer);
		
		final CheckBox stairs = new CheckBox(context);
		stairs.setText(Language.getString("Stairs"));
		stairs.setChecked(series.getStairs());
		stairs.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				series.setStairs(isChecked);
			}
		});
		layout.addView(stairs);
		
		final CheckBox invStairs = new CheckBox(context);
		invStairs.setText(Language.getString("Inv. Stairs"));
		invStairs.setChecked(series.getInvertedStairs());
		invStairs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				series.setInvertedStairs(isChecked);
			}
		});
		layout.addView(invStairs);
		
		setContentView(layout);
	}
}
