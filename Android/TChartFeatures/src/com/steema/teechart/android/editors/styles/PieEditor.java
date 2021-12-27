package com.steema.teechart.android.editors.styles;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

import com.steema.teechart.android.editors.LabelSeekBar;
import com.steema.teechart.android.editors.PenEditor;
import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.Pie;

public class PieEditor extends BaseSeriesEditor {

	public PieEditor(final Context context, final Pie series) {

		super(context, series);

		setTitle(Language.getString("Pie"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);

		final CheckBox circled = new CheckBox(context);
		circled.setText(Language.getString("Circled"));
		circled.setChecked(series.getCircled());
		circled.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				series.setCircled(isChecked);
			}
		});
		layout.addView(circled);
		
		final LabelSeekBar exploded = new LabelSeekBar(context, "Exploded", 0, 100, 
				series.getExplodeBiggest(), new OnLabelSeekListener() {
					
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						series.setExplodeBiggest(progress);
					}
				});
		layout.addView(exploded);
		
		final Button pen = new Button(context);
		pen.setText(Language.getString("Border"));
		pen.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				PenEditor edit = new PenEditor(context, series.getPen());
				edit.show();
			}
		});
		
		layout.addView(pen);

		setContentView(layout);
	}

}
