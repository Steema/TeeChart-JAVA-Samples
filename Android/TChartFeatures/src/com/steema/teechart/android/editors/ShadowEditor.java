package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import com.steema.teechart.Shadow;
import com.steema.teechart.languages.Language;

public class ShadowEditor extends Dialog {

	public ShadowEditor(final Context context, final Shadow shadow) {
		
		super(context);

		setTitle(Language.getString("Shadow"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);

		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(shadow.getVisible());
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				shadow.setVisible(isChecked);
			}
		});
		layout.addView(visible);
		layout.setVerticalScrollBarEnabled(true);
		layout.addView(new ButtonBrush(context, shadow.getBrush()));

		final CheckBox smooth = new CheckBox(context);
		smooth.setText(Language.getString("Smooth"));
		smooth.setChecked(shadow.getSmooth());
		smooth.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				shadow.setSmooth(isChecked);
				
			}
		});
		
		layout.addView(smooth);
		
		LabelSeekBar size = new LabelSeekBar(context, "Size", -20, 20, 
				shadow.getSize().width, new LabelSeekBar.OnLabelSeekListener() {

			public void onProgressChanged(LabelSeekBar bar, int progress) {
				shadow.setSize(progress);
			}
		});
		
		layout.addView(size);

		LabelSeekBar smoothBlur = new LabelSeekBar(context, "Smooth Blur", 1, 10, 
				shadow.getSmoothBlur(), new LabelSeekBar.OnLabelSeekListener() {

			public void onProgressChanged(LabelSeekBar bar, int progress) {
				shadow.setSmoothBlur(progress);
			}
		});
		
		layout.addView(smoothBlur);

		setContentView(layout);
	}

}
