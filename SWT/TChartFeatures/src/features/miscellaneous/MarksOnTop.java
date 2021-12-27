/*
 * MarksOnTop.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.miscellaneous;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;

import features.ChartSample;

/**
 * @author yeray
 *
 */
public class MarksOnTop extends ChartSample implements SelectionListener {

	private Bar bar1, bar2, bar3;
	
	public MarksOnTop(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source == onTopButton) {
			boolean isSelected = ((Button)source).getSelection();
			for (int i = 0; i < chart1.getSeriesCount(); i++)
				chart1.getSeries(i).getMarks().setOnTop(isSelected);
        }
	}

	protected void createContent() {
		super.createContent();    	       	

		onTopButton = addCheckButton("Marks On Top", "", this);
	}

	protected void initContent() {
		super.initContent();   
		onTopButton.setSelection(bar1.getMarks().getOnTop());
	}

	protected void initChart() {
		super.initChart();

		chart1.getAspect().setView3D(false);
		
		bar1 = new com.steema.teechart.styles.Bar(chart1.getChart());
		bar1.fillSampleValues();
		
		bar2 = new com.steema.teechart.styles.Bar(chart1.getChart());
		bar2.fillSampleValues();
		
		bar3 = new com.steema.teechart.styles.Bar(chart1.getChart());
		bar3.fillSampleValues();
		
		bar1.setMultiBar(MultiBars.STACKED);
		bar2.setMultiBar(MultiBars.STACKED);
		bar3.setMultiBar(MultiBars.STACKED);
		
		for (int i = 0; i < chart1.getSeriesCount(); i++)
			chart1.getSeries(i).getMarks().setOnTop(true);
	}   		
	
	private Button onTopButton;
}
