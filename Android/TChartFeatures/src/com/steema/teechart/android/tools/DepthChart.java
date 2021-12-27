package com.steema.teechart.android.tools;

import com.steema.teechart.Aspect;
import com.steema.teechart.Chart;

@SuppressWarnings("serial")
public class DepthChart extends ZoomChart {

	public DepthChart(Chart chart) {
		super(chart);
	}

	@Override
    protected void doAction(int x, int y) {
        Aspect a=chart.getAspect();
        a.setChart3DPercent(Math.max(0, a.getChart3DPercent()+(x+y)));
	}
}
