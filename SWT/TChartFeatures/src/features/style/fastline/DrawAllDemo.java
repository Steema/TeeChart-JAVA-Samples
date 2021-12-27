/*
 * DrawAllDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.fastline;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.FastLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DrawAllDemo extends ChartSample implements SelectionListener {

    private FastLine lineSeries;	
	
	public DrawAllDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
	        if (source == drawAllPoints) {
	            lineSeries.setDrawAllPoints(true);
	        } else if (source == drawNonRepeated) {
	            lineSeries.setDrawAllPoints(false);
	        }
		}
	}

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Draw: ");
		drawAllPoints = addRadioButton("All points (one million)", "", this);
		drawNonRepeated  = addRadioButton("Non-repeated X only", "", this);		
	}

	protected void initContent() {
		super.initContent();  
		drawNonRepeated.setSelection(true);			
	}

	protected void initSeries() {
        double[] MyX = new double[NUM_POINTS];
        double[] MyY = new double[NUM_POINTS];

        Random generator = new Random();
        double tmpRandom = 10000 * generator.nextDouble();

        for (int t=0; t < NUM_POINTS; t++) {
            tmpRandom += (100 * generator.nextDouble())-50;
            MyX[t] = t;
            MyY[t] = tmpRandom;
        }

        lineSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        lineSeries.getXValues().count = NUM_POINTS;
        lineSeries.getXValues().value = MyX;
        lineSeries.getYValues().count = NUM_POINTS;
        lineSeries.getYValues().value = MyY;

        // tell lineSeries to draw non-repeated points only ( much faster ! )
        lineSeries.setDrawAllPoints(false);       
	}	

	protected void initChart() {
		super.initChart();
        // Set axis calculations in "fast mode".
        // Note: For Windows Me and 98 might produce bad drawings when
        //       chart zoom is very big.
        //TODO myChart.getAxes().setFastCalc(true);
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("One million points !\nDisplaying non-repeated X position only.");

        chart1.getZoom().setAnimated(true);
        chart1.getZoom().setAnimatedSteps(3);
        chart1.getZoom().getBrush().setColor(Color.BLUE);
        chart1.getZoom().getBrush().setSolid(true);
        chart1.getZoom().getPen().setColor(Color.RED);
        chart1.getZoom().getPen().setWidth(3);
		
		initSeries();
	}   	

	private Button drawAllPoints;
	private Button drawNonRepeated;	
    private final static int NUM_POINTS = 1000000;  // one million !	

}
