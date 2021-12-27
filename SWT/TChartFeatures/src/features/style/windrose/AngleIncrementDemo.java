
package features.style.windrose;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.WindRose;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AngleIncrementDemo extends ChartSample implements SelectionListener {

	private WindRose series1;
	
	public AngleIncrementDemo(Composite c) {
		super(c);
		anglesList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == anglesList) {
            switch (anglesList.getSelectionIndex()) {
                case 0: series1.setAngleIncrement(15); break;
                case 1: series1.setAngleIncrement(30); break;
                case 2: series1.setAngleIncrement(45); break;
                case 3: series1.setAngleIncrement(90); break;
            }
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	       	
    	addLabel(SWT.LEFT, "Angle increment: ");    	
    	anglesList = addCombo(SWT.READ_ONLY | SWT.SINGLE);
    	addLabel(SWT.LEFT, "degrees");    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
        anglesList.setItems(new String[] {"15", "30", "45", "90"});
        anglesList.select(0);  
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setColorWidth(35);
        tmpLegend.getFont().setColor(Color.RED);
        tmpLegend.getFont().setSize(15);
        tmpLegend.getFont().setBold(true);
        tmpLegend.getGradient().setStartColor(Color.GRAY);
        tmpLegend.getGradient().setVisible(true);
        tmpLegend.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpLegend.getSymbol().setWidth(35);

        chart1.getAxes().getBottom().setIncrement(10.0);
        chart1.getPanel().setColor(Color.GRAY);    	
        series1 = new WindRose(chart1.getChart());
        series1.fillSampleValues(20);
        series1.setCircled(true);
        series1.setAngleIncrement(15);
        series1.getBrush().setVisible(false);
        series1.getMarks().setVisible(false);
        series1.setCircleBackColor(Color.YELLOW);
        series1.getCircleLabelsFont().setColor(Color.BLUE);
        series1.getCircleLabelsFont().setBold(true);
        series1.getCircleLabelsFont().setItalic(true);
        series1.getCirclePen().setColor(Color.RED);
        series1.getCirclePen().setWidth(2);
        series1.getPointer().getBrush().setColor(Color.BLUE);
        series1.getPointer().getPen().setColor(Color.WHITE);
        series1.getPointer().setHorizSize(2);
        series1.getPointer().setVertSize(2);
        series1.getPointer().setVisible(true);
        series1.getPen().setColor(Color.LIME);
        series1.getPen().setWidth(2);
    }   			
    
    private Combo anglesList;    
}
