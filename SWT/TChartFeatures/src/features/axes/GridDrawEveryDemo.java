
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bubble;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class GridDrawEveryDemo extends ChartSample implements ModifyListener, SelectionListener {

	public GridDrawEveryDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == drawEverySpinner) {
			getSelectedAxis().getGrid().setDrawEvery(drawEverySpinner.getSelection());    		
		}
	}	

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == chooseAxis){
			drawEverySpinner.setSelection(getSelectedAxis().getGrid().getDrawEvery());
		}
	}

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Axis: ");
		chooseAxis = addCombo(SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER, this);
		addLabel(SWT.LEFT, " Draw every: ");        
		drawEverySpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 1, Integer.MAX_VALUE, 1, this);				
	}

	protected void initContent() {
		super.initContent();
		getButtonPane().setVisible(false);
		chooseAxis.add("Vertical");
		chooseAxis.add("Horizontal"); 		
		chooseAxis.select(0);
		drawEverySpinner.setSelection(getSelectedAxis().getGrid().getDrawEvery());        
	}

	protected void initChart() {
		super.initChart();
		chart1.getAspect().setView3D(false);
		/* Set axes alternate labels */
		for (int t=0; t < chart1.getAxes().getCount(); t++) {
			chart1.getAxes().getAxis(t).getLabels().setAlternate(true);
		}

		Bubble series = new Bubble(chart1.getChart());
		/* Sample values */
		series.fillSampleValues(6);
		series.setHorizontalAxis(HorizontalAxis.BOTTOM);
		series.setVerticalAxis(VerticalAxis.LEFT);

		series.getMarks().setVisible(false);

		SeriesPointer pointer= series.getPointer();
		pointer.getGradient().setStartColor(Color.YELLOW);
		pointer.getGradient().setEndColor(Color.RED);
		pointer.getGradient().setVisible(true);
		pointer.setInflateMargins(true);
		pointer.setVisible(true);

		chart1.getAxes().getLeft().setIncrement(200);   
	}   		

	private Axis getSelectedAxis() {
		if (chooseAxis.getSelectionIndex() == 0) {
			return chart1.getAxes().getLeft();
		} 
		else {
			return chart1.getAxes().getBottom();
		}
	}
	
	private Combo chooseAxis;
	private Spinner drawEverySpinner;	
}
