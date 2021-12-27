package com.steema.teechart.android.editors.styles;

import com.steema.teechart.android.editors.PenEditor;
import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.CustomBar;
import com.steema.teechart.styles.MultiBars;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class BarEditor extends BaseSeriesEditor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BarEditor(final Context context, final CustomBar series) {

		super(context, series);

		setTitle(Language.getString("Bar"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);

		final String[] style = {
			Language.getString("Rectangle"),
			Language.getString("Pyramid"),
			Language.getString("Inv. Pyramid"),
			Language.getString("Cylinder"),
			Language.getString("Ellipse"),
			Language.getString("Arrow"),
			Language.getString("Rect. Gradient"),
			Language.getString("Cone"),
			Language.getString("Inv. Arrow"),
			Language.getString("Inv. Cone")  };
		
 		final Spinner styles = new Spinner(context);
		styles.setAdapter(new ArrayAdapter(context,
			             android.R.layout.simple_spinner_item, style));
		styles.setSelection(series.getBarStyle().getValue());
		
		styles.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				series.setBarStyle(BarStyle.fromValue(styles.getSelectedItemPosition()));
			}

			
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(styles);

		final TextView inc = new TextView(context);
		inc.setText(Language.getString("Multiple Bar"));
		layout.addView(inc);

		final String[] multis = { Language.getString("None"),
				Language.getString("Side"),
				Language.getString("Stacked"),
				Language.getString("Stacked 100%"),
				Language.getString("Side All"),
				Language.getString("Self Stack")
				};

		final Spinner multi = new Spinner(context);
		multi.setAdapter(new ArrayAdapter(context,
				android.R.layout.simple_spinner_item, multis));
		multi.setSelection(series.getMultiBar().getValue());
		multi.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				series.setMultiBar(MultiBars.fromValue(multi.getSelectedItemPosition()));
			}

			
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(multi);
		
		// origin

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
