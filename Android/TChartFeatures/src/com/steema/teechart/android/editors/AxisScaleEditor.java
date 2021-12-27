package com.steema.teechart.android.editors;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.languages.Language;
import com.steema.teechart.misc.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AxisScaleEditor extends Dialog {

	public AxisScaleEditor(Context context, final Axis axis) {
		super(context);

		setTitle(Language.getString("Axis Scale"));
		
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox autoMax = new CheckBox(context);
		autoMax.setText(Language.getString("Auto"));
		autoMax.setChecked(axis.getAutomatic());
		autoMax.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				axis.setAutomatic(isChecked);
			}
		});
		layout.addView(autoMax);
		
		final TextView max = new TextView(context);
		max.setText(Language.getString("Maximum"));
		layout.addView(max);

		final EditText eMax = new EditText(context);
		eMax.setText(Double.toString(axis.getMaximum()));
		eMax.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				axis.setMaximum(Utils.stringToDouble(eMax.getText().toString(), 
						axis.getMaximum()));
				return false;
			}
		});
		layout.addView(eMax);
		
		final TextView min = new TextView(context);
		min.setText(Language.getString("Minimum"));
		layout.addView(min);
		
		final EditText eMin = new EditText(context);
		eMin.setText(Double.toString(axis.getMinimum()));
		eMin.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				axis.setMinimum(Utils.stringToDouble(eMin.getText().toString(), 
						axis.getMinimum()));
				return false;
			}
		});
		layout.addView(eMin);
		
		final TextView inc = new TextView(context);
		inc.setText(Language.getString("Increment"));
		layout.addView(inc);

		final EditText eInc = new EditText(context);
		eInc.setText(Double.toString(axis.getIncrement()));
		eInc.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				axis.setIncrement(Utils.stringToDouble(eInc.getText().toString(),
						axis.getIncrement()));
				return false;
			}
		});
		layout.addView(eInc);

		setContentView(layout);
	}

}
