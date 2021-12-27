/*
 * ExpAverageDemo.java
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
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.ExpAverage;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ExpAverageDemo extends ChartSample implements SelectionListener {

    private Line sourceSeries, expAvgSeries;
    private ExpAverage expAvgFunction;
    
	public ExpAverageDemo(Composite c) {
		super(c);
		weightSlider.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == animateButton) {
        	boolean isSelected = ((Button)source).getSelection();
            if (isSelected) {
            	getDisplay().timerExec(ONE_MILLISECOND, timer);
            } else {
            	getDisplay().timerExec(-1, timer);
            }
        } else if (source == weightSlider) {
            int tmp = ((Slider)source).getSelection();
            if (source == weightSlider) {
                setFunctionWeight(tmp / 100.0);
            }
        }
	}

	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Exponential Weight: ");
		weightSlider = addSlider(SWT.HORIZONTAL, 0, 100, 20);
		weightLabel = addLabel(SWT.LEFT, "");
		animateButton = addCheckButton("Animate", "", this);
		
        //Create a timer.
        timer = new Runnable() {
            public void run() {
                int pos = weightSlider.getSelection()+delta;
                weightSlider.setSelection(pos);
                if ((pos < 2) || (pos > 98)) {
                    delta = -delta;
                }
                setFunctionWeight(pos / 100.0);
                /* re-enable timer again */
              getDisplay().timerExec(ONE_MILLISECOND, this);
            }
          };		
	}

	protected void initContent() {
		super.initContent();
        animateButton.setSelection(false);
        setFunctionWeight(0.20); 
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Exponential Average Function");		
        /* Chart Components */
        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.fillSampleValues(50);

        expAvgSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        expAvgSeries.setTitle("Exp. Average");
        expAvgSeries.setColor(Color.GREEN);
        expAvgSeries.getMarks().setVisible(false);
        expAvgSeries.getPointer().setVisible(false);
        SeriesPointer tmpPointer = expAvgSeries.getPointer();
        {
            tmpPointer.getBrush().setColor(Color.BLUE);
            tmpPointer.setHorizSize(2);
            tmpPointer.setVertSize(2);
            tmpPointer.setInflateMargins(true);
            tmpPointer.setVisible(true);
        }

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            ((Line)chart1.getSeries(i)).getMarks().setVisible(false);
            ((Line)chart1.getSeries(i)).setStacked(CustomStack.NONE);
        }

        expAvgSeries.setDataSource(sourceSeries);

        expAvgFunction = new com.steema.teechart.functions.ExpAverage();
        expAvgFunction.setChart(chart1.getChart());
        expAvgFunction.setPeriod(1);

        expAvgSeries.setFunction(expAvgFunction); 
	}
	
    private void setFunctionWeight(double newWeight) {
        try {
            expAvgFunction.setWeight(newWeight);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        weightLabel.setText(Double.toString(expAvgFunction.getWeight()));
    }

    private int delta = 2;

    private Button animateButton;
    private Slider weightSlider;
    private Label weightLabel;
    private Runnable timer;

    private final static int ONE_MILLISECOND = 1;	
}
