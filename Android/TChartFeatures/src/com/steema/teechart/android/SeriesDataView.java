package com.steema.teechart.android;

import java.text.DecimalFormat;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.steema.teechart.android.editors.ColorPickerDialog;
import com.steema.teechart.android.editors.ColorPickerDialog.OnColorChangedListener;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.ValueList;

public class SeriesDataView extends TableLayout {

	private Series series;
	private DecimalFormat valuesFormat;

	public SeriesDataView(final Context context, final Series series) {
		super(context);
		this.series = series;

		valuesFormat = new DecimalFormat(series.getValueFormat());

		addSeriesRows(context);
	}

	protected void addSeriesRows(final Context context) {

		boolean hasX = series.hasNoMandatoryValues();
		@SuppressWarnings("unused")
		boolean hasLabels = !series.getLabels().isEmpty();

		for (int t = 0; t < Math.min(series.getCount(), 100); t++) {

			TableRow row = new TableRow(context);
			final TextView label = new EditText(context);

			TextView x = null;

			if (hasX)
				x = createValueEdit(context, t, series.getXValues());

			final TextView y = createValueEdit(context, t, series.getYValues());

			final Color c = series.getValueColor(t);

			final Button color = new Button(context);
			color.setBackgroundColor(c.getRGB());
			color.setText("    ");
			color.setTag(t);

			color.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final ColorPickerDialog c = new ColorPickerDialog(context, new OnColorChangedListener() {

						public void colorChanged(int newColor) {
							color.setBackgroundColor(newColor);
							// color.setText(String.valueOf(newColor));

							series.getColors().set((Integer) color.getTag(), new Color(newColor));
							series.repaint();
						}

					}, series.getValueColor((Integer) color.getTag()).getRGB());

					c.show();
				}

			});

			label.setText(series.getLabels().getString(t));
			label.setTag(t);

			label.addTextChangedListener(new TextWatcher() {

				public void afterTextChanged(Editable arg0) {
					series.getLabels().setString((Integer) label.getTag(), label.getText().toString());
					series.repaint();
				}

				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				}

				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				}

			});

			row.addView(label);

			if (hasX)
				row.addView(x);

			row.addView(y);
			row.addView(color);

			addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}
	}

	private TextView createValueEdit(final Context context, int index, final ValueList values) {
		final TextView edit = new EditText(context);
		edit.setTag(index);

		if (values.getDateTime())
			edit.setInputType(InputType.TYPE_CLASS_DATETIME);
		else
			edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

		edit.setText(valuesFormat.format(values.getValue(index)));

		edit.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable arg0) {

				final String s = edit.getText().toString();

				if (s.length() > 0) {
					try {
						values.setValue(((Integer) edit.getTag()), Double.parseDouble(s));

						series.repaint();
					} catch (NumberFormatException e) {
						Toast.makeText(context, "Not a valid number.", Toast.LENGTH_SHORT).show();
					}
				}
			}

			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

		});

		return edit;
	}
}
