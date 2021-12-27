/*
 * CCIDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CCI;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CCIDemo extends ChartSample implements ModifyListener, SelectionListener {

	private Candle sourceSeries;
	private CCI function;

	public CCIDemo(Composite c) {
		super(c);
		periodSpinner.addModifyListener(this);        
		constantText.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source instanceof Spinner) {
			int period = ((Spinner)source).getSelection();
			if ((period > 0) && (period < 101)) {
				if (source == periodSpinner) {
					function.setPeriod(period);
				}
			}
		} else  if (source == constantText) {
			double tmpValue= 0.015;
			if (source == constantText) {
				try {
					tmpValue = Double.parseDouble(constantText.getText());
					function.setConstant(tmpValue);
				} catch (NumberFormatException e) {
				}
			} 
		}
	}	

	public void widgetDefaultSelected(SelectionEvent se) {
		Widget source = se.widget;	
		if (source == constantText) {
			double tmpValue= 0.015;
			if (source == constantText) {
				try {
					tmpValue = Double.parseDouble(constantText.getText());
					function.setConstant(tmpValue);
				} catch (NumberFormatException e) {
				}
			} 
		}
	}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == activeButton) {
			boolean isSelected = ((Button)source).getSelection();
			function.getSeries().setActive(isSelected);
			chart1.getLegend().setVisible(isSelected);
			/* re-position the axis */
			if (function.getSeries().getActive()) {
				chart1.getAxes().getLeft().setEndPosition(70);
			} else {
				chart1.getAxes().getLeft().setEndPosition(100);
			}
		}
	}	

	protected void createContent() {
		super.createContent();	
		activeButton = addCheckButton("Active", "", this);

		addLabel(SWT.LEFT, "Period: ");
		periodSpinner = addSpinner(SWT.BORDER, 1, 100, 1); 

		addLabel(SWT.LEFT, "Constant: ");

		//TODO make the input more error-prone       
		constantText = addText(SWT.SINGLE | SWT.BORDER, Double.toString(0.015));
	}

	protected void initContent() {
		super.initContent();
		activeButton.setSelection(true);
		periodSpinner.setSelection(10);
	}

	protected void initChart() {
		super.initChart();
		chart1.getHeader().setVisible(true);
		chart1.setText("CCI Function Example");
		chart1.getAspect().setView3D(false);

		sourceSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
		sourceSeries.getMarks().setVisible(false);
		sourceSeries.getPointer().setVisible(true);
		sourceSeries.setTitle("Source");
		sourceSeries.getVertAxis().setStartPosition(0);
		sourceSeries.getVertAxis().setEndPosition(70);
		sourceSeries.getVertAxis().getTitle().setCaption("Candle");
		sourceSeries.fillSampleValues(60);

		function = new com.steema.teechart.functions.CCI(chart1.getChart());
		function.setPeriod(10);

		Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
		functionSeries.setTitle("CCI");
		functionSeries.setColor(Color.GREEN);
		functionSeries.getLinePen().setColor(Color.GREEN);
		functionSeries.setVerticalAxis(VerticalAxis.RIGHT);
		functionSeries.getVertAxis().setStartPosition(70);
		functionSeries.getVertAxis().setEndPosition(100);
		functionSeries.getVertAxis().getTitle().setCaption("CCI");
		functionSeries.setDataSource(sourceSeries);
		functionSeries.setFunction(function);
	}   	

	private Spinner periodSpinner;
	private Button activeButton;
	private Text constantText;	
}
