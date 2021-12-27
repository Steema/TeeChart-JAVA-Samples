package com.steema.teechart.android.editors;

import com.steema.teechart.languages.Language;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LabelSeekBar extends LinearLayout {

	public interface OnLabelSeekListener {

		public void onProgressChanged(LabelSeekBar bar, int progress);
	}

	private int progress;
	private int max;
	private int min;
	private TextView size;
	private OnLabelSeekListener onLabelSeekChange;
	
	protected void addProgress(int inc) {
		if (((progress+inc) <= max) && ((progress+inc) >= min)) {
			progress += inc;
			size.setText(Integer.toString(progress));
			onLabelSeekChange.onProgressChanged(this, progress);
		}
	}

	public LabelSeekBar(Context context, final String title, final int min, final int max, final int progress,
			final OnLabelSeekListener onLabelSeekChange) {
		super(context);
		
		this.progress = progress;
		this.min = min;
		this.max = max;
		this.onLabelSeekChange = onLabelSeekChange;
		
		final TextView sizeLabel = new TextView(context);
		sizeLabel.setText(Language.getString(title));
		addView(sizeLabel);
		
		size = new TextView(context);
		size.setPadding(10, 10, 0, 0);
		size.setText(Integer.toString(progress));
		
		/*
		size.setMax(max);
		size.setProgress(progress);
		size.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		size.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				onLabelSeekChange.onProgressChanged(seekBar, progress, fromUser);
			}
		});
		*/
		
		addView(size);

		final Button plus = new Button(context);
		plus.setText("+");
		plus.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				addProgress(1);
			}
		});
		
		addView(plus);

		final Button minus = new Button(context);
		minus.setText("-");
		minus.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				addProgress(-1);
			}
		});
		
		addView(minus);
	}
}
