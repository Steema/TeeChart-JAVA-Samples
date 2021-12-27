package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

import com.steema.teechart.Shape;
import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.languages.Language;

public class ShapeEditor extends Dialog {

	public ShapeEditor(final Context context, final Shape shape,
			final ChartFont shapeFont) {

		super(context);
		
		setTitle(Language.getString("Format"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(shape.getVisible());
		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				shape.setVisible(isChecked);
			}
		});
		
		if (shapeFont != null) {
			final Button font = new Button(context);
			font.setText(Language.getString("Font"));
			font.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					FontEditor font = new FontEditor(context, shapeFont);
					font.show();
				}

			});
			layout.addView(font);
		}

		layout.addView(new ButtonBrush(context, shape.getBrush()));

		final Button pen = new Button(context);
		pen.setText(Language.getString("Border"));
		pen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				PenEditor edit = new PenEditor(context, shape.getPen());
				edit.show();
			}
		});
		
		layout.addView(pen);
		
		final Button shadow = new Button(context);
		shadow.setText(Language.getString("Shadow"));
		shadow.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ShadowEditor edit = new ShadowEditor(context, shape.getShadow());
				edit.show();
			}
		});
		
		layout.addView(shadow);
		
		final LabelSeekBar round = new LabelSeekBar(context, "Round",
				0, 30, shape.getBorderRound(), new OnLabelSeekListener() {

					public void onProgressChanged(LabelSeekBar bar,
							int progress) {
						shape.setBorderRound(progress);
					}
		});
		
		layout.addView(round);
		
		final CheckBox transp = new CheckBox(context);
		transp.setText(Language.getString("Transparent"));
		transp.setChecked(shape.getTransparent());
		transp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				shape.setTransparent(isChecked);
			}
		});
		
		layout.addView(transp);
		
		final Button bevel = new Button(context);
		bevel.setText(Language.getString("Bevel"));
		bevel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*BevelEditor edit = new BevelEditor(context, shape.getBevel());
				edit.show();*/
			}
		});
		
		layout.addView(bevel);
		
		setContentView(layout);
	}
}
