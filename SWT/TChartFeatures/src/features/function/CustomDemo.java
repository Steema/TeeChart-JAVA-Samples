/*
 * CustomDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Custom;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CustomDemo extends ChartSample implements SelectionListener {

    private Line lineSeries ;
    private Custom customFunction;
    
	public CustomDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
          //TODO  ChartEditor.editSeriesDatasource(lineSeries);
        }
	}
	
	protected void createContent() {
		super.createContent();	

        editButton = addPushButton("Edit...", "", this);
	}

	protected void initContent() {
		super.initContent();
        editButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("y = Sin(x / 10)");
        chart1.getAspect().setView3D(false);

        //TODO chart1.setClipPoints(false);
        chart1.getAxes().getLeft().setMaximumOffset(3);
        chart1.getAxes().getLeft().setMinimumOffset(3);
        
        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());

        lineSeries.setTitle("y = Sin( x / 10)");
        lineSeries.setColor(Color.BLUE);
        lineSeries.setStacked(CustomStack.NONE);
        lineSeries.getMarks().setVisible(false);
        lineSeries.getPointer().setVisible(false);
        lineSeries.getLinePen().setWidth(2);
        lineSeries.getXValues().setDateTime(false);
        lineSeries.getYValues().setDateTime(false);

        customFunction = new com.steema.teechart.functions.Custom(chart1.getChart());
        customFunction.setPeriod(1);
        customFunction.setNumPoints(100);

        customFunction.setYCalculator( new Custom.YCalculator() {
            public double calculate(Custom series, double x) {
                return Math.sin(x/10)*100;
            };
        });

        lineSeries.setFunction(customFunction);
        lineSeries.setStairs(false);
	}   		

    private Button editButton;	
}
