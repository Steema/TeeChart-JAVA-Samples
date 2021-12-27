/*
 * AddDemo.java
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

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AddDemo extends ChartSample implements SelectionListener {

	private Line lineSeries ;
	private Bar barSeries;
	private Function addFunction;

	public AddDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		boolean isSelected = ((Button)source).getSelection();
		if (source == every2PointButton) {
			if (isSelected) {
				addFunction.setPeriod(2);
			} else {
				addFunction.setPeriod(0);
			}
		}; 	
	}	

	protected void createContent() {
		super.createContent();            	   	
		every2PointButton = addCheckButton("By every 2 points", "", this);	
	}

	protected void initContent() {
		super.initContent();    	   	
		every2PointButton.setSelection(false); 
	}

	protected void initChart() {
		super.initChart();
		chart1.getHeader().setVisible(true);
		chart1.setText("Add function");

		barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
		barSeries.setTitle("Source");
		barSeries.setColor(Color.RED);
		barSeries.getMarks().setColor(Color.BLACK);
		barSeries.getMarks().setBackColor(Color.BLACK);
		barSeries.getMarks().getFont().setColor(Color.RED);
		barSeries.getMarks().setArrowLength(20);
		barSeries.fillSampleValues(6);

		lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
		lineSeries.setTitle("Add");
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

		addFunction = new com.steema.teechart.functions.Add();
		addFunction.setChart(chart1.getChart());
		addFunction.setPeriod(0); //all points

		lineSeries.setDataSource(barSeries);
		lineSeries.setFunction(addFunction);
	} 

	private Button every2PointButton;    
}
