/*
 * DivisionDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function.momentum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.MomentumDivision;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.ColorLine;
import com.steema.teechart.tools.ColorLineStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DivisionDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Line sourceSeries, functionSeries;
    private MomentumDivision momentumFunction;
    private ColorLine colorLineTool;
    private Axis customAxis;
    
	public DivisionDemo(Composite c) {
		super(c);
		periodSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source instanceof Spinner) {
            int period = ((Spinner)source).getSelection();
            if ((period > 0) && (period < 101)) {
                if (source == periodSpinner) {
                    momentumFunction.setPeriod(period);
                }
            }
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		boolean isSelected = ((Button)source).getSelection();
        if (source == showMomentumButton) {
            /* show / hide the momentum series */
            functionSeries.setActive(isSelected);

            /* re-position the axis */
            if (functionSeries.getActive()) {
                chart1.getAxes().getLeft().setEndPosition(80);
            } else {
                chart1.getAxes().getLeft().setEndPosition(100);
            }

            /* show / hide the custom right axis */
            chart1.getAxes().getCustom().getAxis(0).setVisible(functionSeries.getActive());

            /* show / hide the blue color line */
            colorLineTool.setActive(functionSeries.getActive());
        };
	}	

	protected void createContent() {
		super.createContent();
        showMomentumButton = addCheckButton("Show Momentum", "", this);        
                         
        addLabel(SWT.LEFT, "Period: ");
        periodSpinner = addSpinner(SWT.BORDER, 1, 100, 1);                 
	}

	protected void initContent() {
		super.initContent();
        showMomentumButton.setSelection(true);
        periodSpinner.setSelection(10);
    }

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);
        chart1.getLegend().setLegendStyle(LegendStyle.SERIES);
        chart1.getPanel().setMarginRight(15.0);
        
        /* create custom axis */
        customAxis = chart1.getAxes().getCustom().getNew();
        customAxis.setHorizontal(false);
        customAxis.setOtherSide(true);
        customAxis.setStartPosition(80.0);
        customAxis.getTitle().setAngle(90);
        customAxis.getTitle().setText("Mom. Div.");
        customAxis.getTitle().setVisible(true);
        chart1.getAxes().getLeft().setEndPosition(80);

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.fillSampleValues(50);

        momentumFunction = new com.steema.teechart.functions.MomentumDivision(chart1.getChart());
        /*{ function is = 100 * Value / (Previous 10th value) */
        momentumFunction.setPeriod(10);

        functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("Momentum Div.");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getLinePen().setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
        functionSeries.setCustomVertAxis(customAxis);
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(momentumFunction);

        colorLineTool = new ColorLine(chart1.getChart());
        colorLineTool.getPen().setColor(Color.BLUE);
        colorLineTool.setStyle(ColorLineStyle.MINIMUM);
        colorLineTool.setValue(480.0);
        colorLineTool.setAxis(sourceSeries.getVertAxis());      
	}   		
	
    private Button showMomentumButton;
    private Spinner periodSpinner;	
}
