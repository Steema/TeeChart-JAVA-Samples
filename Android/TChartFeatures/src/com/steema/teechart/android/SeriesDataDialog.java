package com.steema.teechart.android;

import android.app.Dialog;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.steema.teechart.android.editors.ButtonColor;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.Utils;
import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.Series;

public class SeriesDataDialog extends Dialog {

	public interface OnImportData {
		public void execute();
	}

	private Series series;
	private final SeriesDataView seriesView;
	private Context context;
	private OnImportData onImportData;

	public SeriesDataDialog(final Context context, final Series series, OnImportData onImportData) {

		super(context);

		this.onImportData = onImportData;
		this.context = context;
		this.series = series;

		final LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);

		final LinearLayout layout2 = new LinearLayout(context);
		layout2.setOrientation(LinearLayout.HORIZONTAL);

		final ButtonColor color = new ButtonColor(context, series.getColor(), new ButtonColor.OnColorChangeListener() {

			public void onColorChanged(Color newColor) {
				series.setColor(newColor);
			}
		});

		layout2.addView(color);

		final Button add = new Button(context);
		add.setText(Language.getString("Add"));
		add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				addPoint();
			}
		});
		layout2.addView(add);

		final Button remove = new Button(context);
		remove.setText(Language.getString("Remove"));
		remove.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				removePoint();
			}
		});
		layout2.addView(remove);

		layout.addView(layout2);

		final ScrollView scroll = new ScrollView(context);

		scroll.setHorizontalScrollBarEnabled(true);

		seriesView = new SeriesDataView(context, series);

		scroll.addView(seriesView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

		layout.addView(scroll);

		setContentView(layout);

		setTitle(Language.getString("Series Data"));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(Menu.NONE, 0, Menu.NONE, Language.getString("Insert"));
		menu.add(Menu.NONE, 1, Menu.NONE, Language.getString("Remove"));
		menu.add(Menu.NONE, 2, Menu.NONE, Language.getString("Clear All"));

		SubMenu touch = menu.addSubMenu(Menu.NONE, 10, Menu.NONE, Language.getString("Show"));
		touch.add(Menu.NONE, 11, Menu.NONE, Language.getString("Colors"));
		touch.add(Menu.NONE, 12, Menu.NONE, Language.getString("X Values"));
		touch.add(Menu.NONE, 13, Menu.NONE, Language.getString("Labels"));
		touch.setGroupCheckable(Menu.NONE, true, false);

		menu.add(Menu.NONE, 20, Menu.NONE, Language.getString("Import Data"));

		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case 0: {
			addPoint();
			return true;
		}
		case 1: {
			removePoint();
			return true;
		}
		case 2: {
			Utils.yesNo(context, Language.getString("Are you sure to remove all data?"), new View.OnClickListener() {

				public void onClick(View v) {
					series.clear();
					seriesView.removeAllViews();
					seriesView.requestLayout();
				}
			}, null);

			return true;
		}

		case 20: {
			if (onImportData != null)
				onImportData.execute();

			return true;
		}

		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	private void removePoint() {
		series.delete(0);
		refreshLayout();
	}

	private void refreshLayout() {
		seriesView.removeAllViews();
		seriesView.addSeriesRows(context);
		seriesView.requestLayout();
	}

	private void addPoint() {
		series.add();
		refreshLayout();
	}
}