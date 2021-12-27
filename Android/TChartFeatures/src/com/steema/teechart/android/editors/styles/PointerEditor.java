package com.steema.teechart.android.editors.styles;

import com.steema.teechart.android.editors.ButtonBrush;
import com.steema.teechart.android.editors.LabelSeekBar;
import com.steema.teechart.android.editors.PenEditor;
import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.SeriesPointer;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class PointerEditor extends Dialog {

	public PointerEditor(final Context context, final SeriesPointer pointer) {
		super(context);
		
		setTitle(Language.getString("Pointer"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);

		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(pointer.getVisible());
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				pointer.setVisible(isChecked);
			}
		});
		layout.addView(visible);
		
		final LabelSeekBar horiz = new LabelSeekBar(context, "Horiz. Size", 1, 200, 
				pointer.getHorizSize(), new OnLabelSeekListener() {
					
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						pointer.setHorizSize(progress);
					}
				});
		layout.addView(horiz);
		
		final LabelSeekBar vert = new LabelSeekBar(context, "Horiz. Size", 1, 200, 
				pointer.getVertSize(), new OnLabelSeekListener() {
					
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						pointer.setVertSize(progress);
					}
				});
		layout.addView(vert);

		layout.addView(new ButtonBrush(context, pointer.getBrush()));
		
		final Button pen = new Button(context);
		pen.setText(Language.getString("Border"));
		pen.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				PenEditor edit = new PenEditor(context, pointer.getPen());
				edit.show();
			}
		});
		
		layout.addView(pen);
		
		setContentView(layout);
	}

}
