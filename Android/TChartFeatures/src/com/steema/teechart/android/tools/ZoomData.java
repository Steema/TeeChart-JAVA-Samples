package com.steema.teechart.android.tools;

import com.steema.teechart.Chart;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.Series;

public class ZoomData extends ZoomChart {
	
	public ZoomData(Chart chart) {
		super(chart);
	}

	protected void doAction(int x, int y) {
		
		if (chart.getSeriesCount() > 0) {
			final Series s = chart.getSeries(0);
			
			if (s.useAxis) {
				zoomAxis(s.getHorizAxis(), x);
				zoomAxis(s.getVertAxis(), y);
			}
		}
	}

	private void zoomAxis(Axis axis, int value) {
		final double amount = value * axis.getRange() / axis.iAxisSize;
		axis.setMinMax(axis.getMinimum() + amount, axis.getMaximum() - amount);
	}

}
