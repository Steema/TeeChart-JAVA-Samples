package com.steema.teechart.android.tools;

import com.steema.teechart.Aspect;
import com.steema.teechart.Chart;

@SuppressWarnings("serial")
public class PanChart extends ZoomChart {

	public PanChart(Chart chart) {
		super(chart);
	}

	@Override
    protected void doAction(int x, int y) {
        Aspect a=chart.getAspect();
        a.setHorizOffset(a.getHorizOffset()+x);
        a.setVertOffset(a.getVertOffset()-y);
	}

}
