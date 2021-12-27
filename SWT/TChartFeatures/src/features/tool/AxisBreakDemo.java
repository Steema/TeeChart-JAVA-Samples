/*
 * AxisBreakDemo.java
 *
 * <p>Copyright: (c) 2005-2011 by Steema Software SL.
 All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.AxisBreak;
import com.steema.teechart.tools.AxisBreakStyle;
import com.steema.teechart.tools.AxisBreaksTool;

import features.ChartSample;

/**
 * @author Steema Software 2011
 * 
 */
public class AxisBreakDemo extends ChartSample implements SelectionListener {

	AxisBreaksTool tool1;
	private Button leftTopButton;

	public AxisBreakDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {
	}

	public void widgetSelected(SelectionEvent se) {
		Object aux = se.getSource();
		if (aux == leftTopButton) {
			tool1.setActive(leftTopButton.getSelection());

			if (!leftTopButton.getSelection()) {
				tool1.getBreaks().clear();
			} else {
				AxisBreak aBreak = new AxisBreak(tool1);
				aBreak.setEndValue(32);
				aBreak.setStartValue(18);
				aBreak.setStyle(AxisBreakStyle.SMALLZIGZAG);

				tool1.getBreaks().add(aBreak);
			}
		}
	}

	protected void initChart() {
		super.initChart();

        chart1.getChart().getAspect().setView3D(false);
        chart1.getChart().getLegend().setVisible(false);

        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpSeries.setColor(Color.GREEN_YELLOW);
        tmpSeries.getLinePen().setWidth(2);
        tmpSeries.setSmoothed(true);
        tmpSeries.fillSampleValues(50);
        tmpSeries.getMarks().setVisible(false);

        tool1 = new AxisBreaksTool(chart1.getChart().getAxes().getBottom());
        tool1.setAxis(chart1.getAxes().getBottom());

        AxisBreak aBreak = new AxisBreak(tool1);
        aBreak.setEndValue(32);
        aBreak.setStartValue(18);
        aBreak.setStyle(AxisBreakStyle.SMALLZIGZAG);

        tool1.getBreaks().add(aBreak);
        tool1.setGapSize(20);
        tool1.getBrush().setColor(Color.GRAY);

        leftTopButton = addCheckButton("Active Axis Break", "", this);
		leftTopButton.setSelection(true);

        ThemesList.applyTheme(chart1.getChart(), 1);
	}
}
