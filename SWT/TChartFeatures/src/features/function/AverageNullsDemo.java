/*
 * AverageNullsDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Average;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.PointerStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AverageNullsDemo extends ChartSample implements SelectionListener {

    private Line lineSeries ;
    private Bar barSeries;
    private Average avgFunction;

	public AverageNullsDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		boolean isSelected = ((Button)source).getSelection();
        if (source == includeNullsButton) {
            avgFunction.setIncludeNulls(isSelected);
            setLabelAverage();
        };
	}	

	protected void createContent() {
		super.createContent();            	   	
		includeNullsButton = addCheckButton("Include nulls", "", this);
		avgLabel = addLabel(SWT.LEFT, "");
		
	}

	protected void initContent() {
		super.initContent();    	   	
		includeNullsButton.setSelection(false);
	    setLabelAverage();		
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Average function and NULL points");		
        barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setTitle("Source");
        barSeries.setColor(Color.RED);
        barSeries.getMarks().setColor(Color.BLACK);
        barSeries.getMarks().setBackColor(Color.BLACK);
        barSeries.getMarks().getFont().setColor(Color.RED);
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().setStyle(MarksStyle.VALUE);
        barSeries.getXValues().setDateTime(false);

        /* Add some points and one null point... */
        barSeries.clear();
        barSeries.add( 10 , "One", Color.RED);
        barSeries.add( 20 , "Two", Color.RED);
        barSeries.add("Three");
        barSeries.add( 40 , "Four", Color.RED);
        barSeries.add( 50 , "Five", Color.RED);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("Average");
        lineSeries.setColor(Color.GREEN);
        lineSeries.setStacked(CustomStack.NONE);
        lineSeries.getMarks().setArrowLength(8);
        lineSeries.getMarks().getShadow().setVisible(false);
        lineSeries.getMarks().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        lineSeries.getMarks().setVisible(true);
        lineSeries.getPointer().setColor(Color.OLIVE);
        lineSeries.getPointer().setInflateMargins(false);
        lineSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
        lineSeries.getPointer().setVisible(true);
        lineSeries.getXValues().setDateTime(false);


        avgFunction = new com.steema.teechart.functions.Average();
        avgFunction.setChart(chart1.getChart());
        avgFunction.setPeriod(0); //all points
        avgFunction.setIncludeNulls(false);

        lineSeries.setDataSource(barSeries);
        lineSeries.setFunction(avgFunction);  
	} 

    private void setLabelAverage() {
        /* calculate the sum and number of points... */
        double tmp=0;
        int tmpCount=0;
        for (int t=0; t < barSeries.getCount(); t++) {
            /* consider or not null points... */
            if (avgFunction.getIncludeNulls() || (!barSeries.isNull(t)) ) {
                tmp = tmp + barSeries.getYValues().getValue(t);
                tmpCount++;
            }
        }
        avgLabel.setText( tmp + "/" + tmpCount + "=" + (tmp/tmpCount) );
    }
    
	private Button includeNullsButton;  
    private Label avgLabel;	
}
