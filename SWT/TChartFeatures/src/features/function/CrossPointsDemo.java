/*
 * CrossPointsDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CrossPoints;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CrossPointsDemo extends ChartSample implements SelectionListener {

    private Line lineSeries;
    
	public CrossPointsDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		boolean isSelected = ((Button)source).getSelection();
        if (source == viewCrossPointsButton) {
            lineSeries.setVisible(isSelected);
        }
	}	

	protected void createContent() {
		super.createContent();            	   	
		viewCrossPointsButton = addCheckButton("View CrossPoints line", "", this);	
	}

	protected void initContent() {
		super.initContent();    	   	
		viewCrossPointsButton.setSelection(true); 
	}

	protected void initChart() {
		super.initChart();
	    chart1.getAspect().setView3D(false);
	    
        Line tmpSeries;
        for (int i=0; i < 3; i++) {
            tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
            tmpSeries.setTitle("Series"+(i+1));
            tmpSeries.setStacked(CustomStack.NONE);
            tmpSeries.getMarks().setVisible(false);

            SeriesPointer tmpPointer = tmpSeries.getPointer();
            tmpPointer.setHorizSize(2);
            tmpPointer.setVertSize(2);
            tmpPointer.setInflateMargins(true);
            tmpPointer.setStyle(PointerStyle.RECTANGLE);
            tmpPointer.setVisible(true);
        }

        chart1.getSeries(0).fillSampleValues(25);
        chart1.getSeries(1).fillSampleValues(25);

        lineSeries = (Line)chart1.getSeries(2);
        lineSeries.setColor(Color.YELLOW);

        CrossPoints tmpFunction = new com.steema.teechart.functions.CrossPoints(chart1.getChart());
        tmpFunction.setPeriod(0); //all points

        ArrayList tmpArray = new ArrayList();
        tmpArray.add(chart1.getSeries(0));
        tmpArray.add(chart1.getSeries(1));
        lineSeries.setDataSource(tmpArray);
        lineSeries.setFunction(tmpFunction);
	} 

	private Button viewCrossPointsButton;	
}
