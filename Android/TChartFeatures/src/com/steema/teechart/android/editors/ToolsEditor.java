package com.steema.teechart.android.editors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.steema.teechart.Chart;
import com.steema.teechart.android.ChartView;
import com.steema.teechart.languages.Language;
import com.steema.teechart.android.ToolsList;

public final class ToolsEditor extends ChartView {

	private int selected = 0;
	private Chart chart;
	@SuppressWarnings("unused")
	private Context context;
	final private String[] tools = new String[ToolsList.size()];

	public ToolsEditor(Chart chart,int selected) {
		this.chart = chart;
		this.selected = selected;

		for (int t = 0; t < tools.length; t++)
			tools[t] = ChartView.getToolDescription(t);
	}

	public void choose(final Context context) {
		this.context = context;
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(Language.getString("Select Tool"))
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						applyTool(chart,selected,context);
					}
				}).setNegativeButton(android.R.string.cancel, null);

		builder.setSingleChoiceItems(tools, selected,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						selected = item;
					}
				});

		builder.create().show();
	}
}
