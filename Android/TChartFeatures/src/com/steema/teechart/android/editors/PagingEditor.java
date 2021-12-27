package com.steema.teechart.android.editors;

import com.steema.teechart.Page;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PagingEditor extends Dialog {

	public PagingEditor(Context context, final Page page) {
		super(context);

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		final TextView max = new TextView(context);
		max.setText(Language.getString("Points_per_page"));
		layout.addView(max);
		layout.setVerticalScrollBarEnabled(true);
		final EditText eMax = new EditText(context);
		eMax.setInputType(InputType.TYPE_CLASS_NUMBER);
		eMax.setText(Integer.toString(page.getMaxPointsPerPage()));
		eMax.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				try {
					page.setMaxPointsPerPage(Integer.parseInt(eMax.getText().toString()));
				}
				catch (NumberFormatException e) {
				}
				
				return false;
			}
		});
		layout.addView(eMax);
		
		final TextView pages = new TextView(context);
		pages.setText(Language.getString("Count"));
		layout.addView(pages);

		final TextView count = new TextView(context);
		count.setText(String.valueOf(page.getCount()));
		layout.addView(count);

		final TextView current = new TextView(context);
		current.setText(Language.getString("PageNumber"));
		layout.addView(current);

		final TextView p = new TextView(context);
		p.setText(String.valueOf(page.getCurrent()));
		layout.addView(p);
		
		setContentView(layout);
	}

}
