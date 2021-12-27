package com.steema.teechart.android.editors;

import com.steema.teechart.axis.Axes;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.AxisLabels;
import com.steema.teechart.axis.AxisTitle;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AxesEditor extends Dialog {

	private CheckBox visible;
	private CheckBox inverted;
	private Spinner axis;
	private Axes axes;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AxesEditor(final Context context, final Axes axes) {
		super(context);
		
		this.axes = axes;
		
		setTitle(Language.getString("Axes"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final String[] axesList = { 
				Language.getString("Left"), 
				Language.getString("Top"),
				Language.getString("Right"),
				Language.getString("Bottom"),
				Language.getString("Depth"),
				Language.getString("Depth Top")
				};
		
 		axis = new Spinner(context);
		axis.setAdapter(new ArrayAdapter(context,android.R.layout.simple_spinner_item, axesList));
		axis.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				setAxis(currentAxis());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(axis);

		LinearLayout layout2 = new LinearLayout(context);
		layout2.setOrientation(LinearLayout.HORIZONTAL);
		
		visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				currentAxis().setVisible(isChecked);
			}
		});
		layout2.addView(visible);
		
		inverted = new CheckBox(context);
		inverted.setText(Language.getString("Inverted"));
		inverted.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				currentAxis().setInverted(isChecked);
			}
		});
		layout2.addView(inverted);

		layout.addView(layout2);
		
		final Button scales = new Button(context);
		scales.setText(Language.getString("Scales"));
		scales.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				AxisScaleEditor edit = new AxisScaleEditor(context, currentAxis());
				edit.show();
			}
		});
		
		layout.addView(scales);

		final Button pen = new Button(context);
		pen.setText(Language.getString("Grid"));
		pen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				PenEditor edit = new PenEditor(context, currentAxis().getGrid());
				edit.show();
			}
		});
		
		layout.addView(pen);
		
		final Button labels = new Button(context);
		labels.setText(Language.getString("Labels"));
		labels.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				AxisLabels a = currentAxis().getLabels();
				ShapeEditor font = new ShapeEditor(context, a, a.getFont() );
				font.show();
			}
		});
		
		layout.addView(labels);

		final Button title = new Button(context);
		title.setText(Language.getString("Title"));
		title.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				final AxisTitle title = currentAxis().getTitle();
				TitleEditor edit = new TitleEditor(context, Language.getString("Title"), title, null);
				edit.show();
			}
		});
		
		layout.addView(title);

		setAxis(axes.getLeft());
		
		setContentView(layout);
	}

	protected Axis currentAxis() {
		return axes.getAxis(axis.getSelectedItemPosition());
	}

	private void setAxis(Axis axis) {
		visible.setChecked(axis.getVisible());
		inverted.setChecked(axis.getInverted());
	}

}
