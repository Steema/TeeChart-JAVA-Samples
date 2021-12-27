package com.steema.teechart.android.editors;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.languages.Language;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class ButtonColor extends Button {

	public interface OnColorChangeListener {
		public void onColorChanged(final Color newColor);
	}

	public ButtonColor(final Context context, final Color color,
			final OnColorChangeListener onColorChangeListener) {

		super(context);
		setBackgroundColor(color.getRGB());
		setText(Language.getString("Color"));

		final int c = color.getRGB();

		if (c == -16777216) // black
			setTextColor(Color.white.getRGB());
		else {
			float[] hsv = new float[3];
			Color.colorToHSV(color.getRGB(), hsv);

			if (hsv[2] > 200)
				setTextColor(Color.white.getRGB());
		}

		setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ColorPickerDialog c = new ColorPickerDialog(context,
						new ColorPickerDialog.OnColorChangedListener() {

							public void colorChanged(int newColor) {
								setBackgroundColor(newColor);

								onColorChangeListener.onColorChanged(Color
										.fromArgb(newColor));
							}
						}, color.getRGB());

				c.show();
			}

		});
	}

}
