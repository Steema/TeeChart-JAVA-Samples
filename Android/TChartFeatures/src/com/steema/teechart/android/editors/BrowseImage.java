package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.steema.teechart.drawing.ChartBrush;
import com.steema.teechart.languages.Language;

public class BrowseImage extends Dialog {

	ImageView image;

	public BrowseImage(Context context, final ChartBrush brush) {
		super(context);

		setTitle(Language.getString("Image"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		Button get = new Button(context);
		get.setText(Language.getString("Select_picture_file"));

		get.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				dismiss();
			}
		});

		layout.addView(get);

		if (brush.getImage() != null) {

			final Button clear = new Button(context);
			clear.setText(Language.getString("ClearImage"));
			clear.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					brush.setImage(null);
					image.setImageBitmap(null);
					clear.setEnabled(false);
				}
			});

			layout.addView(clear);

			image = new ImageView(context);
			image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			layout.addView(image);

			image.setImageBitmap(brush.getImage().bitmap);
		}

		setContentView(layout);
	}
}
