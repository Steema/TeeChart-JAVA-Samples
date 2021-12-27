/*
 * CurveFittingDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.functions.CurveFitting;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.FastLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CurveFittingDemo extends ChartSample implements ModifyListener, SelectionListener {

    private FastLine dataSeries, curve1Series, curve2Series;
    private CurveFitting fitting1Function, fitting2Function;
    
	public CurveFittingDemo(Composite c) {
		super(c);
		degree1Spinner.addModifyListener(this);
		degree2Spinner.addModifyListener(this);		
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source instanceof Spinner) {
            int degree = ((Spinner)source).getSelection();
            if (degree > 0) {
                if (source == degree1Spinner) {
                    fitting1Function.setPolyDegree(degree);
                } else if (source == degree2Spinner) {
                    fitting2Function.setPolyDegree(degree);
                }
            }
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == animateButton) {
	            if (isSelected) {
	            	getDisplay().timerExec(ONE_MILLISECOND, timer);
	            } else {
	            	getDisplay().timerExec(-1, timer);
	            }
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

        animateButton = addCheckButton("Animate", "", this);
        addLabel(SWT.LEFT, "Poly degree 1: ");
        degree1Spinner = new Spinner(getButtonPane(), SWT.READ_ONLY | SWT.BORDER);
        addLabel(SWT.LEFT, "2: ");
        degree2Spinner = new Spinner(getButtonPane(), SWT.READ_ONLY | SWT.BORDER);        

        //Create a timer.
        timer = new Runnable() {
            public void run() {
                 Random generator = new Random();

                 /* delete first point */
                 dataSeries.delete(0);

                 /* add a new random point */
                 dataSeries.add(
                         dataSeries.getXValues().getLast()+1,
                         dataSeries.getYValues().getLast()+(generator.nextInt(MAX_CHART_SAMPLES)-(MAX_CHART_SAMPLES / 2)),
                         "",
                         dataSeries.getColor()
                         );

                 curve1Series.checkDataSource();  // <-- fill again the points
                 curve2Series.checkDataSource();  // <-- fill again the points

              getDisplay().timerExec(ONE_MILLISECOND, this);
            }
          };
	}

	protected void initContent() {
		super.initContent();
        animateButton.setSelection(false);
        degree1Spinner.setMaximum(20);
        degree1Spinner.setMinimum(2);
        degree1Spinner.setIncrement(1);
        degree1Spinner.setSelection(5);
        degree2Spinner.setMaximum(20);
        degree2Spinner.setMinimum(2);
        degree2Spinner.setIncrement(1);
        degree2Spinner.setSelection(3);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Curve Fitting");
        chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);		
        /* Chart Components */
        dataSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        dataSeries.fillSampleValues(200);
        dataSeries.setTitle("data");

        curve1Series = new com.steema.teechart.styles.FastLine(chart1.getChart());
        curve2Series = new com.steema.teechart.styles.FastLine(chart1.getChart());

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            chart1.getSeries(i).getMarks().setVisible(false);
            ((FastLine)chart1.getSeries(i)).setStairs(false);
        }

        fitting1Function = new com.steema.teechart.functions.CurveFitting(chart1.getChart());
        fitting1Function.setPeriod(1);
        fitting1Function.setPolyDegree(5);

        curve1Series.setDataSource(dataSeries);
        curve1Series.setFunction(fitting1Function);
        curve1Series.setTitle("Curve1");

        fitting2Function = new com.steema.teechart.functions.CurveFitting();
        fitting2Function.setChart(chart1.getChart());
        fitting2Function.setPeriod(1);
        fitting2Function.setPolyDegree(3);

        curve2Series.setDataSource(dataSeries);
        curve2Series.setFunction(fitting2Function);
        curve2Series.setTitle("Curve2");

	}   	

    private Button animateButton;
    private Spinner degree1Spinner, degree2Spinner;
    private Runnable timer;

    private final static int ONE_MILLISECOND = 1;
    private final static int MAX_CHART_SAMPLES = 1000;
}
