package com.steema.teechart.android.editors;

import com.steema.teechart.Aspect;
import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class AspectEditor extends Dialog {

	public AspectEditor(Context context, final Aspect aspect) {
		super(context);
		
		setTitle(Language.getString("Aspect"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox view3D = new CheckBox(context);
		view3D.setText("3D");
		view3D.setChecked(aspect.getView3D());

		view3D.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				aspect.setView3D(arg1);
			}
		});

		layout.addView(view3D);
		
		final CheckBox ortho = new CheckBox(context);
		ortho.setText(Language.getString("Orthogonal"));
		ortho.setChecked(aspect.getOrthogonal());

		ortho.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				aspect.setOrthogonal(arg1);
			}
		});

		layout.addView(ortho);
		
		final LabelSeekBar perspec = new LabelSeekBar(context, "Perspective", 0, 100, 
				aspect.getPerspective(), new OnLabelSeekListener() {
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						aspect.setPerspective(progress);
					}
				});
		
		layout.addView(perspec);
		
		final LabelSeekBar chart3D = new LabelSeekBar(context, "3D Percent", 0, 100, 
				aspect.getChart3DPercent(), new OnLabelSeekListener() {
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						aspect.setChart3DPercent(progress);
					}
				});
		
		layout.addView(chart3D);
		
		setContentView(layout);
	}

}
