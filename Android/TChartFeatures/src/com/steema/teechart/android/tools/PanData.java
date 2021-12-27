package com.steema.teechart.android.tools;

import com.steema.teechart.Chart;

@SuppressWarnings("serial")
public class PanData extends ZoomChart {

	public PanData(Chart chart) {
		super(chart);
	}

	@Override
	protected void doAction(int x, int y) {

		final int factor = 3;
		
		boolean panned = false;
		panned = ((Chart) chart).panAxis(true, 0, -factor * x, panned);
		panned = ((Chart) chart).panAxis(false, 0, factor * y, panned);

		if (panned)
			chart.invalidate();
	}

}
