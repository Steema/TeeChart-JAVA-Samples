package com.steema.teechart.android.tools;

import com.steema.teechart.Aspect;
import com.steema.teechart.Chart;

public class ZoomText extends ZoomChart {

	public ZoomText(Chart chart) {
		super(chart);
	}

	@Override
    protected void doAction(int x, int y) {
        Aspect a = chart.getAspect();
        
        double value = Math.hypot(x, y)*0.3;
        if ((x < 0 && y<=10 && y>=-10)| (y > 0 && x<=10 && x>=-10)) 
        	value = -value;
        
        a.setFontZoom(a.getFontZoom() + value);
	}
}
