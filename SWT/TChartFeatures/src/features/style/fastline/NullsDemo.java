/*
 * FastLineSeries.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.fastline;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.FastLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class NullsDemo extends ChartSample implements SelectionListener {

	private FastLine lineSeries;

	public NullsDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();	        	
			if (source == optionButtons[0]) {
				lineSeries.setStairs(isSelected);
				optionButtons[1].setEnabled(lineSeries.getStairs());
			} else if (source == optionButtons[1]) {
				lineSeries.setInvertedStairs(isSelected);
			} else if (source == optionButtons[2]) {
				lineSeries.setIgnoreNulls(isSelected);
			}
		}
	}

	protected void createContent() {
		super.createContent();
		optionButtons = new Button[3];
		optionButtons[0] = addCheckButton("Stairs mode", "", this);
		optionButtons[1] = addCheckButton("Inverted stairs", "", this);
		optionButtons[2] = addCheckButton("Ignore Nulls", "", this);        
	}

	protected void initContent() {
		super.initContent();  
        optionButtons[0].setSelection(true);		
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getFooter().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Fast-Line series with null points and stairs display.");

        lineSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        lineSeries.fillSampleValues(50);

        /* set some null points: */
        lineSeries.setNull(14);
        lineSeries.setNull(20);
        lineSeries.setNull(31);
        lineSeries.setNull(39);

        /* allow nulls:
         * (this property is false by default, for speed reasons)
         */
        lineSeries.setIgnoreNulls(false);

        /* Draw in "stairs" mode: */
        lineSeries.setStairs(true);
	}   	

	private Button[] optionButtons;		
}
