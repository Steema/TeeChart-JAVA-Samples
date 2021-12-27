package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.steema.teechart.android.editors.ButtonColor.OnColorChangeListener;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.languages.Language;

public class GradientEditor extends Dialog {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GradientEditor(final Context context, final Gradient gradient) {
		super(context);
		
		setTitle(Language.getString("Gradient"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(gradient.getVisible());
		visible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				gradient.setVisible(isChecked);
			}
		});
		layout.addView(visible);
		
		final String[] directions = { Language.getString("Vertical"),
				Language.getString("Horizontal"),
				Language.getString("Forward diagonal"),
				Language.getString("Back diagonal"),
				Language.getString("Radial") };

		final Spinner direction = new Spinner(context);
		direction.setAdapter(new ArrayAdapter(context,android.R.layout.simple_spinner_item, directions));
		direction.setSelection(gradient.getDirection().getValue());
		direction.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				gradient.setDirection(GradientDirection
						.atIndex(direction.getSelectedItemPosition()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(direction);

		final ButtonColor start = new ButtonColor(context, gradient.getStartColor(),
				new OnColorChangeListener() {
					
					public void onColorChanged(Color newColor) {
						gradient.setStartColor(newColor);
					}
				});
		layout.addView(start);
		
		LinearLayout lMid = new LinearLayout(context);
		lMid.setOrientation(LinearLayout.HORIZONTAL);
		
		final ButtonColor mid = new ButtonColor(context, gradient.getMiddleColor(),
				new OnColorChangeListener() {
					
					public void onColorChanged(Color newColor) {
						gradient.setMiddleColor(newColor);
					}
				});
		lMid.addView(mid);

		final CheckBox use = new CheckBox(context);
		use.setText(Language.getString("UseMiddle"));
		use.setChecked(gradient.getUseMiddle());
		
		use.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				gradient.setUseMiddle(isChecked);
			}
		});
		
		lMid.addView(use);
		
		layout.addView(lMid);
		
		final ButtonColor end = new ButtonColor(context, gradient.getEndColor(),
				new OnColorChangeListener() {
					
					public void onColorChanged(Color newColor) {
						gradient.setEndColor(newColor);
					}
				});
		layout.addView(end);
				
		final Button swap = new Button(context);
		swap.setText(Language.getString("Swap"));
		swap.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				final Color tmp = gradient.getStartColor();
				gradient.setStartColor(gradient.getEndColor());
				gradient.setEndColor(tmp);
				
				start.setBackgroundColor(gradient.getStartColor().getRGB());
				end.setBackgroundColor(gradient.getEndColor().getRGB());
			}
		});
		
		layout.addView(swap);
		
		setContentView(layout);
	}

}
