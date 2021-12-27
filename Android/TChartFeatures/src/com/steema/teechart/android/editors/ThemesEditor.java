package com.steema.teechart.android.editors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.steema.teechart.Chart;
import com.steema.teechart.languages.Language;
import com.steema.teechart.themes.ThemesList;

public final class ThemesEditor {

	private Chart chart;
	private int selected = 0;

	final private String[] themes = new String[ThemesList.size()];

	public ThemesEditor(Chart chart, int selected) {
		this.chart = chart;
		this.selected = selected;

		for (int t = 0; t < themes.length; t++)
			themes[t] = ThemesList.getThemeDescription(t);
	}

	public void choose(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(Language.getString("Select Theme"))
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, themes[selected],
								Toast.LENGTH_SHORT).show();

						ThemesList.applyTheme(chart, selected);
					}
				}).setNegativeButton(android.R.string.cancel, null);

		builder.setSingleChoiceItems(themes, selected,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						selected = item;
					}
				});

		builder.create().show();
	}
}
