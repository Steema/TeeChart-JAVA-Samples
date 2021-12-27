package com.steema.teechart.android.editors;

import com.steema.teechart.Chart;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitlesEditor extends Dialog {

	public TitlesEditor(final Context context, final Chart chart) {
		super(context);
		
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final Button header = new Button(context);
		header.setText(Language.getString("Header"));
		header.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				TitleEditor edit = new TitleEditor(context, Language
						.getString("Title"), chart.getHeader(), null);
				edit.show();
			}
		});
		
		layout.addView(header);
		
		final Button footer = new Button(context);
		footer.setText(Language.getString("Footer"));
		footer.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				TitleEditor edit = new TitleEditor(context, Language
						.getString("Footer"), chart.getFooter(), null);
				edit.show();
			}
		});
		
		layout.addView(footer);
		
		final Button subHeader = new Button(context);
		subHeader.setText(Language.getString("Sub Header"));
		subHeader.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				TitleEditor edit = new TitleEditor(context, Language
						.getString("Sub Header"), chart.getSubHeader(), null);
				edit.show();
			}
		});
		
		layout.addView(subHeader);

		final Button subFooter = new Button(context);
		subFooter.setText(Language.getString("Sub Footer"));
		subFooter.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				TitleEditor edit = new TitleEditor(context, Language
						.getString("Sub Footer"), chart.getSubFooter(), null);
				edit.show();
			}
		});
		
		layout.addView(subFooter);

		setContentView(layout);
	}

}
