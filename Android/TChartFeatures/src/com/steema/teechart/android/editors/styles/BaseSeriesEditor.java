package com.steema.teechart.android.editors.styles;

import com.steema.teechart.styles.Series;

import android.app.Dialog;
import android.content.Context;

public abstract class BaseSeriesEditor extends Dialog {

	public BaseSeriesEditor(Context context, Series series) {
		super(context);
	}
}
