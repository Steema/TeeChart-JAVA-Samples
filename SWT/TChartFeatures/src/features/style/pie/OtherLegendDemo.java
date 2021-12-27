/*
 * OtherLegendDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.pie;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.styles.PieOtherStyle;

import features.ChartSample;

/**
 * @author tom
 * 
 */
public class OtherLegendDemo extends ChartSample implements SelectionListener {

	private Pie pieSeries;

	public OtherLegendDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {
	}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;
		if (source instanceof Button) {
			boolean isSelected = ((Button) source).getSelection();
			if (source == otherLegendButton) {
				pieSeries.getOtherSlice().getLegend().setVisible(isSelected);
			}
		}
	}

	protected void createContent() {
		super.createContent();
		otherLegendButton = addCheckButton("Show 'other' legend", "", this);
	}

	protected void initContent() {
		super.initContent();
		otherLegendButton.setSelection(pieSeries.getOtherSlice().getLegend().getVisible());
	}

	protected void initChart() {
		super.initChart();
		chart1.getAspect().setElevation(315);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(0);
		chart1.getAspect().setRotation(360);

		pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
		pieSeries.getMarks().setVisible(true);

		// add data
		pieSeries.add(134, "Google");
		pieSeries.add(65, "Yahoo");
		pieSeries.add(23, "AltaVista");
		pieSeries.add(12, "AllTheWeb");
		pieSeries.add(9, "Terra");
		pieSeries.add(6, "Lycos");
		pieSeries.add(3, "Ask Jeeves");

		// prepare "Other" to group values below 10
		pieSeries.getOtherSlice().setStyle(PieOtherStyle.BELOWVALUE);
		pieSeries.getOtherSlice().setValue(10);

		// Display "Other" legend:
		Legend tmpLegend = pieSeries.getOtherSlice().getLegend();
		tmpLegend.setCustomPosition(true);
		tmpLegend.setLeft(150);
		tmpLegend.setTop(120);
		tmpLegend.setVisible(true);

		chart1.addChartPaintListener(new ChartPaintAdapter() {
			public void chartPainted(ChartDrawEvent ae) {
				// cosmetic line from normal legend to "other" legend
				if (pieSeries.getOtherSlice().getLegend().getVisible()) {
					chart1.getGraphics3D().getPen().setStyle(DashStyle.DOT);
					chart1.getGraphics3D().getPen().setWidth(3);
					chart1.getGraphics3D().getPen().setColor(Color.NAVY);
					int tmpX = chart1.getLegend().getLeft();
					chart1.getGraphics3D().moveTo(tmpX, chart1.getLegend().getShapeBounds().getBottom() - 1);
					chart1.getGraphics3D().lineTo(tmpX - 10, chart1.getLegend().getShapeBounds().getBottom());
					chart1.getGraphics3D().lineTo(tmpX - 10, pieSeries.getOtherSlice().getLegend().getTop() + 4);
					chart1.getGraphics3D().lineTo(pieSeries.getOtherSlice().getLegend().getRight(), pieSeries.getOtherSlice().getLegend().getTop() + 4);
				}
			}
		});
	}

	private Button otherLegendButton;
}
