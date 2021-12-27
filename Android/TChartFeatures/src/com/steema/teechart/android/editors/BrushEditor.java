package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.drawing.ChartBrush;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.Image;
import com.steema.teechart.languages.Language;

public class BrushEditor extends Dialog {

	public BrushEditor(final Context context, final ChartBrush brush) {

		super(context);

		setTitle(Language.getString("Brush"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final ButtonColor back = new ButtonColor(context, brush.getColor(), new ButtonColor.OnColorChangeListener() {
			public void onColorChanged(Color newColor) {
				brush.setColor(newColor);
			}
		});

		layout.addView(back);

		final LabelSeekBar transp = new LabelSeekBar(context, "Transparency", 0, 100, brush.getTransparency(), new OnLabelSeekListener() {

			public void onProgressChanged(LabelSeekBar bar, int progress) {
				brush.setTransparency(progress);
			}
		});

		layout.addView(transp);

		final Button gradient = new Button(context);
		gradient.setText(Language.getString("Gradient"));
		gradient.setBackground(getGradientDrawable(brush.getGradient()));
		gradient.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				GradientEditor g = new GradientEditor(context, brush.getGradient());
				g.show();
			}
		});

		layout.addView(gradient);

		final Button image = new Button(context);
		image.setText(Language.getString("Image"));

		Image i = brush.getImage();

		if (i != null)
			image.setBackground(new BitmapDrawable(context.getResources(), i.bitmap));

		image.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				BrowseImage browse = new BrowseImage(context, brush);
				browse.show();
			}
		});

		layout.addView(image);

		setContentView(layout);
	}

	private static Drawable getGradientDrawable(Gradient gradient) {
		Orientation o = null;

		if (gradient.getDirection() == GradientDirection.HORIZONTAL)
			o = Orientation.LEFT_RIGHT;
		else if (gradient.getDirection() == GradientDirection.VERTICAL)
			o = Orientation.TOP_BOTTOM;
		else if (gradient.getDirection() == GradientDirection.BACKDIAGONAL)
			o = Orientation.BL_TR;
		else if (gradient.getDirection() == GradientDirection.FORWARDDIAGONAL)
			o = Orientation.BR_TL;
		else
			o = Orientation.LEFT_RIGHT;

		GradientDrawable d;

		int[] colors;

		if (gradient.getUseMiddle()) {
			colors = new int[3];
			colors[0] = gradient.getStartColor().getRGB();
			colors[1] = gradient.getMiddleColor().getRGB();
			colors[2] = gradient.getEndColor().getRGB();
		} else {
			colors = new int[2];
			colors[0] = gradient.getStartColor().getRGB();
			colors[1] = gradient.getEndColor().getRGB();
		}

		d = new GradientDrawable(o, colors);

		if (gradient.getDirection() == GradientDirection.RADIAL)
			d.setGradientType(GradientDrawable.RADIAL_GRADIENT);

		return d;
	}
}
