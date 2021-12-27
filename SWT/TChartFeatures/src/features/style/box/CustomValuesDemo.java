
package features.style.box;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.styles.Box;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CustomValuesDemo extends ChartSample implements SelectionListener {

	private Box series;

	public CustomValuesDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		boolean isSelected = ((Button)source).getSelection();
		if (source == customButton) {
			series.setUseCustomValues(isSelected);
			if (series.getUseCustomValues()) {
				series.setMedian(15);
				series.setQuartile1(13);
				series.setQuartile3(17);
				series.setInnerFence1(12);
				series.setInnerFence3(18);
				series.setOuterFence1(10);
				series.setOuterFence3(20);
			}
			//chart1.repaint();
			updateDisplay();
		}    	
	}	

	protected void createContent() {
		super.createContent();
		customButton = addCheckButton("Custom Values", "", this);
		medianLabel = addLabel(SWT.LEFT, "");
		innerLabel = addLabel(SWT.LEFT, "");
		outerLabel = addLabel(SWT.LEFT, "");

		df = new DecimalFormat("0.00");		  	
	}

	protected void initContent() {
		super.initContent();    	   	
		customButton.setSelection(series.getUseCustomValues());
		updateDisplay(); 
	}

	protected void initChart() {
		super.initChart();
		chart1.getLegend().setVisible(false);
		chart1.getAspect().setView3D(false);
		chart1.addChartPaintListener(new ChartPaintAdapter() {
			public void chartPainted(ChartDrawEvent e) {
				updateDisplay();
			};
		});
		series = new Box(chart1.getChart());
		series.clear();
		series.add(new double[] {12, 14, 18, 18.5, 18.6, 18.6, 19, 24});
		series.setUseCustomValues(false); // by default, use internal calculating algorithms
		SeriesPointer tmpPointer;
		tmpPointer = series.getPointer();
		tmpPointer.getBrush().setColor(Color.WHITE);
		tmpPointer.setDraw3D(false);
		tmpPointer.setHorizSize(15);
		tmpPointer.setVertSize(15);
		tmpPointer.setInflateMargins(true);
		tmpPointer.setStyle(PointerStyle.RECTANGLE);
		tmpPointer.setVisible(true);
		tmpPointer = series.getExtrOut();
		tmpPointer.setInflateMargins(true);
		tmpPointer.setStyle(PointerStyle.STAR);
		tmpPointer.setVisible(true);
		tmpPointer = series.getMildOut();
		tmpPointer.setInflateMargins(true);
		tmpPointer.setStyle(PointerStyle.CIRCLE);
		tmpPointer.setVisible(true);
		series.getMedianPen().setStyle(DashStyle.DASH);
		series.setWhiskerLength(1.5);        
	}   			

	private DecimalFormat df;

	private void updateDisplay() {
		medianLabel.setText(
				"Median = " + df.format(series.getMedian())
		);
		innerLabel.setText(
				"Inner Fences = ["
				+ df.format(series.getInnerFence1())
				+ " ; "
				+ df.format(series.getInnerFence3())
				+ "]"
		);
		outerLabel.setText(
				"Outer Fences = ["
				+ df.format(series.getOuterFence1())
				+ " ; "
				+ df.format(series.getOuterFence3())
				+ "]"
		);
	}

	private Button customButton;
	private Label medianLabel, innerLabel, outerLabel;    
}
