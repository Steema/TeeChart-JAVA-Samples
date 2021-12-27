/*
 * NegativeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.errorbar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.ErrorBar;
import com.steema.teechart.tools.ColorLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class NegativeDemo extends ChartSample implements SelectionListener {

	private ErrorBar series;

	public NegativeDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		boolean isSelected = ((Button)source).getSelection();
		if (source == view3DButton) {
			chart1.getAspect().setView3D(isSelected);
		}      	
	}	

	protected void createContent() {
		super.createContent();
		view3DButton = addCheckButton("View 3D", "", this);    	
	}

	protected void initContent() {
		super.initContent();    	   	
		view3DButton.setSelection(chart1.getAspect().getView3D());        
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Error-Bar series with negative values.");
        chart1.getAspect().setView3D(false);
        chart1.getFrame().setVisible(false);
        chart1.getWalls().getBack().getPen().setVisible(false);
        chart1.getPanel().getGradient().setStartColor(Color.GRAY);
        chart1.getPanel().getGradient().setEndColor(Color.WHITE);
        chart1.getPanel().getGradient().setVisible(true);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.getSymbol().setWidth(10);
        tmpLegend.getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getAxisPen().setColor(Color.OLIVE);
        tmpAxis.getAxisPen().setWidth(1);
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.SILVER);
        tmpAxis.getLabels().getFont().setBold(true);
        tmpAxis.getLabels().getShadow().setColor(Color.BLACK);
        tmpAxis.getLabels().getShadow().setHorizSize(1);
        tmpAxis.getLabels().getShadow().setVertSize(1);
        tmpAxis.getMinorTicks().setLength(3);
        tmpAxis.getMinorTicks().setColor(Color.BLACK);
        tmpAxis.getTicks().setColor(Color.AQUA);
        tmpAxis.getTicksInner().setColor(Color.BLACK);

        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getGrid().setColor(Color.WHITE);
        tmpAxis.getGrid().setStyle(DashStyle.SOLID);
        tmpAxis.setGridCentered(true);
        
        series = new ErrorBar(chart1.getChart());
        initSeries();
        ColorLine tool = new ColorLine(chart1.getChart());
        tool.getPen().setColor(Color.GRAY);
        tool.setAxis(chart1.getAxes().getLeft());        
	}   			

    private void initSeries() {
        series.setBarStyle(BarStyle.RECTGRADIENT);
        series.setColor(Color.RED);
        series.getGradient().setDirection(GradientDirection.VERTICAL);
        series.getGradient().setUseMiddle(true);
        series.getGradient().setMiddleColor(Color.YELLOW);
        series.getGradient().setStartColor(Color.BLUE);
        series.getErrorPen().setColor(Color.BLUE);
        series.getErrorValues().setDateTime(false);
        series.clear();
        series.add(0,-123,23,"", Color.EMPTY);
        series.add(1,432,65,"", Color.EMPTY);
        series.add(2,-88,13,"", Color.EMPTY);
        series.add(3,222,44,"", Color.EMPTY);
        series.add(4,-321,49,"", Color.EMPTY);
    }

	private Button view3DButton;
}
