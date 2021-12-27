package com.steema.teechart.android.editors;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.languages.Language;

public class FontEditor extends Dialog {

	private String[] typefaces = { 
			"Normal", "Sans Serif", "Monospace", "Verdana",
			"Times New Roman", "Arial" };
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FontEditor(final Context context, final ChartFont font) {
		super(context);
		
		setTitle(Language.getString("Font"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
 		final Spinner typeface = new Spinner(context);
		typeface.setAdapter(new ArrayAdapter(context,
			             android.R.layout.simple_spinner_item, typefaces));
		
		typeface.setSelection(indexOf(font.getName()));
		
		typeface.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				font.setName((String)typeface.getSelectedItem());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(typeface);

		final CheckBox bold = new CheckBox(context);
		bold.setText(Language.getString("Bold"));
		bold.setChecked(font.getBold());
		bold.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				font.setBold(isChecked);
			}
		});
		layout.addView(bold);
		
		final CheckBox italic = new CheckBox(context);
		italic.setText(Language.getString("Italic"));
		italic.setChecked(font.getItalic());
		italic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				font.setItalic(isChecked);
			}
		});
		layout.addView(italic);
		
		final ButtonColor color = new ButtonColor(context, font.getColor(), 
				new ButtonColor.OnColorChangeListener() {
					
					public void onColorChanged(Color newColor) {
						font.setColor(newColor);
					}
				});

		layout.addView(color);
		
		final LabelSeekBar size = new LabelSeekBar(context, "Size", 1, 100, 
				font.getSize(), new OnLabelSeekListener() {
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						font.setSize(progress);
					}
				});
		
		layout.addView(size);
		
		final CheckBox underline = new CheckBox(context);
		underline.setText(Language.getString("Underline"));
		underline.setChecked(font.getUnderline());
		underline.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				font.setUnderline(isChecked);
			}
		});
		layout.addView(underline);

		setContentView(layout);
	}

	private int indexOf(final String name) {
		for (int t = 0; t < typefaces.length; t++)
			if (typefaces[t].equalsIgnoreCase(name)) 
				return t;
			
		return 0;
	}
}
