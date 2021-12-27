/*
 * MultiLineDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.marks;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MarksStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MultiLineDemo extends ChartSample implements SelectionListener {

    private Bar barSeries;
	
	public MultiLineDemo(Composite c) {
		super(c);
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
		     if (source == multiLineButton) {
		            barSeries.getMarks().setMultiLine(isSelected);
		            if (isSelected) {
		                barSeries.getLabels().setString(0, HELLO_MULTILINE);
		            } else {
		                barSeries.getLabels().setString(0, HELLO_SINGLELINE);
		            }
		        }		
		}
	}
	
	protected void createContent() {
		super.createContent();
		multiLineButton = addCheckButton("Marks Multiline", "", this);			
	}

	protected void initContent() {
		super.initContent();
		multiLineButton.setSelection(barSeries.getMarks().getMultiLine());
	}

	protected void initChart() {
		super.initChart();
        chart1.getPanel().setMarginBottom(10);		
		
        barSeries = new Bar(chart1.getChart());
        barSeries.add(123, HELLO_MULTILINE);
        barSeries.getMarks().setStyle(MarksStyle.LABELVALUE);
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().setMultiLine(true);
	}   				

    private Button multiLineButton;

    private final static String HELLO_MULTILINE="Hello\nWorld";
    private final static String HELLO_SINGLELINE="Hello World";	
}
