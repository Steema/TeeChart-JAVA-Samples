package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

import com.steema.teechart.android.editors.ButtonColor.OnColorChangeListener;
import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.drawing.ChartPen;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.languages.Language;

public class PenEditor extends Dialog {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PenEditor(final Context context, final ChartPen pen) {
		super(context);
		
		setTitle(Language.getString("Border"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(pen.getVisible());
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				pen.setVisible(isChecked);
			}
		});
		layout.addView(visible);
		
		final ButtonColor color = new ButtonColor(context, pen.getColor(),
				new OnColorChangeListener() {
					
					public void onColorChanged(Color newColor) {
						pen.setColor(newColor);
					}
				});
		
		layout.addView(color);

		final LabelSeekBar width = new LabelSeekBar(context, "Width",
				1, 30, pen.getWidth(), new OnLabelSeekListener() {

					public void onProgressChanged(LabelSeekBar bar,
							int progress) {
						pen.setWidth(progress);
					}
		});
		
		layout.addView(width);
		
		final String[] styles = { Language.getString("Solid"),
				Language.getString("Dot"),
				Language.getString("Dash"),
				Language.getString("Dash Dot"),
				Language.getString("Dash Dot Dot")
				};

		final Spinner style = new Spinner(context);
		style.setAdapter(new ArrayAdapter(context,android.R.layout.simple_spinner_item, styles));
		style.setSelection(pen.getStyle().getValue());
		style.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				pen.setStyle(DashStyle.fromValue(style.getSelectedItemPosition()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(style);

		final LabelSeekBar transp = new LabelSeekBar(context, "Transparency", 0, 100, 
				pen.getTransparency(), new OnLabelSeekListener() {
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						pen.setTransparency(progress);
					}
				});
		
		layout.addView(transp);
		
		
		setContentView(layout);
	}
}
